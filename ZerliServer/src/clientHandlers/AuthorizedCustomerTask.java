package clientHandlers;

import java.util.ArrayList;

import catalog.Product;
import msg.Msg;
import order.Order;
import orderManagment.OrderController;
import server.ServerMsgController;

public class AuthorizedCustomerTask extends ClientTasks {
	/**
	 * order controller to manage the order process
	 */
	private OrderController orderController;

	public AuthorizedCustomerTask(HandleClientTask clientTaskHandler) {
		super(clientTaskHandler);
		// TODO Auto-generated constructor stub
	}

	/**
	 * handle the authorized customer actions, can view the catalog, get his orders
	 * history, pay for order ,place order and update order status
	 */
	@Override
	public Msg handleTask(Object msg) {
		// if no correct msg was found
		if (msgController.mgsParser(msg) == false)
			return ErrorMsg;
		switch (msgController.getType()) {
		case LOG_OUT_REQUEST:
		case EXIT:
			// to log out remove the user entity
			dbController.disconnectUser(clientTaskHandler.getActiveUser().getUsername());
			this.orderController = null;
			clientTaskHandler.setActiveUser(null);
			newMsgToSend = ServerMsgController.createAPPROVE_LOGOUTMsg();
			break;
		case GET_CATALOG_PAGE:
			// get catalog page
			ArrayList<Product> catalog = dbController.getCatalogCategory(msgController.getCategory());// toto
			newMsgToSend = ServerMsgController.createRETURN_CATALOG_PAGEMsg(catalog);
			break;
		case GET_ALL_ORDERS:
			ArrayList<Order> orders = dbController.getAllOrdersOfCustomer(null,
					clientTaskHandler.getActiveUser().getUsername());
			newMsgToSend = ServerMsgController.createRETURN_ALL_ORDERSMsg(orders);
			break;
		case PAY_FOR_ORDER:
			// get the card info
			String cardInfo = dbController.getCardInfo(clientTaskHandler.getActiveUser().getUsername());
			// use the order controller to pay
			if (orderController.payForOrder(cardInfo)) {
				// payment succeed, save the order!
				if (orderController.saveOrderToDB()) {
					// the order saved successfully
					newMsgToSend = ServerMsgController.createRETURN_PAYMENT_APPROVALMsg();
				} else {
					newMsgToSend = ServerMsgController.createERRORMsg("Error! failed to save the order!");
				}
			} else {
				newMsgToSend = ServerMsgController.createERRORMsg("Payment declined!");
			}
			// reset the order controller
			orderController.reset();
			break;
		case PLACE_ORDER_REQUEST:
			// use the order controller
			orderController = new OrderController();
			Order order = orderController.placeOrder(msgController.getCart(), 0,
					clientTaskHandler.getActiveUser().getUsername());
			newMsgToSend = ServerMsgController.createRETURN_ORDERMsg(order);
			break;
		case UPDATE_ORDER_STATUS:
			// update order status in the db
			dbController.updateOrder(msgController.getOrder());
			break;
		case GET_ORDER:
			Order order2 = dbController.getOrdrFromDB(msgController.getOrderNumber());
			order2.setItems(dbController.getItemInOrderFromDB(msgController.getOrderNumber()));
			newMsgToSend = ServerMsgController.createRETURN_ORDERMsg(order2);
			break;
		default:
			// handle cant do it
			newMsgToSend = ServerMsgController.createERRORMsg("Error! unauthorized access");
			break;

		}
		return newMsgToSend;
	}
}
