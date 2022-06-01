package usersManagment;

import java.sql.Date;
import java.util.ArrayList;

import client.ClientBoundary;
import client.ClientController;
import client.MsgController;
import msg.Msg;
import msg.MsgType;
import order.*;
import report.Report;
import report.ReportType;
import user.User;
import user.UserStatus;
import user.UserType;

/**
 * use the branch manager contoller and the different controllers for the needed
 * actions
 * 
 * @author halel
 *
 */
public class BranchManagerBoundary extends UserBoundary {

	/**
	 * clientController used to communicate with clientController and let the
	 * branchManger send to the server msg contains the data or mission we want to
	 * received from the server msgController used to create mission or ask message
	 * and received the returned data
	 */
	private ClientController clientController = ClientController.getInstance();
	private Msg msg;
	/**
	 * in msgController saved the received data (in case we receive ERROR type in
	 * the GUI we check the error string)
	 */
	private MsgController msgController;

	/**
	 * request the order approval, using order number and approve\not approve
	 * 
	 * @param orderNumber
	 * @return true if the request succeed
	 */
	public boolean requestApproveOrder(int orderNumber, boolean isApproved) {
		Order order = new Order();
		order.setOrderNumber(orderNumber);
		if (isApproved) // branchManger approved the request
		{
			order.setOrderStatus(OrderStatus.APPROVED);
		} else {
			order.setOrderStatus(OrderStatus.NOT_APPROVED);
		}
		msg = MsgController.createUPDATE_ORDER_STATUSMsg(order);
		msgController = clientController.sendMsg(msg);
		if (msgController.getType().equals(MsgType.COMPLETED)) // receive completed in type mean update has been done
																// and succeed
		{
			return true;
		}
		return false; // return false mean updated for new status not succeed

	}

	/**
	 * request the order cancellation approval, using order number and approve\not
	 * approve
	 * 
	 * @param orderNumber
	 * @return true if the request succeed
	 */
	public boolean requestApproveCancelation(int orderNumber, boolean isApproved) {

		Order order = new Order();
		order.setOrderNumber(orderNumber);
		if (isApproved) // branchManger not approve Cancellation
		{
			order.setOrderStatus(OrderStatus.CANCELED);
		} else {
			order.setOrderStatus(OrderStatus.APPROVED); // branchManger didn't accept to cancel then he approved the
														// order
		}
		msg = MsgController.createUPDATE_ORDER_STATUSMsg(order);
		msgController = clientController.sendMsg(msg);
		if (msgController.getType().equals(MsgType.COMPLETED)) // receive completed in type mean update has been done
																// and succeed
		{
			return true;
		}
		return false; // return false mean update not succeed
		// (if GUI received false it should access to msgController of
		// branchMangerBoundary
		// and check the Error string and display it)

	}

	/**
	 * update user type + status, can be null if only wanted to update one of the
	 * fields return boolean if the request succeed
	 * 
	 * @param userName
	 * @param type
	 * @param status
	 * @return
	 */
	public boolean requestUpdateUserData(String userName, UserType type, UserStatus status) {

		User user = UserBoundary.CurrentUser;
		if (type != null) // check if field null then we didn't need to update
		{
			user.setUserType(type);
		}
		if (status != null) {
			user.setStatus(status);
		}
		user.setUsername(userName);
		msg = MsgController.createUPATE_USER_DATAMsg(user);
		msgController = clientController.sendMsg(msg);
		if (msgController.getType().equals(MsgType.COMPLETED)) // receive completed in type mean update has been done
																// and succeed
		{
			return true;
		}
		return false;
	}

	/**
	 * return the report get the report using the client controller
	 * 
	 * @return
	 */
	public Report requestViewReport(ReportType type, int Month, int year) {
		// String branchNameOfUser = CurrentUser.getBranchName(); // get branchName from
		// branchManger user
		String branchNameOfUser = UserBoundary.CurrentUser.getBranchName();
		msg = MsgController.createGET_REPORTMsg(type, year, Month, branchNameOfUser);
		msgController = clientController.sendMsg(msg);
		if (msgController.getType().equals(MsgType.RETURN_REPORT)) {
			return msgController.getReport();
		}
		return null; // in case returned msg was ERROR for Example mean Report not found or exist
	}

	public User requestUser(String username) {
		msg = MsgController.createGET_USERMsg(username);
		msgController = clientController.sendMsg(msg);
		if (msgController.getType().equals(MsgType.RETURN_USER)) {
			return msgController.getUser();
		}
		return null; // in case returned msg was ERROR for Example mean user not found or exist
	}

	public ArrayList<Order> getAllOrdersToApprove() {
		msg = MsgController.createGET_ALL_ORDERSMsg();
		msgController = clientController.sendMsg(msg);
		if (msgController.getType().equals(MsgType.RETURN_ALL_ORDERS)) {
			return msgController.getOrders();
		}
		return null; // in case returned msg was ERROR for Example mean orders not found or exist
	}

	public ArrayList<ProductInOrder> getAllProductsInOrder(int orderNumber) {
		msg = MsgController.createGET_ORDERMsg(orderNumber);
		msgController = clientController.sendMsg(msg);
		if (msgController.getType().equals(MsgType.RETURN_ORDER)) {
			return msgController.getOrder().getItems();
		}
		return null; // in case returned msg was ERROR for Example mean orders not found or exist
	}

}
