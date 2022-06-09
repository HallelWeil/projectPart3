package revenueReportUT;

import static org.junit.jupiter.api.Assertions.*;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import UnitTestinData.DBINFO;
import database.DBController;
import order.Order;
import report.ReportType;
import report.RevenueReport;
import reportManagment.ReportsController;

class createMonthlyRevenueReportUT {

	// tested with the actual database, against saved test data:
	// in the database we have 3 orders for 1/1/2000 for the TestBranch
	// order1: number -1, price 200 items: p1: cost 100 amount 2
	// order2: number -2, price 200 items: p2 cost 50 amount 3
	// order3: number -3, price 200 items: none
	//

	private ReportsController reportsController;
	private int month = 1;
	private int year = 2000;

	/**
	 * method to get access to the private method
	 */
	private Method method;

	private ArrayList<Order> orders;

	@BeforeEach
	void setUp() throws Exception {
		DBController.getInstance().connectToDB(DBINFO.DBname, DBINFO.DBUser, DBINFO.DBPassword);
		reportsController = new ReportsController(month, year);
		orders = new ArrayList<Order>();
		method = ReportsController.class.getDeclaredMethod("createMonthlyRevenueReport", orders.getClass(),
				String.class);
		method.setAccessible(true);
	}

	// checking functionality - create report using the database data
	// input data: the database existing data(as described above)
	// expected result: correct revenue report
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
			fail("Sholdnt throw exception");
		}
		assertEquals(600, result.getTotalRevenue());
		assertEquals("testBranch", result.getBranchName());
		assertEquals(month, result.getMonth());
		assertEquals(year, result.getYear());
		assertEquals("p1", result.getMostProfitableItem());
		assertEquals(ReportType.MONTHLY_REVENU_EREPORT, result.getType());
		assertEquals(600, result.getRevenuePerDay()[0]);
	}

	// checking functionality - null input for the orders
	// input data: null
	// expected result: empty revenue report
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
			fail("Sholdnt throw exception");
		}
		assertEquals(0, result.getTotalRevenue());
		assertEquals("testBranch", result.getBranchName());
		assertEquals(month, result.getMonth());
		assertEquals(year, result.getYear());
		assertEquals("", result.getMostProfitableItem());
		assertEquals(ReportType.MONTHLY_REVENU_EREPORT, result.getType());
		assertEquals(0, result.getRevenuePerDay()[0]);
	}

	// checking functionality - null input for the branch
	// input data: null,empty orders list
	// expected result: empty revenue report
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
			fail("Sholdnt throw exception");
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
