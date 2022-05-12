package database;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLData;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.TimeZone;
import java.util.spi.TimeZoneNameProvider;
import com.mysql.cj.jdbc.Blob;
import catalog.Item;
import catalog.Product;
import common.Status;
import complaint.Complaint;
import order.Order;
import order.OrderStatus;
import order.ProductInOrder;
import report.Report;
import survey.Survey;
import user.UserStatus;
import user.UserType;

/**
 * The database controller, manage connection to database using DBBoundary,
 * create queries and manage the result
 *
 */
public class DBController {
	/**
	 * The database name
	 */
	private String DBname;
	/**
	 * The database boundary
	 */
	private DBBoundry dbBoundry;
	/**
	 * class to convert ResultSet to objects arrays
	 */
	DBObject objectCreator;

	public DBController() {
		dbBoundry = new DBBoundry();
		objectCreator = new DBObject();
		DBname = "";
	}

	/**
	 * Connect to the Driver and on success to the database
	 * 
	 * @param DBName : the database name('root' for example)
	 * @param DBUser : the database username
	 * @param DBPass : the database password
	 * @throws Exception with relevant message
	 */
	public void connectToDB(String DBName, String DBUser, String DBPass) throws Exception {
		this.DBname = DBName;
		if (!dbBoundry.ConnectDriver()) {
			throw new Exception("Error - can't connect to JDBC driver");
		}
		try {
			dbBoundry.ConnectDB(DBName, DBUser, DBPass);
		} catch (Exception e) {
			dbBoundry.disconnectDB();// disconnect the driver
			throw new Exception("Error - can't connect to the database");
		}
	}

	/**
	 * Disconnect from the database
	 */
	public void disConnectFromDB() {
		dbBoundry.disconnectDB();
	}

	/**
	 * Get order from the database
	 * 
	 * @param orderNum the order number
	 * @return the order, null if no such order exist
	 */
	public Order getOrdrFromDB(int orderNum) {
		// create the query
		String s = "SELECT * FROM " + DBname + ".order WHERE (orderNumber = " + orderNum + " );";
		// get the result
		ResultSet res = (ResultSet) dbBoundry.sendQueary(s);
		// get the returned values
		ArrayList<Order> orders = objectCreator.orderDB(res);
		return orders.size() > 0 ? orders.get(0) : null;
	}

	/**
	 * Update existing order color and date
	 * 
	 * @param order with updated color and date
	 * @return true if the order was updated
	 */

	public boolean updateOrder(Status status) {
		// create the query
		String s = "UPDATE  " + DBname + ".order  SET status = '" + status.toString() + "' ;";
		// send query + get result
		boolean res = (boolean) dbBoundry.sendQueary(s);
		return res;
	}

	public int saveOrderToDB(Order order) {
		String s = "INSERT INTO " + DBname + "order VALUES ('" + null + "','" + order.getOrderDate()
				+ "','" + order.getArrivalDate() + "','" + order.isHomeDelivery() + "','" + order.getBranchName()
				+ order.getPrice() + "','" + order.getUsername() + "','" + order.getPersonalLetter() + "','"
				+ order.getOrderStatus().ordinal() + "','" + order.getOrderData() + "');";
		boolean res = (boolean) dbBoundry.sendQueary(s);
		s = "select last_insert_id() as last_id from complaint";
		ResultSet idRes = (ResultSet) dbBoundry.sendQueary(s);
		int lastID = -1;
		if (idRes.next()) {
			lastID = idRes.getInt("last_id");
		}
		return lastID;
	}

	public boolean deleteOrder(int orderNum) {
		String s = "DELETE FROM " + DBname + ".orders WHERE (orderNumber = " + orderNum + " );";
		boolean res = (boolean) dbBoundry.sendQueary(s);
		return res;
	}

	public java.sql.Blob getReportFromDB(Report report) {
		// create the query
		java.sql.Blob data;
		String s = "SELECT * FROM " + DBname + ".report WHERE (branchName = " + report.getBranchName() + " AND "
				+ "reportType = " + report.getType() + " AND reportDate = " + report.getStartDate() + " );";
		// get the result
		ResultSet res = (ResultSet) dbBoundry.sendQueary(s);
		// get the returned values
		try {
			if (res.next()) {
				data = res.getBlob("reportData");
			} else {
				return null;
			}
		} catch (Exception e) {
			return null;
		}
		return data;
	}

