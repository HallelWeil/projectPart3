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

	private double avgOrders = 0;
	private double avgRev = 0;

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
		if ((month) % 3 == 0) {
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
		}
		for (Order order : allOrders) {
			ArrayList<Order> temp = sortedLists.get(order.getBranchName());
			temp.add(order);
		}
		// create all the monthly reports
		avgOrders = 0;
		avgRev = 0;
		for (String branch : branches) {
			revenueReports.add(createMonthlyRevenueReport(sortedLists.get(branch), branch));
			orderReports.add(createMonthlyOrdersReport(sortedLists.get(branch), branch));
		}
		// get the average
		if (branches.size() > 0) {
			avgOrders = roundDouble(avgOrders / branches.size());
			avgRev = roundDouble(avgRev / branches.size());
		}
		for (OrdersReport r : orderReports) {
			r.setAvarageMonthlyOrders(avgOrders);
		}
		for (RevenueReport r : revenueReports) {
			r.setAverageMonthlyRevenue(avgRev);
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
		ArrayList<Report> reportList = dbController.getAllReportsInTimePeriod(month - 2, month - 1, year);
		// create new arraylist for all the revenue reports(including the new ones)
		ArrayList<RevenueReport> tempRevenueReports = new ArrayList<RevenueReport>(this.revenueReports);
		// create new arraylist for all the order reports(including the new ones)
		ArrayList<OrdersReport> tempOrdersReports = new ArrayList<OrdersReport>(this.orderReports);
		// sort the report to their lists
		if (reportList != null) {
			for (Report report : reportList) {
				if (report.getType() == ReportType.MONTHLY_ORDERS_REPORT) {
					tempOrdersReports.add((OrdersReport) report);
				} else if (report.getType() == ReportType.MONTHLY_REVENU_EREPORT) {
					tempRevenueReports.add((RevenueReport) report);
				}
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
		QuarterlyRevenueReport newReport = new QuarterlyRevenueReport(month / 3, year);
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
			newReport.setAverageRevenuePerOrder(roundDouble(newReport.getTotalRevenue() / totalOrders));
		// save the newly created report
		quarterlyRevenueReport = newReport;
	}

	/**
	 * create the quarterly orders report
	 * 
	 * @param tempOrdersReports the list of all the orders reports for this quarter
	 */
	private void createQuarterlyOrdersReport(ArrayList<OrdersReport> tempOrdersReports) {
		QuarterlyOrdersReport newReport = new QuarterlyOrdersReport(month / 3, year);
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
		QuarterlySatisfactionReport newReport = new QuarterlySatisfactionReport(month / 3, year);
		// create the report
		// 1. count all the complaints from each month
		int[] months = new int[3];
		months[0] = dbController.countComplaints(month - 2, year);
		months[1] = dbController.countComplaints(month - 1, year);
		months[2] = dbController.countComplaints(month, year);
		newReport.setComplaintsPerMonth(months);
		quarterlySatisfactionReport = newReport;
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

	/**
	 * round a double up to 2 decimal places
	 * 
	 * @param num
	 * @return
	 */
	private double roundDouble(double num) {
		return Math.round(num * 100.0) / 100.0;
	}

	/**
	 * create monthly revenue report for the given branch based on the given orders
	 * list. 1. get the total revenue(sum of the orders prices) 2. get the average
	 * revenue per order(total revenue/number of orders) 3. get revenue per day 4.
	 * get most profitable item(the item who got the most profit)
	 * 
	 * @param orders
	 * @param branchName
	 * @return
	 */
	private RevenueReport createMonthlyRevenueReport(ArrayList<Order> orders, String branchName) {
		RevenueReport newRevenueReport = new RevenueReport(month, year, ReportType.MONTHLY_REVENU_EREPORT, branchName);
		// find the most popular item and the other details
		int counter = 0;
		HashMap<String, Double> itemsRevCounter = new HashMap<String, Double>();
		double revenue = 0;
		if (orders != null)
			for (Order order : orders) {
				order.setItems(dbController.getAllProductsInOrder(order.getOrderNumber()));
				if (order.getItems() != null)
					for (ProductInOrder item : order.getItems()) {
						addToRevCounter(itemsRevCounter, item.getName(), item.getAmount() * item.getPrice());
					}
				newRevenueReport.addOrderRevenuOnDay(order.getOrderDate().toLocalDateTime().getDayOfMonth(),
						order.getPrice());
				counter++;
				revenue += order.getPrice();
			}
		if (counter == 0)
			newRevenueReport.setAverageRevenuePerOrder(revenue);
		else
			newRevenueReport.setAverageRevenuePerOrder(roundDouble(revenue / counter));
		// save the values
		newRevenueReport.setTotalRevenue(roundDouble(revenue));
		newRevenueReport.setAverageMonthlyRevenue(0);
		avgRev += revenue;
		double maxRevenu = 0;
		String itemName = "";
		for (String tempName : itemsRevCounter.keySet()) {
			double tempRev = itemsRevCounter.get(tempName);
			if (tempRev > maxRevenu) {
				maxRevenu = tempRev;
				itemName = tempName;
			}
		}
		newRevenueReport.setMostProfitableItem(itemName);
		// save the new reports
		return newRevenueReport;
	}

	/**
	 * create monthly orders report, using the orders list: 1.get the total number
	 * of orders. 2. get the most popular item. 3. count items in orders for each
	 * category. 4. count orders per day 5. get the most popular item
	 * 
	 * @param orders
	 * @param branchName
	 * @return the new orders report
	 */
	private OrdersReport createMonthlyOrdersReport(ArrayList<Order> orders, String branchName) {
		OrdersReport newOrdersReport = new OrdersReport(month, year, ReportType.MONTHLY_ORDERS_REPORT, branchName);
		// find the most popular item and the other details
		int counter = 0;
		HashMap<String, Integer> itemsCounter = new HashMap<String, Integer>();
		if (orders != null)
			for (Order order : orders) {
				order.setItems(dbController.getAllProductsInOrder(order.getOrderNumber()));
				if (order.getItems() != null)
					for (ProductInOrder item : order.getItems()) {
						addToCounter(itemsCounter, item.getName(), item.getAmount());
						newOrdersReport.addOrderOnDay(order.getOrderDate().toLocalDateTime().getDayOfMonth());
						newOrdersReport.addToCategory(item.getCategory(), item.getAmount());
					}
				counter++;
				avgOrders++;
			}
		// save the values
		newOrdersReport.setAvarageMonthlyOrders(0);
		newOrdersReport.setTotalOrders(counter);
		int maxAmount = 0;
		String itemName = "";
		for (String tempName : itemsCounter.keySet()) {
			int temp = itemsCounter.get(tempName);
			if (temp > maxAmount) {
				maxAmount = temp;
				itemName = tempName;
			}
		}
		newOrdersReport.setMostPopularItem(itemName);
		// save the new reports
		return newOrdersReport;
	}
}
