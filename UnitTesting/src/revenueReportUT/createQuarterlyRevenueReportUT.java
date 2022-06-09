package revenueReportUT;

import static org.junit.jupiter.api.Assertions.*;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import report.*;
import reportManagment.ReportsController;

class createQuarterlyRevenueReportUT {

	private ReportsController reportsController;
	// end of the quarter
	private int month = 3;
	private int year = 2000;

	/**
	 * method to get access to the private method
	 */
	private Method method;

	private ArrayList<RevenueReport> reports;

	@BeforeEach
	void setUp() throws Exception {
		reports = new ArrayList<RevenueReport>();
		reportsController = new ReportsController(month, year);
		method = ReportsController.class.getDeclaredMethod("createQuarterlyRevenueReport", reports.getClass(),
				int.class);
		method.setAccessible(true);

	}

	// checking functionality - creating new quarterly revenue report
	// input data: 2 revenue reports(1000 revenue for each, on the first day)
	// expected result: quarterly report with the correct data
	@Test
	void QuarterlyRevenueReport_Creating_report_using_correct_input() {
		// first we get 2 reports for the input
		RevenueReport report1 = new RevenueReport(1, 2000, ReportType.MONTHLY_REVENU_EREPORT, "testBranch");
		report1.setMostProfitableItem("p1");
		report1.setTotalRevenue(1000);
		report1.addOrderRevenuOnDay(1, 1000);
		report1.setAverageMonthlyRevenue(1000);
		reports.add(report1);
		RevenueReport report2 = new RevenueReport(2, 2000, ReportType.MONTHLY_REVENU_EREPORT, "testBranch");
		report2.setMostProfitableItem("p2");
		report2.setTotalRevenue(1000);
		report2.addOrderRevenuOnDay(1, 1000);
		report2.setAverageMonthlyRevenue(1000);
		reports.add(report2);
		// now lets create the revenue report
		QuarterlyRevenueReport result = null;
		try {
			result = (QuarterlyRevenueReport) method.invoke(reportsController, reports, 100);
		} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
			fail("Sholdnt throw exception");
		}
		assertEquals(1000 + 1000, result.getTotalRevenue());
		assertEquals(1000, result.getAvarageMonthlyRevenue()[0]);
		assertEquals(1000, result.getAvarageMonthlyRevenue()[1]);
		assertTrue(result.getMostProfitableItem().contains("p1"));
		assertTrue(result.getMostProfitableItem().contains("p2"));
		assertEquals(1000, result.getRevenuePerDay()[0][0]);
		assertEquals(1000, result.getRevenuePerDay()[1][0]);
		assertEquals(2000 / 100, result.getAverageRevenuePerOrder());
	}

	// checking functionality - null input
	// input data: null
	// expected result: quarterly report with 0 as data(all fields)
	@Test
	void QuarterlyRevenueReport_Creating_report_using_null_input() {
		reports = null;
		// now lets create the revenue report
		QuarterlyRevenueReport result = null;
		try {
			result = (QuarterlyRevenueReport) method.invoke(reportsController, reports, 100);
		} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
			fail("Sholdnt throw exception");
		}
		assertEquals(0, result.getTotalRevenue());
		assertEquals(0, result.getAvarageMonthlyRevenue()[0]);
		assertEquals(0, result.getAvarageMonthlyRevenue()[1]);
		assertTrue(result.getMostProfitableItem().isEmpty());
		assertEquals(0, result.getRevenuePerDay()[0][0]);
		assertEquals(0, result.getRevenuePerDay()[1][0]);
	}

	// checking functionality - empty input(empty arraylist)
	// input data: empty array list, 0 orders
	// expected result: quarterly report with 0 as data(all fields)
	@Test
	void QuarterlyRevenueReport_Creating_report_using_empty_input() {
		reports = new ArrayList<>();
		// now lets create the revenue report
		QuarterlyRevenueReport result = null;
		try {
			result = (QuarterlyRevenueReport) method.invoke(reportsController, reports, 0);
		} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
			fail("Sholdnt throw exception");
		}
		assertEquals(0, result.getTotalRevenue());
		assertEquals(0, result.getAvarageMonthlyRevenue()[0]);
		assertEquals(0, result.getAvarageMonthlyRevenue()[1]);
		assertTrue(result.getMostProfitableItem().isEmpty());
		assertEquals(0, result.getRevenuePerDay()[0][0]);
		assertEquals(0, result.getRevenuePerDay()[1][0]);
	}

}
