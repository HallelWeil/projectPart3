package revenueReportUT;

import static org.junit.jupiter.api.Assertions.*;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import database.DBController;
import order.Order;
import order.ProductInOrder;
import report.ReportType;
import report.RevenueReport;
import reportManagment.ReportsController;

class createMonthlyRevenueReportUT {

	// in the database we have 3 orders for 1/1/2000 for the TestBranch
	// order1: number -1, price 200 items: p1: cost 100 amount 2
	// order2: number -2, price 200 items: p2 cost 50 amount 4
	// order3: number -3, price 200 items: none
	//

	private ReportsController reportsController;
	private int month = 1;
	private int year = 2000;
	private String DBname = "zelisystem";
	private String DBUser = "root";
	private String DBPassword = "Aa123456";

	/**
	 * method to get access to the private method
	 */
	private Method method;

	private ArrayList<Order> orders;

	@BeforeEach
	void setUp() throws Exception {
		DBController.getInstance().connectToDB(DBname, DBUser, DBPassword);
		reportsController = new ReportsController(month, year);
		orders = DBController.getInstance().getAllOrdersForReport(month, year);
		method = ReportsController.class.getDeclaredMethod("createMonthlyRevenueReport", orders.getClass(),
				String.class);
		method.setAccessible(true);
	}

	@Test
	void create_revenue_report() {
		orders = DBController.getInstance().getAllOrdersForReport(month, year);
		RevenueReport result = null;
		try {
			result = (RevenueReport) method.invoke(reportsController, orders, "testBranch");
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			fail("Not yet implemented");
		}
		assertEquals(600, result.getTotalRevenue());
		assertEquals("testBranch", result.getBranchName());
		assertEquals(month, result.getMonth());
		assertEquals(year, result.getYear());
		assertEquals("p1", result.getMostProfitableItem());
		assertEquals(ReportType.MONTHLY_REVENU_EREPORT, result.getType());
		assertEquals(600, result.getRevenuePerDay()[0]);
	}

	@Test
	void create_revenue_report_null_input_orders() {
		orders = null;
		RevenueReport result = null;
		try {
			result = (RevenueReport) method.invoke(reportsController, orders, "testBranch");
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			fail("Not yet implemented");
		}
		assertEquals(0, result.getTotalRevenue());
		assertEquals("testBranch", result.getBranchName());
		assertEquals(month, result.getMonth());
		assertEquals(year, result.getYear());
		assertEquals("", result.getMostProfitableItem());
		assertEquals(ReportType.MONTHLY_REVENU_EREPORT, result.getType());
		assertEquals(0, result.getRevenuePerDay()[0]);
	}

	@Test
	void create_revenue_report_null_input_branch() {
		orders = new ArrayList<>();
		RevenueReport result = null;
		try {
			result = (RevenueReport) method.invoke(reportsController, orders, null);
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			fail("Not yet implemented");
		}
		assertEquals(0, result.getTotalRevenue());
		assertEquals(null, result.getBranchName());
		assertEquals(month, result.getMonth());
		assertEquals(year, result.getYear());
		assertEquals("", result.getMostProfitableItem());
		assertEquals(ReportType.MONTHLY_REVENU_EREPORT, result.getType());
		assertEquals(0, result.getRevenuePerDay()[0]);
	}

}
