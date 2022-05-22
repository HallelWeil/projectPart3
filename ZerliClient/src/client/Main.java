package client;

import java.sql.Timestamp;
import java.util.ArrayList;

import catalog.Product;
import order.Order;
import report.Report;
import report.ReportType;
import shop.CartController;
import shop.CatalogController;
import user.*;
import usersManagment.AuthorizedCustomerBoundary;
import usersManagment.CEOBoundary;
import usersManagment.UserBoundary;

public class Main {

	public static void main(String[] args) {
		ClientBoundary clientBoundary = new ClientBoundary();
		User user = new User();
		try {
			clientBoundary.connect("localhost", 5555);
		} catch (Exception e) {
			System.out.println("Cant connect to server");
			e.printStackTrace();
		}
		UserBoundary loginuser = new UserBoundary();
		Boolean connected = loginuser.requestLogin("Ronen", "Zeyan");
		if (connected) {
			System.out.println("Connected!");
			user = UserBoundary.getCurrentUser();
			System.out.println("hello " + user.getFirstName() + " " + user.getLastName());
			switch (user.getUserType()) {
			case CEO:
				CEOBoundary newCEOBoundary = new CEOBoundary();
				Report report = newCEOBoundary.requestViewReport(ReportType.MONTHLY_ORDERS_REPORT, 6, 2000,
						"mainBranch");
				if (report == null)
					System.out.println("no report");
				else
					System.out.println(report.toString());
				System.out.println(report.getBranchName() + report.getMonth() + report.getYear() + report.getType());
				loginuser.requestLogOut();
				break;
			default:
				break;
			}

		} else {
			System.out.println("cant connect");
		}
		// connect to AuthorizedCustomerBoundary
		connected = loginuser.requestLogin("Or", "Balmas");
		AuthorizedCustomerBoundary authorizedCustomerBoundary = new AuthorizedCustomerBoundary();
		CatalogController catalogController = new CatalogController();
		ArrayList<Product> products = catalogController.chooseCategory("test");
		for (Product p : products) {
			System.out.println(p.getName());
		}
		CartController cartController = new CartController();
		//cartController.addToCart(products.get(0));
		cartController.addGreetingCard("hello");
		cartController.setArrivelOrPickupDateAndTime(new Timestamp(System.currentTimeMillis()+4000000));
		System.out.println("price = " + cartController.calculatePrice());
		Order order = cartController.placeOrder();
		cartController.payForOrder();
		authorizedCustomerBoundary.getAllOrders();

		authorizedCustomerBoundary.getAllOrders();

		loginuser.requestLogOut();
	}

}
