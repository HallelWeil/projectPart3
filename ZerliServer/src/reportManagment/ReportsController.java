package reportManagment;

import java.util.ArrayList;
import java.util.HashMap;

import database.DBController;
import order.Order;
import order.ProductInOrder;
import report.*;

/**
 * Manage creation of the monthly and quarterly reports.
 * 
 * @author halel
 *
 */
public class ReportsController {

	/**
	 * The database controller
	 */
	private DBController dbController = DBController.getInstance();
	/**
	 * save all the monthly order reports
	 */
	private ArrayList<OrdersReport> orderReports;
	/**
	 * save all the monthly revenue reports
	 */
	private ArrayList<RevenueReport> revenueReports;
	/**
	 * The quarterly orders report
	 */
	private QuarterlyOrdersReport quarterlyOrdersReport;
	/**
	 * The quarterly revenue report
	 */
	private QuarterlyRevenueReport quarterlyRevenueReport;
	/**
	 * The quarterly satisfaction report
	 */
	private QuarterlySatisfactionReport quarterlySatisfactionReport;
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
	 * Construct new report controller for the month+year
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

	/**
	 * Save all the reports the report controller created in CreateAll to the
	 * database
	 */
	public void saveAllReports() {
		for (Report report : orderReports) {
			try {
				dbController.saveReportToDB(report);
			} catch (Exception e) {
			}
		}
		for (Report report : revenueReports) {
			try {
				dbController.saveReportToDB(report);
			} catch (Exception e) {
			}
		}
		if (IsQuarterly) {
			try {
				dbController.saveReportToDB(quarterlyOrdersReport);
				dbController.saveReportToDB(quarterlyRevenueReport);
				dbController.saveReportToDB(quarterlySatisfactionReport);
			} catch (Exception e) {
			}
		}
	}

	/**
	 * Create all the reports, all the monthly reports and if its the end of a
	 * quarter create the quarterly reports
	 */
	public void createAllReports() {
		// get all the branches
		branches = dbController.getAllBranches();
		// get all the orders in the time period
		ArrayList<Order> allOrders = dbController.getAllOrdersForReport(month, year);// get all the orders in the
																						// "month" month
		// sort the orders by branch, for each branch save arraylist of the branch's
		// orders
		HashMap<String, ArrayList<Order>> sortedLists = new HashMap<String, ArrayList<Order>>();
		for (String branch : branches) {
			ArrayList<Order> temp = sortedLists.get(branch);
			if (temp == null) {
				temp = new ArrayList<Order>();
				sortedLists.put(branch, temp);
			}
			for (Order order : allOrders) {
				if (order.getBranchName().equals(branch)) {

					temp.add(order);
				}
			}
		}

		// create all the monthly reports
		for (

		String branch : branches) {
			createMonthlyReports(month, year, branch, sortedLists.get(branch));
		}
		// if needed create the quarterly reports
		if (IsQuarterly) {
			createQuarterlyReports();
		}
	}

	/**
	 * Create all the quarterly reports, get all the reports from the last 3 month.
	 * we just created the last month so we get the other 2 from the database and
	 * use the reports data for the creation of the orders report and the revenue
	 * report
	 */
	private void createQuarterlyReports() {
		// create quarterly reports
		// get all the revenue reports for the time period
		ArrayList<Report> reportList = null;// dbController.//
		// create new arraylist for all the revenue reports(including the new ones)
		ArrayList<RevenueReport> tempRevenueReports = new ArrayList<RevenueReport>(this.revenueReports);
		// create new arraylist for all the order reports(including the new ones)
		ArrayList<OrdersReport> tempOrdersReports = new ArrayList<OrdersReport>(this.orderReports);
		// sort the report to their lists
		for (Report report : reportList) {
			if (report.getType() == ReportType.MONTHLY_ORDERS_REPORT) {
				tempOrdersReports.add((OrdersReport) report);
			} else if (report.getType() == ReportType.MONTHLY_REVENU_EREPORT) {
				tempRevenueReports.add((RevenueReport) report);
			}
		}
		createQuarterlyOrdersReport(tempOrdersReports);
		// the revenue report needs the total orders number from the orders report
		createQuarterlyRevenueReport(tempRevenueReports, quarterlyOrdersReport.getTotalOrders());
		createQuarterlySatisfactionReport();
	}

