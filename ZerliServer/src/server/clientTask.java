package server;

import java.util.ArrayList;

import catalog.Product;
import complaint.Complaint;
import database.DBController;
import msg.Msg;
import ocsf.server.ConnectionToClient;
import order.Order;
import orderManagment.OrderController;
import report.Report;
import survey.Survey;
import user.User;

/**
 * manage a single client tasks, base on the client user type(if connected)
 * 
 * @author halel
 *
 */
public class ClientTask {
	// class variables
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
		this.dbController = DBController.getInstance();
		user = null;
		msgController = new ServerMsgController();
		CompletedMsg = ServerMsgController.createCOMPLETEDMsg();
		ErrorMsg = ServerMsgController.createERRORMsg("");
		orderController = null;
	}

	/**
	 * handle single client task, for security each client can only do the tasks
	 * available for the active connected user from the client.
	 * 
	 * @param msg the received message
	 * @return the return message
	 */
	public Msg handleTask(Object msg) {
		// if no correct msg was found
		if (msgController.mgsParser(msg) == false)
			return ErrorMsg;
		// action for none connected clients
		if (user == null)
			noUserTasks();
		else {
			// some tasks are identical for all the connected users
			switch (msgController.getType()) {
			case LOG_OUT_REQUEST:
			case EXIT:
				// to log out remove the user entity
				dbController.disconnectUser(user.getUsername());
				this.orderController = null;
				this.user = null;
				newMsgToSend = ServerMsgController.createAPPROVE_LOGOUTMsg();
				break;
			case GET_BRANCH_LIST:
				ArrayList<String> branches = dbController.getAllBranches();
				newMsgToSend = ServerMsgController.createRETURN_BRANCH_NAMESMsg(branches);
				break;
			case ERROR:
				// none
				break;
			default:
				// each user handle this tasks differently
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
	 * the action for non connected user, can login
	 */
	private void noUserTasks() {
		switch (msgController.getType()) {
		case LOGIN_REQUEST:
			// get User with userName and Password from db
			try {
				if (dbController.connectUser(msgController.getUserName(), msgController.getPassword())) {
					user = dbController.getUser(msgController.getUserName());
					newMsgToSend = ServerMsgController.createAPPROVE_LOGINMsg(user);
				} else {// wrong username or password
					newMsgToSend = ServerMsgController.createERRORMsg("Wrong username and password");
				}
			} catch (Exception e) {
				newMsgToSend = ServerMsgController.createERRORMsg("The user already connected");// already connected msg
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
			newMsgToSend = ServerMsgController.createERRORMsg("Error! unauthorized access");
			break;
		}
	}

	/**
	 * Handle the branch manager actions can update the user data, update the order
	 * status and get all the order belonging to his branch
	 */
	private void handleBranchManagerRequest() {
		switch (msgController.getType()) {
		case UPATE_USER_DATA:
			if (dbController.updateUser(msgController.getUser().getUsername(), msgController.getUser().getUserType(),
					msgController.getUser().getStatus()))
				newMsgToSend = CompletedMsg;
			else
				newMsgToSend = ServerMsgController.createERRORMsg("Error! failed to update the user information");
			break;
		case UPDATE_ORDER_STATUS:
			if (dbController.updateOrder(msgController.getOrder()))
				newMsgToSend = CompletedMsg;
			else
				newMsgToSend = ServerMsgController.createERRORMsg("Error! failed to update the order status");
			break;
		case GET_ALL_ORDERS:
			ArrayList<Order> orders = dbController.getAllOrdersInBranch(user.getBranchName(), null);
			newMsgToSend = ServerMsgController.createRETURN_ALL_ORDERSMsg(orders);
			break;
		case GET_USER:
			User user = dbController.getUser(msgController.getUserName());
			newMsgToSend = ServerMsgController.createRETURN_USERMsg(user);
			break;
		case GET_ORDER:
			Order order = dbController.getOrdrFromDB(msgController.getOrderNumber());
			order.setItems(dbController.getItemInOrderFromDB(msgController.getOrderNumber()));
			newMsgToSend = ServerMsgController.createRETURN_ORDERMsg(order);
			break;
		default:
			// handle cant do it
			newMsgToSend = ServerMsgController.createERRORMsg("Error! unauthorized access");
			break;
		}
	}

	/**
	 * Handle the ceo actions, can watch reports
	 */
	private void handleCEORequest() {
		switch (msgController.getType()) {
		case GET_REPORT:
			Report tempReport = new Report(msgController.getMonth(), msgController.getYear(),
					msgController.getReportType(), msgController.getBranch());
			Report report = dbController.getReportFromDB(tempReport);
			newMsgToSend = ServerMsgController.creatRETURN_REPORTMsg(report);
			break;
		default:
			// handle cant do it
			newMsgToSend = ServerMsgController.createERRORMsg("Error! unauthorized access");
			break;
		}
	}

	/**
	 * handle the branch employee actions, can get survey, get all the survey and
	 * add survey answers
	 */
	private void handleBranchEmployeeRequest() {
		switch (msgController.getType()) {
		case GET_SURVEY:
			// get survey from db
			Survey survey = dbController.getSurvey(msgController.getSurveyNumber());
			newMsgToSend = ServerMsgController.createRETURN_SURVEYMsg(survey);
			break;
		case GET_ALL_SURVEY:
			// get all the surveys
			ArrayList<Survey> surveys = dbController.getAllSurvey();
			newMsgToSend = ServerMsgController.createRETURN_ALL_SURVEYMsg(surveys);
			break;
		case ADD_SURVEY_ANSWERS:
			if (dbController.addSurveyAnswers(msgController.getAnswers(), msgController.getSurveyNumber()))
				newMsgToSend = CompletedMsg;
			else
				newMsgToSend = ServerMsgController.createERRORMsg("Error! failed to update the survey");
			break;
		default:
			// handle cant do it
			newMsgToSend = ServerMsgController.createERRORMsg("Error! unauthorized access");
			break;
		}
	}

	/**
	 * handle the customer service employee actions, can create complaint, update
	 * complaint, create new survey, add survey result and get all the complaints he
	 * responsible for
	 */
	private void handleCustomerServiceEmloyeeRequest() {
		switch (msgController.getType()) {
		case CREATE_COMPLAINT:
			if (dbController.createComplaint(msgController.getComplaint()) != -1)
				newMsgToSend = CompletedMsg;
			else
				newMsgToSend = ServerMsgController.createERRORMsg("Error! failed to create the complaint");
			break;
		case UPDATE_COMPLAINT:
			Complaint tempComplaint = msgController.getComplaint();
			if (dbController.updateComplaint(tempComplaint.getAnswer(), tempComplaint.getComplaintsNumber(),
					tempComplaint.getStatus()))
				newMsgToSend = CompletedMsg;
			else
				newMsgToSend = ServerMsgController.createERRORMsg("Error! failed to update the complaint");
			break;
		case CREATE_SURVEY:
			if (dbController.createSurvey(msgController.getSurvey()) != -1)
				newMsgToSend = CompletedMsg;
			else
				newMsgToSend = ServerMsgController.createERRORMsg("Error! failed to create the survey");
			break;
		case ADD_SURVEY_RESULT:
			if (dbController.addSurveyAnswers(msgController.getAnswers(), msgController.getSurveyNumber()))
				newMsgToSend = CompletedMsg;
			else
				newMsgToSend = ServerMsgController.createERRORMsg("Error! failed to add the survey result");
			break;
		case GET_ALL_COMPLAINTS:
			// get all the relevant complaints from db
			ArrayList<Complaint> complaints = dbController.getAllComplaints(user.getUsername());
			newMsgToSend = ServerMsgController.createRETURN_ALL_COMPLAINTSMsg(complaints);
			break;
		default:
			// handle cant do it
			newMsgToSend = ServerMsgController.createERRORMsg("Error! unauthorized access");
			break;
		}
	}

	/**
	 * handle the marketing employee actions, can create new promotion and update
	 * the catalog
	 */
	private void handleMArketingEmployeeRequest() {
		switch (msgController.getType()) {
		case ACTIVATE_PROMOTION:
			if (dbController.savePromotion(msgController.getPromotion()) != -1) {
				// the promotion was created
				// to do -> update the item price
				newMsgToSend = CompletedMsg;
			} else
				newMsgToSend = ServerMsgController.createERRORMsg("Error! failed to create the promotion");
			break;
		case UPDATE_CATALOG:
			if (dbController.updateProduct(msgController.getProduct()))
				newMsgToSend = CompletedMsg;
			else
				newMsgToSend = ServerMsgController.createERRORMsg("Error! failed to update the catalog");
			break;
		default:
			// handle cant do it
			newMsgToSend = ServerMsgController.createERRORMsg("Error! unauthorized access");
			break;
		}
	}

	/**
	 * handle the non authorized customer actions, can view the catalog, orders
	 * history and cancel orders
	 */
	private void handleNonAuthorizedCustomerRequest() {
		switch (msgController.getType()) {
		case GET_CATALOG_PAGE:
			// get catalog page
			ArrayList<Product> catalog = dbController.getCatalogCategory(msgController.getCategory());// toto
			newMsgToSend = ServerMsgController.createRETURN_CATALOG_PAGEMsg(catalog);
			break;
		case GET_ALL_ORDERS:
			ArrayList<Order> orders = dbController.getAllOrdersOfCustomer(null, user.getUsername());
			newMsgToSend = ServerMsgController.createRETURN_ALL_ORDERSMsg(orders);
			break;
		case UPDATE_ORDER_STATUS:
			// update order status in the db
			dbController.updateOrder(msgController.getOrder());
			break;

		default:
			// handle cant do it
			newMsgToSend = ServerMsgController.createERRORMsg("Error! unauthorized access");
			break;
		}
	}

	/**
	 * handle the authorized customer actions, can view the catalog, get his orders
	 * history, pay for order ,place order and update order status
	 */
	private void handleAuthorizedCustomerRequest() {
		switch (msgController.getType()) {
		case GET_CATALOG_PAGE:
			// get catalog page
			ArrayList<Product> catalog = dbController.getCatalogCategory(msgController.getCategory());// toto
			newMsgToSend = ServerMsgController.createRETURN_CATALOG_PAGEMsg(catalog);
			break;
		case GET_ALL_ORDERS:
			ArrayList<Order> orders = dbController.getAllOrdersOfCustomer(null, user.getUsername());
			newMsgToSend = ServerMsgController.createRETURN_ALL_ORDERSMsg(orders);
			break;
		case PAY_FOR_ORDER:
			// get the card info
			String cardInfo = dbController.getCardInfo(user.getUsername());
			// use the order controller to pay
			if (orderController.payForOrder(cardInfo)) {
				// payment succeed, save the order!
				if (orderController.saveOrderToDB()) {
					// the order saved successfully
					newMsgToSend = ServerMsgController.createRETURN_PAYMENT_APPROVALMsg();
				} else {
					newMsgToSend = ServerMsgController.createERRORMsg("Error! failed to save the order!");
				}
			} else {
				newMsgToSend = ServerMsgController.createERRORMsg("Payment declined!");
			}
			// reset the order controller
			orderController.reset();
			break;
		case PLACE_ORDER_REQUEST:
			// use the order controller
			orderController = new OrderController();
			Order order = orderController.placeOrder(msgController.getCart(), 0, user.getUsername());
			newMsgToSend = ServerMsgController.createRETURN_ORDERMsg(order);
			break;
		case UPDATE_ORDER_STATUS:
			// update order status in the db
			dbController.updateOrder(msgController.getOrder());
			break;
		case GET_ORDER:
			Order order2 = dbController.getOrdrFromDB(msgController.getOrderNumber());
			order2.setItems(dbController.getItemInOrderFromDB(msgController.getOrderNumber()));
			newMsgToSend = ServerMsgController.createRETURN_ORDERMsg(order2);
			break;
		default:
			// handle cant do it
			newMsgToSend = ServerMsgController.createERRORMsg("Error! unauthorized access");
			break;
		}
	}

	/**
	 * The courier actions. can update order status
	 */
	private void handleCourierRequest() {
		switch (msgController.getType()) {
		case UPDATE_ORDER_STATUS:
			// update order status in the db
			dbController.updateOrder(msgController.getOrder());
			break;
		default:
			// handle cant do it
			newMsgToSend = ServerMsgController.createERRORMsg("Error! unauthorized access");
			break;
		}
	}

	public void forceLogOut() {
		if (user != null) {
			// to log out remove the user entity
			dbController.disconnectUser(user.getUsername());
			this.orderController = null;
			this.user = null;
		}

	}

}
