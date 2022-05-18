package database;

import java.sql.ResultSet;
import java.util.ArrayList;
import com.mysql.cj.jdbc.Blob;
import catalog.Product;
import common.Status;
import complaint.Complaint;
import order.Order;
import order.ProductInOrder;
import promotion.Promotion;
import report.Report;
import survey.Survey;
import user.User;
import user.UserStatus;
import user.UserType;

/**
 * The database controller, manage connection to database using DBBoundary,
 * create queries and manage the result
 *
 */
public class DBController {

	private static DBController singelton = null;

	public static DBController getInstance() {
		if (singelton == null)
			singelton = new DBController();

		return singelton;
	}

	/**
	 * The database name
	 */
	private String DBname;
	/**
	 * The database boundary
	 */
	private DBBoundry dbBoundry;
	/**
	 * class to handle ResultSet
	 */
	DBObjectsManager objectManager;

	private DBController() {
		dbBoundry = new DBBoundry();
		objectManager = new DBObjectsManager();
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
		ArrayList<Order> orders = objectManager.orderDB(res);
		return orders.size() > 0 ? orders.get(0) : null;
	}

	/**
	 * Update existing order color and date
	 * 
	 * @param order with updated color and date
	 * @return true if the order was updated
	 */

	public boolean updateOrder(Order order) {
		// create the query
		String s = "UPDATE  " + DBname + ".order  SET status = '" + order.getOrderStatus().toString() + "' ;";
		// send query + get result
		boolean res = (boolean) dbBoundry.sendQueary(s);
		return res;
	}

	public boolean saveOrderToDB(Order order) {
		// LocalDateTime now = LocalDateTime.now();
		// Timestamp orderTime = Timestamp.valueOf(now);
		String s = "INSERT INTO " + DBname + "order VALUES ('" + order.getOrderNumber() + "','" + order.getOrderDate()
				+ "','" + order.getArrivalDate() + "','" + order.isHomeDelivery() + "','" + order.getBranchName()
				+ order.getPrice() + "','" + order.getUsername() + "','" + order.getPersonalLetter() + "','"
				+ order.getOrderStatus().toString() + "','" + order.getOrderData() + "');";
		boolean res = (boolean) dbBoundry.sendQueary(s);
		return res;
	}

	public boolean deleteOrder(int orderNum) {
		String s = "DELETE FROM " + DBname + ".orders WHERE (orderNumber = '" + orderNum + "' );";
		boolean res = (boolean) dbBoundry.sendQueary(s);
		return res;
	}

	public Report getReportFromDB(Report report) {
		// create the query
		String s = "SELECT * FROM " + DBname + ".report WHERE branchName = '" + report.getBranchName()
				+ "' AND type = '" + report.getType().toString() + "' AND year = " + report.getYear() + " AND month = "
				+ report.getMonth() + ";";
		// get the result
		ResultSet res = (ResultSet) dbBoundry.sendQueary(s);
		// get the returned values
		ArrayList<Report> reports = objectManager.reportDB(res);
		return reports.size() > 0 ? reports.get(0) : null;
	}

	// will be implemented in the future, not supposed to work
	public boolean saveReportToDB(Report report) {
		// create the query
		byte[] data;
		String sdata = objectManager.objectToBlobString(report);
		String s = "INSERT INTO " + DBname + ".report VALUES ('" + report.getBranchName() + "','"
				+ report.getType().toString() + "','" + report.getYear() + "','" + report.getMonth() + "','" + sdata
				+ "');";
		// get the result
		boolean res = (boolean) dbBoundry.sendQueary(s);
		// get the returned values
		return res;
	}

	public boolean saveItemInOrderToDB(ProductInOrder product) {
		// create the query
		String s = "INSERT INTO " + DBname + ".itemInOrder VALUES ('" + product.getOrderNumber() + "','"
				+ product.getName() + "','" + " " + "','" + product.getPrice() + "','" + product.getAmount() + " ');";
		boolean res = (boolean) dbBoundry.sendQueary(s);
		return res;
	}