	public java.sql.Blob saveReportToDB(Report report) {
		// create the query
		java.sql.Blob data;
		String s = "INSERT INTO " + DBname + ".report VALUES ('" + report.getBranchName() + "','"
				+ report.getStartDate() + "','" + report.getType() + "','" + report + "','" // to do blob
				+ order.getOrderData() + "');";
		// get the result
		ResultSet res = (ResultSet) dbBoundry.sendQueary(s);
		// get the returned values
		try {
			if (res.next()) {
				data = res.getBlob("reportData");
			} else {
				return null;
			}
		} catch (Exception e) {
			return null;
		}
		return data;
	}

	public boolean saveItemInOrderToDB(ProductInOrder product) {
		// create the query
		String s = "INSERT INTO " + DBname + ".itemInOrder VALUES ('" + product.getOrderNumber() + "','"
				+ product.getName() + "','" + " " + "','" + product.getPrice() + "','" + product.getAmount() + " ');";
		boolean res = (boolean) dbBoundry.sendQueary(s);
		return res;
	}

	public ArrayList<String> getItemInOrderFromDB(int orderID, String itemName) {
		// create the query
		ArrayList<String> item = new ArrayList<>();
		String s = "SELECT * FROM " + DBname + ".iteminorder WHERE (orderID = " + orderID + " AND itemName = "
				+ itemName + " );";
		// get the result
		ResultSet res = (ResultSet) dbBoundry.sendQueary(s);
		// get the returned values
		try {
			if (res.next()) {
				item.add(Integer.toString(res.getInt("orderID")));
				item.add(res.getString("itemName"));
				item.add(Double.toString(res.getDouble("itemPrice")));
				item.add(Integer.toString(res.getInt("amount")));
			} else {
				return null;
			}
		} catch (Exception e) {
			return null;
		}
		return item;
	}

	public boolean ConnectUser(String userName, String password) {
		// create the query
		boolean res = false;
		String s = "SELECT * FROM " + DBname + ".users WHERE (userName = " + userName + " );";
		// get the result
		ResultSet user = (ResultSet) dbBoundry.sendQueary(s);
		// get the returned values
		try {
			if (user.next() && user.getString("password") == password) {
				s = "UPDATE  " + DBname + ".users  SET connected = true WHERE (userName = " + userName + ");";
				res = (boolean) dbBoundry.sendQueary(s);
			} else {
				return false;
			}
		} catch (Exception e) {
			return false;
		}
		return res;
	}

	public boolean DisconnectUser(String userName) {
		boolean res = false;
		try {
			String s = "UPDATE  " + DBname + ".users  SET connected = false WHERE (userName = " + userName + ");";
			res = (boolean) dbBoundry.sendQueary(s);
		} catch (Exception e) {
		}
		return res;
	}

	public boolean updateUser(String userName, UserType type, UserStatus status) {
		// create the query
		String s = "UPDATE  " + DBname + ".users  SET userName = '" + userName + "', userType = '" + type.ordinal()
				+ "', status = '" + status.ordinal() + "' WHERE username = " + userName + " ;";
		// send query + get result
		boolean res = (boolean) dbBoundry.sendQueary(s);
		return res;
	}

	public ArrayList<Order> getAllOrdersInBranch(String branchName, String customerID) {
		// create the query
		String s = "SELECT * FROM " + DBname + ".order WHERE (branchName = " + branchName + " );";
		// get the result
		ResultSet res = (ResultSet) dbBoundry.sendQueary(s);
		// get the returned values
		ArrayList<Order> orders = objectCreator.orderDB(res);
		return orders;
	}

	public ArrayList<Order> getAllOrdersOfCustomer(String branchName, String customerID) {
		// create the query
		String s = "SELECT * FROM " + DBname + ".order WHERE (customerID = " + customerID + " );";
		// get the result
		ResultSet res = (ResultSet) dbBoundry.sendQueary(s);
		// get the returned values
		ArrayList<Order> orders = objectCreator.orderDB(res);
		return orders;
	}

	public Survey getSurvey(int surveyNumber) {
		// create the query
		String s = "SELECT * FROM " + DBname + ".survey WHERE (surveyNumber = " + surveyNumber + " );";
		// get the result
		ResultSet res = (ResultSet) dbBoundry.sendQueary(s);
		// get the returned values
		ArrayList<Survey> surveys = objectCreator.surveyDB(res);
		return surveys.size() > 0 ? surveys.get(0) : null;
	}

	public ArrayList<Survey> getAllSurvey() {
		// create the query
		String s = "SELECT * FROM " + DBname + ".survey;";
		// get the result
		ResultSet res = (ResultSet) dbBoundry.sendQueary(s);
		// get the returned values
		ArrayList<Survey> surveys = objectCreator.surveyDB(res);
		return surveys;
	}

