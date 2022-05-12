package server;

import java.io.IOException;
import java.util.ArrayList;

import catalog.Product;
import complaint.Complaint;
import database.DBController;
import msg.Msg;
import msg.MsgType;
import ocsf.server.ConnectionToClient;
import order.Order;
import orderManagment.OrderController;
import report.Report;
import report.ReportType;
import survey.Survey;
import user.User;

/**
 * manage a single client tasks, base on the client user type(if connected)
 * 
 * @author halel
 *
 */
public class ClientTask {

	/**
	 * to parse the massages
	 */
	private ServerMsgController msgController;
	/**
	 * save the current user info
	 */
	private User user;
	/**
	 * save the client connection
	 */
	private ConnectionToClient client;
	/**
	 * the database controller
	 */
	private DBController dbController;
	/**
	 * static msg
	 */
	private Msg CompletedMsg, ErrorMsg;
	/**
	 * order controller to manage the order process
	 */
	private OrderController orderController;
	/**
	 * the new msg we want to sand,
	 */
	private Msg newMsgToSend;

	public ClientTask(ConnectionToClient client) {
		super();
		this.client = client;
		this.dbController = null;// todo
		user = null;
		msgController = new ServerMsgController();
		CompletedMsg = ServerMsgController.createCOMPLETEDMsg();
		ErrorMsg = ServerMsgController.createERRORMsg();
		orderController = new OrderController();
	}

	/**
	 * handle single client task, for security each client can only do the tasks
	 * available for the active connected user from the client.
	 * 
	 * @param msg
	 * @return
	 * @throws IOException
	 */
	public Msg handleTask(Object msg) {
		// if no correct msg was found
		if (msgController.mgsParser(msg) == false)
			return ErrorMsg;
		// action for none connected clients
		if (user == null)
			noUserTasks();
		else {
			switch (msgController.getType()) {
			case LOG_OUT_REQUEST:
				// to log out remove the user entity
				dbController.logout(msgController.getUserName());
				this.orderController = null;
				this.user = null;
				break;
			case EXIT:
				// none
				break;
			case ERROR:
				// none
				break;
			default:
				handleMsgForUserType();
				break;
			}
		}
		return newMsgToSend;
	}

	/**
	 * handle the request base on the user type
	 */
	private void handleMsgForUserType() {
		switch (user.getUserType()) {
		case NonAuthorizedCustomer:
			handleNonAuthorizedCustomerRequest();
			break;
		case AuthorizedCustomer:
			handleAuthorizedCustomerRequest();
			break;
		case BranchManager:
			handleBranchManagerRequest();
			break;
		case BranchEmployee:
			handleBranchEmployeeRequest();
			break;
		case CustomerServiceEmloyee:
			handleCustomerServiceEmloyeeRequest();
			break;
		case MarketingEmployee:
			handleMArketingEmployeeRequest();
			break;
		case Courier:
			handleCourierRequest();
			break;
		case CEO:
			handleCEORequest();
			break;
		default:
			break;
		}
	}

	// for each client type there are different tasks and different action for each
	/**
	 * the action for non connected user
	 */
	private void noUserTasks() {
		switch (msgController.getType()) {
		case LOGIN_REQUEST:
			// get User with userName and Password from db
			User user = null;
			try {
				user = dbController.login(msgController.getUserName(), msgController.getPassword());
			} catch (Exception e) {
				newMsgToSend = ServerMsgController.createERRORMsg();// todo: update to already connected msg
				break;
			}
			if (user != null) {
				this.user = user;
				this.orderController = new OrderController();
				newMsgToSend = ServerMsgController.createAPPROVE_LOGINMsg(user);
			} else {
				newMsgToSend = ServerMsgController.createERRORMsg();
			}
			break;
		case EXIT:
			// none
			break;
		case ERROR:
			// none
			break;
		default:
			// handle cant do it
			newMsgToSend = ErrorMsg;
			break;
		}
		newMsgToSend = CompletedMsg;
	}

	/**
	 * the branch manager actions
	 */
	private void handleBranchManagerRequest() {
		switch (msgController.getType()) {
		case UPATE_USER_DATA:
			if (dbController.updateUserData(msgController.getUser().getUsername(),
					msgController.getUser().getUserType(), msgController.getUser().getStatus()))
				newMsgToSend = CompletedMsg;
			else
				newMsgToSend = ErrorMsg;
			break;
		case UPDATE_ORDER_STATUS:
			if (dbController.updateOrder(msgController.getOrder()))
				newMsgToSend = CompletedMsg;
			else
				newMsgToSend = ErrorMsg;
			break;
		case GET_ALL_ORDERS:
			ArrayList<Order> orders = dbController.getAllOrders(user.getBranchName(), null);
			newMsgToSend = ServerMsgController.createRETURN_ALL_ORDERSMsg(orders);
			break;
		default:
			//
			break;
		}
	}

	/**
	 * the ceo actions
	 */
	private void handleCEORequest() {
		switch (msgController.getType()) {
		case GET_REPORT:
			Report report = dbController.getReport(msgController.getReportType(), msgController.getYear(),
					msgController.getMonth(), msgController.getBranch());
			newMsgToSend = ServerMsgController.creatRETURN_REPORTMsg(report);
			break;
		default:
			//
			break;
		}
	}

