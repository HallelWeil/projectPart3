package report;

import java.sql.Date;
import java.util.HashMap;

public class RevenueReport extends Report {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private double[] revenuePerDay;
	private double totalRevenue;
	private double avarageMonthlyRevenue;
	private double avarageRevenuePerOrder;
	private String mostProfitableItem;

	public RevenueReport(int month, int year, ReportType type, String branchName) {
		super(month, year, type, branchName);
		this.revenuePerDay = new double[31];
		for (int i = 0; i < 31; i++)
			revenuePerDay[i] = 0;
		totalRevenue = 0;
		avarageMonthlyRevenue = 0;
		avarageRevenuePerOrder = 0;
		mostProfitableItem = null;
	}

	public void addOrderRevenuOnDay(int day, double amount) {
		if (day > 31 || day < 1 || amount <= 0)
			return;
		revenuePerDay[day - 1] += amount;
	}

	public double getTotalRevenue() {
		return totalRevenue;
	}

	public void setTotalRevenue(double totalRevenue) {
		this.totalRevenue = totalRevenue;
	}

	public double getAvarageMonthlyRevenue() {
		return avarageMonthlyRevenue;
	}

	public void setAvarageMonthlyRevenue(double avarageMonthlyRevenue) {
		this.avarageMonthlyRevenue = avarageMonthlyRevenue;
	}

	public double getAvarageRevenuePerOrder() {
		return avarageRevenuePerOrder;
	}

	public void setAvarageRevenuePerOrder(double avarageRevenuePerOrder) {
		this.avarageRevenuePerOrder = avarageRevenuePerOrder;
	}

	public String getMostProfitableItem() {
		return mostProfitableItem;
	}

	public void setMostProfitableItem(String mostProfitableItem) {
		this.mostProfitableItem = mostProfitableItem;
	}

	public double[] getRevenuePerDay() {
		return revenuePerDay;
	}
	

}
