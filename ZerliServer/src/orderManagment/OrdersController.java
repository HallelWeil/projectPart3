package orderManagment;

import database.DBController;
import order.*;
import paymentManagment.CreditController;
import paymentManagment.PaymentController;
import remindersManagment.ReminderController;
import user.User;

/**
 * controller for the different actions for order management
 * 
 * @author halel
 *
 */
public class OrdersController {
	DBController dbController = DBController.getInstance();
	/**
	 * 1 h = 3600000 ms
	 */
	private static final int HOUR_IN_MILLISECONS = 3600000;

	/**
	 * Approve the order delivery - update status to collected
	 * 
	 * @param ordernumber
	 * @throws Exception - if failed throw exception with the error msg
	 */
	public void approveOrderDelivery(int ordernumber) throws Exception {
		// 1. try to get the order from the database
		Order order = dbController.getOrdrFromDB(ordernumber);
		// 2. confirm the order exist
		if (order == null)
			throw new Exception("There is no such order!");
		// 3. check if it has a home delivery
		if (!order.isHomeDelivery())
			throw new Exception("This order wasn't for home delivery! please check again");
		// 4. check if the order status is correct
		switch (order.getOrderStatus()) {
		case CANCELED:
		case NOT_APPROVED:
		case WAITING_FOR_APPROAVL:
		case WAITING_FOR_CANCELLATION_APPROVAL:
		case WAITING_FOR_PAYMENT:
			throw new Exception(
					"This order wasn't ready for delivery! the order is: \n" + order.getOrderStatus().toString());
		case COLLECTED:
			throw new Exception("The order delivery already confirmed");
		case APPROVED:
			break;
		default:
			break;
		}
		// 5. we can confirm the order delivery!
		order.setOrderStatus(OrderStatus.COLLECTED);
		if (!dbController.updateOrder(order))
			throw new Exception("Failed to confirm the order delivery! please try again later");

		// 6. we got here - the order delivery confirmed successfully
	}

	/**
	 * update the order status -> to approve, not approved and canceled, give refund
	 * if needed
	 * 
	 * @param order
	 * @throws Exception - if failed throw exception with the error msg
	 */
	public void approveOrder(Order order) throws Exception {
		switch (order.getOrderStatus()) {
		case APPROVED:
			approveOrder(order.getOrderNumber());
			break;
		case CANCELED:
			cancelOrder(order.getOrderNumber());
			break;
		case NOT_APPROVED:
			notApproveOrder(order.getOrderNumber());
			break;
		default:
			break;
		}
	}

	private void approveOrder(int ordernumber) throws Exception {
		// 1. get the order from the database
		Order order = dbController.getOrdrFromDB(ordernumber);
		// 2. check the order current status
		switch (order.getOrderStatus()) {
		case APPROVED:
			throw new Exception("The order already been approved");
		case CANCELED:
		case COLLECTED:
		case COMPLETED:
		case WAITING_FOR_CANCELLATION_APPROVAL:
		case WAITING_FOR_PAYMENT:
			throw new Exception("You dont need to approve the order \n the order is :" + order.getOrderStatus());
		default:
			break;
		}
		// 3. we can approve the order!
		order.setOrderStatus(OrderStatus.APPROVED);
		if (!dbController.updateOrder(order))
			throw new Exception("Failed to approve the order! please try again later");
		// 6. we got here - the order approved successfully
		// 7. send reminder and we done!
		User user = dbController.getUser(order.getUsername());
		sendReminder(order, " approved ", 0, user);
	}

	private void notApproveOrder(int ordernumber) throws Exception {
		// 1. get the order from the database
		Order order = dbController.getOrdrFromDB(ordernumber);
		// 2. check the order current status
		switch (order.getOrderStatus()) {
		case NOT_APPROVED:
			throw new Exception("The order is already not approved");
		case CANCELED:
		case APPROVED:
		case COLLECTED:
		case COMPLETED:
		case WAITING_FOR_CANCELLATION_APPROVAL:
		case WAITING_FOR_PAYMENT:
			throw new Exception("You dont need to not approve the order \n the order is :" + order.getOrderStatus());
		default:
			break;
		}
		// 3. we can approve the order!
		order.setOrderStatus(OrderStatus.NOT_APPROVED);
		if (!dbController.updateOrder(order))
			throw new Exception("Failed to update the order! please try again later");
		// 6. we got here - the order is not approved
		// 7. send reminder and we done!
		User user = dbController.getUser(order.getUsername());
		sendReminder(order, " not approved ", 0, user);
	}

	private void cancelOrder(int ordernumber) throws Exception {
		// 1. get the order from the database
		Order order = dbController.getOrdrFromDB(ordernumber);
		// 2. check the order current status
		switch (order.getOrderStatus()) {
		case CANCELED:
			throw new Exception("The order already been canceled");
		case APPROVED:
		case COLLECTED:
		case COMPLETED:
		case WAITING_FOR_APPROAVL:
		case WAITING_FOR_PAYMENT:
			throw new Exception("You dont need to approve the order \n the order is :" + order.getOrderStatus());
		default:
			break;
		}
		// 3. we can now check the delivery time and give refund accordingly
		double refund = 0;
		long orderTime = order.getArrivalDate().getTime();
		long currentTime = System.currentTimeMillis();
		long timeDiff = orderTime - currentTime;
		if (timeDiff < HOUR_IN_MILLISECONS) {
			// no refund in the lsat hour
			refund = 0;
		} else if (timeDiff >= HOUR_IN_MILLISECONS && timeDiff <= 3 * HOUR_IN_MILLISECONS) {
			// between hour to 3 hours - 50% refund
			refund = order.getPrice() * 0.5;
		} else {
			// more than 3 hours - full refund
			refund = order.getPrice();
		}
		// 4. we can update the order status
		order.setOrderStatus(OrderStatus.CANCELED);
		if (!dbController.updateOrder(order))
			throw new Exception("Failed to cancel the order! please try again later");
		// 5. we can give the refund
		String userCard = dbController.getCardInfo(order.getUsername());
		if (userCard == null) {
			throw new Exception("Order canceled, no card info was found for the refund");
		}
		User user = dbController.getUser(order.getUsername());
		if (refund > 0) {
			// give refund!
			CreditController creditController = new CreditController();
			creditController.refund(user.getPersonID(), refund);
		}
		// 6. we got here - the order approved successfully
		// 7. send reminder and we done!
		sendReminder(order, " canceled ", refund, user);
	}

	private void sendReminder(Order order, String orderIs, double refund, User user) {
		StringBuilder sb = new StringBuilder();
		sb.append("Hello " + user.getFirstName() + " " + user.getLastName() + "\n");
		sb.append("Your order " + order.getOrderNumber() + " was " + orderIs);
		if (refund > 0) {
			sb.append("You will receive refund of " + refund);
		}
		sb.append("automatic message from Zerli system");
		String msgString = sb.toString();
		ReminderController reminders = new ReminderController();
		reminders.sendEmail(msgString, user.getEmail());
		reminders.sendSMS(msgString, user.getPhoneNumber());
	}
}