	/**
	 * create the quarterly revenue report
	 * 
	 * @param tempRevenueReports the list of all the revenue reports for this
	 *                           quarter
	 * @param totalOrders        the total number of orders in the quarter
	 */
	private void createQuarterlyRevenueReport(ArrayList<RevenueReport> tempRevenueReports, int totalOrders) {
		QuarterlyRevenueReport newReport = new QuarterlyRevenueReport(month, year);
		// local variables
		int tempMonth;
		// for each report add the report details to the quarterly report
		for (RevenueReport report : tempRevenueReports) {
			tempMonth = report.getMonth();
			// get the revenue per day from the report, for each day of the month
			double[] revenuePerDay = report.getRevenuePerDay();
			for (int i = 0; i < revenuePerDay.length; i++) {
				newReport.addRevenuOnDay(tempMonth, i + 1, revenuePerDay[i]);
			}
			// add the report fields to the quarterly report
			newReport.addProfitableItem(report.getMostProfitableItem());
			newReport.addToTotalRevenue(report.getTotalRevenue());
			newReport.setMonthlyAvarageRevenu(tempMonth, report.getAverageMonthlyRevenue());
		}
		// if the total order is bigger than 0, get the average
		if (totalOrders != 0)
			newReport.setAverageRevenuePerOrder(newReport.getTotalRevenue() / totalOrders);
		// save the newly created report
		quarterlyRevenueReport = newReport;
	}

	/**
	 * create the quarterly orders report
	 * 
	 * @param tempOrdersReports the list of all the orders reports for this quarter
	 */
	private void createQuarterlyOrdersReport(ArrayList<OrdersReport> tempOrdersReports) {
		QuarterlyOrdersReport newReport = new QuarterlyOrdersReport(month, year);
		// local variables
		int tempMonth;
		// for each report add the report details to the quarterly report
		if (tempOrdersReports != null)
			for (OrdersReport report : tempOrdersReports) {
				tempMonth = report.getMonth();
				// get the orders per day from the report, for each day of the month
				int[] ordersPerDay = report.getOrdersPerDay();
				for (int i = 0; i < ordersPerDay.length; i++) {
					newReport.addOrdersOnDay(i + 1, tempMonth, ordersPerDay[i]);
				}
				// get the orders per category, for each category
				HashMap<String, Integer> ordersPerCategory = report.getOrdersPerCategory();
				if (ordersPerCategory.keySet() != null)
					for (String category : ordersPerCategory.keySet()) {
						newReport.addToCategory(category, ordersPerCategory.get(category));
					}
				// get the total orders
				newReport.addToTotalOrders(report.getTotalOrders());
				// get the most popular item
				newReport.addPopularItem(report.getMostPopularItem());
				// get the monthly average
				newReport.setAvarageMonthlyOrders(report.getAvarageMonthlyOrders(), tempMonth);
			}
		// save the newly created report
		quarterlyOrdersReport = newReport;
	}

	/**
	 * create the quarterly satisfaction report
	 */
	private void createQuarterlySatisfactionReport() {
		QuarterlySatisfactionReport newReport = new QuarterlySatisfactionReport(month, year);
		// create the report
		quarterlySatisfactionReport = newReport;
	}

	/**
	 * Create the monthly reports(revenue and orders) simultaneously(to go over the
	 * orders only once)
	 * 
	 * @param month      the reports month
	 * @param year       the reports year
	 * @param branchName the report branch
	 * @param orders     list of the orders in this month for this branch
	 */
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
		if (orders != null)
			for (Order order : orders) {
				order.setItems(dbController.getAllProductsInOrder(order.getOrderNumber()));
				if (order.getItems() != null)
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
			newRevenueReport.setAverageRevenuePerOrder(revenue);
		else
			newRevenueReport.setAverageRevenuePerOrder(revenue / counter);
		// save the values
		newRevenueReport.setTotalRevenue(revenue);
		newRevenueReport.setAverageMonthlyRevenue(avgRevenue);
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

	/**
	 * Add item to the item counter( by item name) for int
	 * 
	 * @param itemsCounter
	 * @param name
	 * @param amount
	 */
	private void addToCounter(HashMap<String, Integer> itemsCounter, String name, int amount) {
		Integer number = itemsCounter.get(name);
		if (number == null) {
			number = amount;
		} else {
			number += amount;
		}
		itemsCounter.put(name, number);
	}

	/**
	 * Add item to the item counter( by item name) for double
	 * 
	 * @param itemsCounter
	 * @param name
	 * @param amount
	 */
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
