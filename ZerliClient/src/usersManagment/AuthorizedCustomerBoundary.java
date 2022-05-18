package usersManagment;

import java.util.ArrayList;

import order.Order;

public class AuthorizedCustomerBoundary extends UserBoundary {

	private AuthorizedCustomerController AuthorizedCustomerCon;

	public AuthorizedCustomerBoundary() {

		this.AuthorizedCustomerCon = new AuthorizedCustomerController();
	}

	public ArrayList<Order> getAllOrders() {
		return AuthorizedCustomerCon.getAllOrders();

	}

	/**
	 * create the catalog boundary
	 */
	public void requestBrowseTheCatalog() {

	}

	/**
	 * 
	 * @param order
	 */
	public boolean requestOrderCancellation(Order order) {
		return AuthorizedCustomerCon.requestOrderCancellation(order);

	}
}