	/**
	 * the branch employee actions
	 */
	private void handleBranchEmployeeRequest() {
		switch (msgController.getType()) {
		case GET_SURVEY:
			// get survey from db
			Survey survey = dbController.getSurvey(msgController.getSurveyNumber());
			newMsgToSend = ServerMsgController.createRETURN_SURVEYMsg(survey);
			break;
		case GET_ALL_SURVEY:
			// get all surveys
			ArrayList<Survey> surveys = dbController.getAllSurveys();
			newMsgToSend = ServerMsgController.createRETURN_ALL_SURVEYMsg(surveys);
			break;
		case ADD_SURVEY_ANSWERS:
			if (dbController.addSurveyAnswers(msgController.getAnswers(), msgController.getSurveyNumber()))
				newMsgToSend = CompletedMsg;
			else
				newMsgToSend = ErrorMsg;
			break;
		default:
			//
			break;
		}
	}

	/**
	 * the customer service employee actions
	 */
	private void handleCustomerServiceEmloyeeRequest() {
		switch (msgController.getType()) {
		case CREATE_COMPLAINT:
			if (dbController.createComplaint(msgController.getComplaint()))
				newMsgToSend = CompletedMsg;
			else
				newMsgToSend = ErrorMsg;
			break;
		case UPDATE_COMPLAINT:
			Complaint tempComplaint = msgController.getComplaint();
			if (dbController.updateComplaint(tempComplaint.getAnswer(), tempComplaint.getComplaintsNumber(),
					tempComplaint.getStatus()))
				newMsgToSend = CompletedMsg;
			else
				newMsgToSend = ErrorMsg;
			break;
		case CREATE_SURVEY:
			if (dbController.createSurvey(msgController.getSurvey()))
				newMsgToSend = CompletedMsg;
			else
				newMsgToSend = ErrorMsg;
			break;
		case ADD_SURVEY_RESULT:
			if (dbController.addSurveyAnswers(msgController.getAnswers(), msgController.getSurveyNumber()))
				newMsgToSend = CompletedMsg;
			else
				newMsgToSend = ErrorMsg;
			break;
		case GET_ALL_COMPLAINTS:
			// get all the relevant complaints from db
			ArrayList<Complaint> complaints = dbController.getAllcomplaints(user.getPersonID());
			newMsgToSend = ServerMsgController.createRETURN_ALL_COMPLAINTSMsg(complaints);
			break;
		default:
			//
			break;
		}
	}

	/**
	 * the marketing employee actions
	 */
	private void handleMArketingEmployeeRequest() {
		switch (msgController.getType()) {
		case ACTIVATE_PROMOTION:
			// if(dbController.) todo
			break;
		case UPDATE_CATALOG:
			if (dbController.updateProduct(msgController.getProduct()))
				newMsgToSend = CompletedMsg;
			else
				newMsgToSend = ErrorMsg;
			break;
		default:
			//
			break;
		}
	}

	/**
	 * the non authorized customer actions
	 */
	private void handleNonAuthorizedCustomerRequest() {
		switch (msgController.getType()) {
		case GET_CATALOG_PAGE:
			// get catalog page
			ArrayList<Product> catalog = null;// get the page
			newMsgToSend = ServerMsgController.createRETURN_CATALOG_PAGEMsg(catalog);
			break;
		case GET_ALL_ORDERS:
			ArrayList<Order> orders = null;// from db
			newMsgToSend = ServerMsgController.createRETURN_ALL_ORDERSMsg(orders);
			break;
		case UPDATE_ORDER_STATUS:
			// update order status to db
			break;

		default:
			//
			break;
		}
	}

	/**
	 * the authorized customer actions
	 */
	private void handleAuthorizedCustomerRequest() {
		switch (msgController.getType()) {
		case GET_CATALOG_PAGE:
			// get catalog page
			ArrayList<Product> catalog = dbController.getCatalogCategory(msgController.getCategory());
			newMsgToSend = ServerMsgController.createRETURN_CATALOG_PAGEMsg(catalog);
			break;
		case GET_ALL_ORDERS:
			ArrayList<Order> orders = dbController.getAllOrders(null, user.getUsername());
			newMsgToSend = ServerMsgController.createRETURN_ALL_ORDERSMsg(orders);
			break;
		case PAY_FOR_ORDER:
			// use the order controller for that
			// send the order to the db
			// reset the order controller
			break;
		case PLACE_ORDER_REQUEST:
			// use the order controller
			Order order = orderController.placeOrder(msgController.getCart(), 0);
			newMsgToSend = ServerMsgController.createRETURN_ORDERMsg(order);
			break;
		case UPDATE_ORDER_STATUS:
			// update order status to db
			break;
		default:
			//
			break;
		}
	}

	/**
	 * the curier actions
	 */
	private void handleCourierRequest() {
		switch (msgController.getType()) {
		case UPDATE_ORDER_STATUS:
			// update order status to db
			break;
		default:
			//
			break;
		}
	}

}
