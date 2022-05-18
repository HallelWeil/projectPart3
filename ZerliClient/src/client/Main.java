package client;

import order.Order;
import order.OrderStatus;
import report.Report;
import report.ReportType;
import user.*;
import user.UserType;
import usersManagment.BranchManagerBoundary;
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
				break;
			default:
				break;
			}

		} else {
			System.out.println("cant connect");
		}

	}

}
