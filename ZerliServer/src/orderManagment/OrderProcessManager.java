package orderManagment;

import java.sql.Timestamp;
import java.util.ArrayList;

import cart.Cart;
import cart.ProductInCart;
import catalog.CustomizedProduct;
import database.DBBoundry;
import database.DBController;
import order.Order;
import order.OrderStatus;
import order.ProductInOrder;
import paymentManagment.CreditController;
import paymentManagment.PaymentController;

/**
 * Create a order from a cart, manage orders status and approval
 * 
 * @author halel
 *
 */
public class OrderProcessManager {

	private Order activeOrder;
	private double creditUse = 0;
	private String userID;
	private String username;

	/**
	 * Reset the orderController
	 */
	public void reset() {
		activeOrder = new Order();
		creditUse = 0;
		userID = "";
		username = "";
	}

	/**
	 * Get a cart object, username and order number. Create new order with the given
	 * number and get the items and other order details from the cart object. for
	 * each item, calculate the item price and add it to the total order price.
	 * return the newly created order object aand save the order for future actions.
	 * 
	 * @param cart        the cart we want to place as order
	 * @param orderNumber the order number
	 * @param username    the user who place the order
	 * @return
	 */
	public Order placeOrder(Cart cart, int orderNumber, String username, String id) {
		userID = id;
		this.username = username;
		if (cart == null)
			return null;
		// create new order
		Order newOrder = new Order();
		// add the order data
		newOrder.setOrderDate(new Timestamp(System.currentTimeMillis()));
		newOrder.setOrderNumber(orderNumber);
		newOrder.setBranchName(cart.getBranchName());
		newOrder.setArrivalDate(cart.getArrivalDate());
		newOrder.setOrderData("");// for future use
		newOrder.setHomeDelivery(cart.isWithHomeDelivery());
		newOrder.setDeliveryDetails(cart.getDeliveryDetails());
		newOrder.setOrderStatus(OrderStatus.WAITING_FOR_PAYMENT);
		newOrder.setUsername(username);
		// calculate the price and get the items
		ArrayList<ProductInOrder> items = new ArrayList<ProductInOrder>();
		newOrder.setPrice(calculateOrderPriceAndGetItems(cart.getProductsInCart(), items));
		newOrder.setItems(items);
		newOrder.setPersonalLetter(cart.getGreetingCard());
		activeOrder = newOrder;
		getDiscountAndCredit();
		return newOrder;
	}

	private void getDiscountAndCredit() {
		DBController dbcontroller = DBController.getInstance();
		CreditController creditController = new CreditController();
		double price = activeOrder.getPrice();
		// 1. check if its the customer's first order
		if (dbcontroller.getAllOrdersOfCustomer(null, username).size() == 0) {
			// you get 20% off for the first order
			price = 0.8 * price;
			activeOrder.setOrderData(activeOrder.getOrderData() + "\n20% off for your first order!");
		}
		// 2. check if there is credit, if there is -> use the credit
		double credit = creditController.getShopCredit(userID);
		if (credit > 0) {
			// if there is enough credit for the full price
			if (credit >= price) {
				activeOrder.setOrderData(activeOrder.getOrderData() + "\nUsed Shop credit for " + price);
				price = 0;
				creditUse = price;
			} else {
				price = price - credit;
				creditUse = credit;
				activeOrder.setOrderData(activeOrder.getOrderData() + "\nUsed Shop credit for " + credit);
			}
		}
		// 3. save the updated price
		activeOrder.setPriceToPay(price);
	}

	/**
	 * pay for the activeOrder, return true if the payment succeed, use the given
	 * card info
	 * 
	 * @param cardInfo
	 * @return true if the payment succeed
	 */
	public boolean payForOrder(String cardInfo) {
		PaymentController paymnetControlelr = new PaymentController();
		CreditController creditController = new CreditController();
		// 1. try to use the credit
		if (creditUse > 0) {
			if (!creditController.useShopCredit(userID, creditUse)) {
				return false;
			}
		}
		// 2. try to pay
		if (activeOrder.getPriceToPay() > 0) {
			try {
				if (paymnetControlelr.pay(cardInfo, activeOrder.getPriceToPay())) {
					activeOrder.setOrderStatus(OrderStatus.WAITING_FOR_APPROAVL);
					return true;
				}
			} catch (Exception e) {
			}
			// 3. if the payment failed, return the credit
			if (creditUse > 0) {
				creditController.refund(userID, creditUse);
			}
			return false;
		}
		return true;
	}

	/**
	 * Get the active order object
	 * 
	 * @return
	 */
	public Order getActiveOrder() {
		return activeOrder;
	}

	/**
	 * calculate the products price and place the items in the items list. get the
	 * products in card, use the db to check again for each product cost, add the
	 * cost to the total cost and save the product in order entity to te items list
	 * 
	 * @param products the products list
	 * @param items    the new ProductInOrder list
	 * @return
	 */
	private double calculateOrderPriceAndGetItems(ArrayList<ProductInCart> products, ArrayList<ProductInOrder> items) {
		double price = 0, tempPrice;
		ProductInOrder tempProductInOrder;
		// for each product in the list
		for (ProductInCart product : products) {
			tempPrice = 0;
			// if its a customized product calculate the price base on the items cost
			if (product.getProduct() instanceof CustomizedProduct) {
				CustomizedProduct tempProduct = (CustomizedProduct) (product.getProduct());
				tempPrice = tempProduct.getPriceRangeHighLimit();// the price is the high selected limit
			}
			// for regular product
			else {
				tempPrice = product.getProduct().getPrice();
			}
			tempProductInOrder = new ProductInOrder();
			tempProductInOrder.setAmount(product.getAmount());
			tempProductInOrder.setCategory(product.getProduct().getCategory());
			tempProductInOrder.setOrderNumber(0);// no number for now
			tempProductInOrder.setName(product.getProduct().getName());
			tempProductInOrder.setPrice(tempPrice);
			tempPrice = tempPrice * product.getAmount();
			items.add(tempProductInOrder);
			price += tempPrice;
		}
		return price;
	}

	/**
	 * save the active order to the database, including items in order and delivery
	 * details(if exists)
	 * 
	 * @return true on success
	 */
	public boolean saveOrderToDB() {
		DBController dbcontroller = DBController.getInstance();
		int orderNumber = dbcontroller.saveOrderToDB(activeOrder);
		if (orderNumber != -1) {

			if (activeOrder.isHomeDelivery()) {
				activeOrder.getDeliveryDetails().setOrderID(orderNumber);
				if (!dbcontroller.saveDeliveryDetails(activeOrder.getDeliveryDetails())) {
					return false;
				}
			}
			for (ProductInOrder p : activeOrder.getItems()) {
				p.setOrderNumber(orderNumber);
				if (!dbcontroller.saveItemInOrderToDB(p)) {
					return false;
				}
			}
			return true;
		}
		return false;
	}
}
