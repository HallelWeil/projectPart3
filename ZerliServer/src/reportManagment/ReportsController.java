package reportManagment;

import java.util.ArrayList;
import java.util.HashMap;

import order.Order;
import order.ProductInOrder;
import report.*;
import report.ReportType;

/**
 * manage creation of the monthly and quarterly reports
 * 
 * @author halel
 *
 */
public class ReportsController {

	/**
	 * save all the monthly order reports
	 */
	private ArrayList<OrdersReport> orderReports;
	/**
	 * save all the monthly revenue reports
	 */
	private ArrayList<RevenueReport> revenueReports;
	/**
	 * if its the end of a quarter, create quarterly reports
	 */
	private boolean IsQuarterly;
	/**
	 * the list of the branches
	 */
	private ArrayList<String> branches;
	/**
	 * the month
	 */
	private int month;
	/**
	 * the year
	 */
	private int year;

	/**
	 * 
	 * @param month
	 * @param year
	 */

	public ReportsController(int month, int year) {
		this.year = year;
		this.month = month;
		branches = null;// get all the branches names from the dataBase
		// quarter end in the months: 3, 6, 9, 12, all the months divided by 3
		if (month % 3 == 0) {
			IsQuarterly = true;
		}
		orderReports = new ArrayList<OrdersReport>();
		revenueReports = new ArrayList<RevenueReport>();
	}

	public void createAllReports() {
		// get all the orders in the time period
		ArrayList<Order> allOrders = null;// get all the orders in the "month" month
		// sort the orders by branch
		HashMap<String, ArrayList<Order>> sortedLists = new HashMap<String, ArrayList<Order>>();
		for (String branch : branches) {
			for (Order order : allOrders) {
				if (order.getBranchName().equals(branch)) {
					ArrayList<Order> temp = sortedLists.get(branch);
					if (temp == null) {
						temp = new ArrayList<Order>();
						temp.add(order);
						sortedLists.put(branch, temp);
					} else {
						temp.add(order);
					}
				}
			}
		}
		// create all the monthly reports
		for (String branch : branches) {
			createMonthlyReports(month, year, branch, sortedLists.get(branch));
		}
		// if needed create the quarterly reports
		if (IsQuarterly) {
			// create quarterly reports
			createQuarterlyReports();
		}
		// save the reports
		// orderReports;
		// revenueReports;

	}

	
	private void createQuarterlyReports() {
		
	}
	
	
	
	@SuppressWarnings("unused")
	private void createMonthlyReports(int month, int year, String branchName, ArrayList<Order> orders) {
		OrdersReport newOrdersReport = new OrdersReport(month, year, ReportType.MONTHLY_ORDERS_REPORT, branchName);
		RevenueReport newRevenueReport = new RevenueReport(month, year, ReportType.MONTHLY_REVENU_EREPORT, branchName);
		// get the average from the db
		double avgOrders = 0;// get the average orders in all the orders reports
		double avgRevenue = 0;// get the average revenue
		// find the most popular item and the other details
		int counter = 0;
		HashMap<String, Integer> itemsCounter = new HashMap<String, Integer>();
		HashMap<String, Double> itemsRevCounter = new HashMap<String, Double>();
		double revenue = 0;
		for (Order order : orders) {
			for (ProductInOrder item : order.getItems()) {
				addToCounter(itemsCounter, item.getName(), item.getAmount());
				addToRevCounter(itemsRevCounter, item.getName(), item.getAmount() * item.getPrice());
				newOrdersReport.addOrderOnDay(order.getOrderDate().getDay());
				newOrdersReport.addToCategory(item.getCategory(), item.getAmount());
				newRevenueReport.addOrderRevenuOnDay(order.getOrderDate().getDay(), order.getPrice());
				counter++;
				revenue += order.getPrice();
			}
		}
		if (counter == 0)
			newRevenueReport.setAvarageRevenuePerOrder(revenue);
		else
			newRevenueReport.setAvarageRevenuePerOrder(revenue / counter);
		newRevenueReport.setTotalRevenue(revenue);
		newRevenueReport.setAvarageMonthlyRevenue(avgRevenue);
		newOrdersReport.setAvarageMonthlyOrders(avgOrders);
		newOrdersReport.setTotalOrders(counter);
		int maxAmount = 0;
		double maxRevenu = 0;
		String name1 = "", name2 = "";
		for (String s : itemsCounter.keySet()) {
			int temp = itemsCounter.get(s);
			double tempRev = itemsRevCounter.get(s);
			if (temp > maxAmount) {
				maxAmount = temp;
				name1 = s;
			}
			if (tempRev > maxRevenu) {
				maxRevenu = tempRev;
				name2 = s;
			}
		}
		newOrdersReport.setMostPopularItem(name1);
		newRevenueReport.setMostProfitableItem(name2);
		// save the new reports
		orderReports.add(newOrdersReport);
		revenueReports.add(newRevenueReport);
	}

	private void addToCounter(HashMap<String, Integer> itemsCounter, String name, int amount) {
		Integer number = itemsCounter.get(name);
		if (number == null) {
			number = amount;
		} else {
			number += amount;
		}
		itemsCounter.put(name, number);
	}

	private void addToRevCounter(HashMap<String, Double> itemsCounter, String name, double amount) {
		Double number = itemsCounter.get(name);
		if (number == null) {
			number = amount;
		} else {
			number += amount;
		}
		itemsCounter.put(name, number);
	}
}