	public boolean addSurveyAnswers(int[] answers, int surveyNumber) {
		// create the query
		String s = "UPDATE  " + DBname + ".survey  SET a1 = '" + answers[0] + "', a2 = '" + answers[1] + "', a3 = '"
				+ answers[2] + "', a4 = '" + answers[3] + "', a5 = '" + answers[4] + "', a6 = '" + answers[5]
				+ "' participants = participants + 1 WHERE surveyNumber = " + surveyNumber + ";";
		// send query + get result
		boolean res = (boolean) dbBoundry.sendQueary(s);
		return res;
	}

	public int CreateSurvey(Survey survey) throws SQLException {
		int[] answers = new int[6]; // need getter from survey
		String s = "INSERT INTO " + DBname + ".survey (q1,q2,q3,q4,q5,q6, participants) VALUES ('" + answers[0] + "','"
				+ answers[1] + "','" + answers[2] + "','" + answers[3] + "','" + answers[4] + "','" + answers[5] + "','"
				+ 0 + " ');";
		boolean res = (boolean) dbBoundry.sendQueary(s);
		s = "select last_insert_id() as last_id from survey";
		ResultSet idRes = (ResultSet) dbBoundry.sendQueary(s);
		int lastID = -1;
		if (idRes.next()) {
			lastID = idRes.getInt("last_id");
		}
		return lastID;
	}

	public int CreateComplaint(Complaint complaint) throws SQLException {
		String s = "INSERT INTO " + DBname + ".survey  VALUES ('" + null + "','" + complaint.getResposibleEmployee()
				+ "','" + complaint.getComplaint() + "','" + complaint.getAnswer() + "','" + complaint.getCompensation()
				+ "','" + complaint.getStatus() + "','" + complaint.getCustomerID() + "');";
		boolean res = (boolean) dbBoundry.sendQueary(s);
		s = "select last_insert_id() as last_id from complaint";
		ResultSet idRes = (ResultSet) dbBoundry.sendQueary(s);
		int lastID = -1;
		if (idRes.next()) {
			lastID = idRes.getInt("last_id");
		}
		return lastID;
	}

	public boolean updateComplaint(String answer, int complaintNumber, Status status) throws SQLException {
		String s = "UPDATE  " + DBname + ".complaint  SET answer = '" + answer + "', status = '" + status.toString()
				+ "' WHERE complaintNumber = " + complaintNumber + ";";
		boolean res = (boolean) dbBoundry.sendQueary(s);
		return res;
	}

	public boolean addSurveyResult(int surveyNumber, Blob surveyResult) {
		// create the query
		String s = "UPDATE  " + DBname + ".survey  SET surveyResult = '" + surveyResult + "' WHERE surveyNumber = "
				+ surveyNumber + ";";
		// send query + get result
		boolean res = (boolean) dbBoundry.sendQueary(s);
		return res;
	}

	public ArrayList<Complaint> getAllComplaints(int employeeID) {
		String s = "SELECT * FROM " + DBname + ".complaint WHERE employeeID = '" + employeeID + "' ;";
		ResultSet res = (ResultSet) dbBoundry.sendQueary(s);
		// get the returned values
		ArrayList<Complaint> complaints = objectCreator.complaintDB(res);
		return complaints;
	}

	public boolean updateProduct(Product product) {
		String s = "UPDATE  " + DBname + ".complaint  SET productID = '" + null  + "', name = '" + product.getName() + "', price = '"
				+ product.getPrice() + "', description = '" + product.getDescription() + "', colors = '" + product.getColors()
				+ "' image = " + product.getImage() + "' WHERE productID = " + product.getID() + ";";
		boolean res = (boolean) dbBoundry.sendQueary(s);
		return res;
	}
	
	ArrayList<Product> getCatalogCategory(String category){
		// create the query
		String s = "SELECT * FROM " + DBname + ".product WHERE (category = " + category + " );"; //category field is needed
		// get the result
		ResultSet res = (ResultSet) dbBoundry.sendQueary(s);
		// get the returned values
		ArrayList<Product> products = objectCreator.productDB(res);
		return products;
	}
	
	boolean savePromotion(Promotion promotion) {
		// create the query
		String s = "INSERT INTO " + DBname + ".promotion VALUES ('" + product.getOrderNumber() + "','"
				+ product.getName() + "','" + " " + "','" + product.getPrice() + "','" + product.getAmount() + " ');";
		boolean res = (boolean) dbBoundry.sendQueary(s);
		return res;
		
	}
	
	
	
	

}
