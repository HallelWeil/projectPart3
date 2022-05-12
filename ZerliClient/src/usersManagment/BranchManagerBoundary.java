package usersManagment;

import java.sql.Date;

import client.ClientController;
import client.MsgController;
import msg.Msg;
import msg.MsgType;
import order.Order;
import order.OrderStatus;
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

	private ClientController clientController;
	private Msg msg;
	private MsgController msgController;

	/**
	 * request the order approval, using order number and approve\not approve
	 * 
	 * @param orderNumber
	 * @return true if the request succeed
	 */
	public boolean requestApproveOrder(int orderNumber, boolean isApproved) {
		Order order = new Order();
		order.setOrderID(orderNumber);
		if (isApproved) // branchManger approved the request
		{
			order.setStatus(OrderStatus.Approved);
		} else {
			order.setStatus(OrderStatus.NotApproved);
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
	public boolean requestApproveCancelation(int orderNumber, boolean isApproved, double refundAmount) {

		Order order = new Order();
		order.setOrderID(orderNumber);
		order.setPrice(refundAmount); // in price field we set the refuned value(server get this value for set in
										// refund field of the user)
		if (isApproved) // branchManger not approve Cancellation
		{
			order.setOrderStatus(OrderStatus.CANECELED);
		} else {
			order.setStatus(OrderStatus.Approved); // branchManger didn't accept to cancel then he approved the order
		}
		msg = MsgController.createUPDATE_ORDER_STATUSMsg(order);
		msgController = clientController.sendMsg(msg);
		if (msgController.getType().equals(MsgType.COMPLETED)) // receive completed in type mean update has been done
																// and succeed
		{
			return true;
		}
		return false; // return false mean updated not succeed

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

		User user = new User();
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
	public Report requestViewReport(ReportType type, String Month, String Year) {
		String branchNameOfUser = loginResults.getUser().getBranchName(); //get branchName from branchManger user 
		Report report = new Report(Month, Year, type, branchNameOfUser);
		msg = MsgController.createView_ReportMsg(report);
		msgController=clientController.sendMsg(msg);
		if(msgController.getType().equals(MsgType.RETURN_REPORT));
		{
			return msgController.getReport();
		}
		return null;  //in case returned msg was ERROR for Example mean Report not found or exist 
	}

}