	public ArrayList<String> getItemInOrderFromDB(int orderNumber, String itemName) {
		// create the query
		ArrayList<String> item = new ArrayList<>();
		String s = "SELECT * FROM " + DBname + ".iteminorder WHERE (orderNumber = '" + orderNumber
				+ "' AND itemName = '" + itemName + "' );";
		// get the result
		ResultSet res = (ResultSet) dbBoundry.sendQueary(s);
		// get the returned values
		try {
			if (res.next()) {
				item.add(Integer.toString(res.getInt("orderNumber")));
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

	public boolean connectUser(String username, String password) throws Exception {
		// create the query
		boolean res = false;
		String s = "SELECT * FROM " + DBname + ".users WHERE (username = '" + username + "' );";
		// get the result
		ResultSet user = (ResultSet) dbBoundry.sendQueary(s);
		// get the returned values
		try {
			if (user.next() && user.getString("password").equals(password)) {
				s = "UPDATE  " + DBname + ".users  SET connected = true WHERE (username = '" + username
						+ "' AND connected = false) ;";
				res = (boolean) dbBoundry.sendQueary(s);
			} else {
				return false;
			}
		} catch (Exception e) {
			return false;
		}
		if (res == false) {
			throw new Exception("Already connecteds");
		}
		return res;
	}

	public boolean disconnectUser(String username) {
		boolean res = false;
		try {
			String s = "UPDATE  " + DBname + ".users  SET connected = false WHERE (username = '" + username + "');";
			res = (boolean) dbBoundry.sendQueary(s);
		} catch (Exception e) {

		}
		return res;
	}

	public boolean updateUser(String userName, UserType type, UserStatus status) {
		// create the query
		String s = "UPDATE  " + DBname + ".users  SET userName = '" + userName + "', userType = '" + type.toString()
				+ "', status = '" + status.toString() + "' WHERE username = '" + userName + "';";
		// send query + get result
		boolean res = (boolean) dbBoundry.sendQueary(s);
		return res;
	}

	public ArrayList<Order> getAllOrdersInBranch(String branchName, String customerID) {
		// create the query
		String s = "SELECT * FROM " + DBname + ".order WHERE (branchName = '" + branchName + "' );";
		// get the result
		ResultSet res = (ResultSet) dbBoundry.sendQueary(s);
		// get the returned values
		ArrayList<Order> orders = objectManager.orderDB(res);
		return orders;
	}

	public ArrayList<Order> getAllOrdersOfCustomer(String branchName, String customerID) {
		// create the query
		String s = "SELECT * FROM " + DBname + ".order WHERE (customerID = '" + customerID + "' );";
		// get the result
		ResultSet res = (ResultSet) dbBoundry.sendQueary(s);
		// get the returned values
		ArrayList<Order> orders = objectManager.orderDB(res);
		return orders;
	}

	public Survey getSurvey(int surveyNumber) {
		// create the query
		String s = "SELECT * FROM " + DBname + ".survey WHERE (surveyNumber = " + surveyNumber + ");";
		// get the result
		ResultSet res = (ResultSet) dbBoundry.sendQueary(s);
		// get the returned values
		ArrayList<Survey> surveys = objectManager.surveyDB(res);
		return surveys.size() > 0 ? surveys.get(0) : null;
	}

	public java.sql.Blob getSurveyResult(int surveyNumber) {
		// create the query
		java.sql.Blob pdf;
		String s = "SELECT * FROM " + DBname + ".survey WHERE (surveyNumber = " + surveyNumber + ");";
		// get the result
		ResultSet res = (ResultSet) dbBoundry.sendQueary(s);
		// get the returned values
		pdf = objectManager.surveyResultDB(res);
		return pdf;
	}

	public boolean saveSurveyResult(int surveyNumber, java.sql.Blob blob) {
		// create the query
		String pdf = objectManager.objectToBlobString(blob);
		String s = "UPDATE  " + DBname + ".survey  SET surveyResult = '" + pdf + "'  WHERE (surveyNumber = "
				+ surveyNumber + ");";
		// get the result
		boolean res = (boolean) dbBoundry.sendQueary(s);
		// get the returned values
		return res;
	}

	public ArrayList<Survey> getAllSurvey() {
		// create the query
		String s = "SELECT * FROM " + DBname + ".survey;";
		// get the result
		ResultSet res = (ResultSet) dbBoundry.sendQueary(s);
		// get the returned values
		ArrayList<Survey> surveys = objectManager.surveyDB(res);
		return surveys;
	}

	public boolean addSurveyAnswers(int[] answers, int surveyNumber) {
		// create the query
		String s = "UPDATE  " + DBname + ".survey  SET a1 = '" + answers[0] + "', a2 = '" + answers[1] + "', a3 = '"
				+ answers[2] + "', a4 = '" + answers[3] + "', a5 = '" + answers[4] + "', a6 = '" + answers[5]
				+ "' participants = participants + 1 WHERE (surveyNumber = " + surveyNumber + ");";
		// send query + get result
		boolean res = (boolean) dbBoundry.sendQueary(s);
		return res;
	}

	public int createSurvey(Survey survey) {
		int lastID = -1;
		String[] questions = survey.getQuestions();
		String s = "INSERT INTO " + DBname + ".survey VALUES ('" + questions[0] + "','" + questions[1] + "','"
				+ questions[2] + "','" + questions[3] + "','" + questions[4] + "','" + questions[5]
				+ "', 0, 0, 0, 0, 0, 0, 0);";
		boolean res = (boolean) dbBoundry.sendQueary(s);
		if (res) {
			s = "select last_insert_id() as last_id from survey";
			ResultSet idRes = (ResultSet) dbBoundry.sendQueary(s);
			lastID = objectManager.lastID(idRes);
		}
		return lastID;
	}

	public int createComplaint(Complaint complaint) {
		int lastID = -1;
		String s = "INSERT INTO " + DBname + ".survey  VALUES ('" + null + "','"
				+ complaint.getResponsibleEmployeeUserName() + "','" + complaint.getComplaint() + "','"
				+ complaint.getAnswer() + "','" + complaint.getCompensation() + "','" + complaint.getStatus().toString()
				+ "','" + complaint.getCustomerUserName() + "');";
		boolean res = (boolean) dbBoundry.sendQueary(s);
		if (res) {
			s = "select last_insert_id() as last_id from complaint";
			ResultSet idRes = (ResultSet) dbBoundry.sendQueary(s);
			lastID = objectManager.lastID(idRes);
		}
		return lastID;
	}

	public boolean updateComplaint(String answer, int complaintNumber, Status status) {
		String s = "UPDATE  " + DBname + ".complaint  SET answer = '" + answer + "', status = '" + status.toString()
				+ "' WHERE (complaintNumber = " + complaintNumber + ");";
		boolean res = (boolean) dbBoundry.sendQueary(s);
		return res;
	}

	public boolean addSurveyResult(int surveyNumber, Blob surveyResult) {
		// create the query
		String data = objectManager.objectToBlobString(surveyResult);
		String s = "UPDATE  " + DBname + ".survey  SET surveyResult = '" + surveyResult + "' WHERE (surveyNumber = "
				+ data + ");";
		// send query + get result
		boolean res = (boolean) dbBoundry.sendQueary(s);
		return res;
	}

	public ArrayList<Complaint> getAllComplaints(String employeeUsername) {
		String s = "SELECT * FROM " + DBname + ".complaint WHERE (responsibleEmployeeUsername = '" + employeeUsername
				+ "');";
		ResultSet res = (ResultSet) dbBoundry.sendQueary(s);
		// get the returned values
		ArrayList<Complaint> complaints = objectManager.complaintDB(res);
		return complaints;
	}

	public ArrayList<Complaint> getAllComplaints(Status status) {
		String s = "SELECT * FROM " + DBname + ".complaint WHERE (status = '" + status.toString() + "');";
		ResultSet res = (ResultSet) dbBoundry.sendQueary(s);
		// get the returned values
		ArrayList<Complaint> complaints = objectManager.complaintDB(res);
		return complaints;
	}

	public ArrayList<Product> getCatalogCategory(String category) {
		String s = "SELECT * FROM " + DBname + ".product WHERE (category = '" + category + "');";
		ResultSet res = (ResultSet) dbBoundry.sendQueary(s);
		// get the returned values
		ArrayList<Product> products = objectManager.productDB(res);
		return products;
	}

	public int savePromotion(Promotion promotion) {
		int lastID = -1;
		String s = "INSERT INTO " + DBname + ".promotion  VALUES ('" + null + "','" + promotion.getProductID() + "','"
				+ promotion.getDiscount() + "','" + promotion.getPromotionText() + "');";
		boolean res = (boolean) dbBoundry.sendQueary(s);
		if (res) {
			s = "select last_insert_id() as last_id from promotion";
			ResultSet idRes = (ResultSet) dbBoundry.sendQueary(s);
			lastID = objectManager.lastID(idRes);
		}
		return lastID;
	}

	public ArrayList<Order> getAllOrdersForReport(int month, int year) {
		// create the query
		String s = "SELECT * FROM " + DBname + ".order WHERE (month = " + month + " AND year = " + year + " );";
		// get the result
		ResultSet res = (ResultSet) dbBoundry.sendQueary(s);
		// get the returned values
		ArrayList<Order> orders = objectManager.orderDB(res);
		return orders;
	}

	public ArrayList<ProductInOrder> getAllProductsInOrder(int orderNumber) {
		// create the query
		String s = "SELECT * FROM " + DBname + ".productInOrder WHERE (orderNumber = " + orderNumber + ");";
		// get the result
		ResultSet res = (ResultSet) dbBoundry.sendQueary(s);
		// get the returned values
		ArrayList<ProductInOrder> products = objectManager.productsInOrderDB(res);
		return products;
	}

	public ArrayList<Report> getAllQuarterReports(int startMonths, int endMonth, int year) {
		// create the query
		String s = "SELECT * FROM " + DBname + ".report WHERE (month >= " + startMonths + " AND month <= " + endMonth
				+ " AND year = " + year + ");";
		// get the result
		ResultSet res = (ResultSet) dbBoundry.sendQueary(s);
		// get the returned values
		ArrayList<Report> reports = objectManager.reportDB(res);
		return reports;
	}

	public User getUser(String username) {
		String s = "SELECT * FROM " + DBname + ".users WHERE (username = '" + username + "');";
		// get the result
		ResultSet res = (ResultSet) dbBoundry.sendQueary(s);
		// get the returned values
		User user = objectManager.userDB(res);
		return user;
	}

	public String getCardInfo(String userID) {
		String s = "SELECT cardInfo FROM " + DBname + ".users WHERE (userID = '" + userID + "');";
		// get the result
		ResultSet res = (ResultSet) dbBoundry.sendQueary(s);
		// get the returned values
		String info = objectManager.cardDB(res);
		return info;
	}

	public ArrayList<String> getAllBranches() {
		// create the query
		String s = "SELECT * FROM " + DBname + ".Branch;";
		// get the result
		ResultSet res = (ResultSet) dbBoundry.sendQueary(s);
		// get the returned values
		ArrayList<String> branches = objectManager.branchNameDB(res);
		return branches;
	}

	public boolean updateProduct(Product product) {
		// TODO Auto-generated method stub
		return false;
	}
}
