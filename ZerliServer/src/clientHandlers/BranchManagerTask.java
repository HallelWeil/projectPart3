package clientHandlers;

import java.util.ArrayList;

import msg.Msg;
import order.Order;
import report.Report;
import server.ServerMsgController;
import user.User;

public class BranchManagerTask extends ClientTasks {

	public BranchManagerTask(HandleClientTask clientTaskHandler) {
		super(clientTaskHandler);
		// TODO Auto-generated constructor stub
	}

	@Override
	public Msg handleTask(Object msg) {
		// if no correct msg was found
		if (msgController.mgsParser(msg) == false)
			return ErrorMsg;
		switch (msgController.getType()) {
		case LOG_OUT_REQUEST:
		case EXIT:
			// to log out remove the user entity
			dbController.disconnectUser(clientTaskHandler.getActiveUser().getUsername());
			clientTaskHandler.setActiveUser(null);
			newMsgToSend = ServerMsgController.createAPPROVE_LOGOUTMsg();
			break;
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
			ArrayList<Order> orders = dbController
					.getAllOrdersInBranch(clientTaskHandler.getActiveUser().getBranchName(), null);
			newMsgToSend = ServerMsgController.createRETURN_ALL_ORDERSMsg(orders);
			break;
		case GET_USER:
			User tempuser = dbController.getUser(msgController.getUserName());
			newMsgToSend = ServerMsgController.createRETURN_USERMsg(tempuser);
			break;
		case GET_ORDER:
			Order order = dbController.getOrdrFromDB(msgController.getOrderNumber());
			order.setItems(dbController.getItemInOrderFromDB(msgController.getOrderNumber()));
			newMsgToSend = ServerMsgController.createRETURN_ORDERMsg(order);
			break;
		case GET_REPORT:
			Report tempReport = new Report(msgController.getMonth(), msgController.getYear(),
					msgController.getReportType(), clientTaskHandler.getActiveUser().getBranchName());
			Report report = dbController.getReportFromDB(tempReport);
			newMsgToSend = ServerMsgController.creatRETURN_REPORTMsg(report);
			break;
		default:
			// handle cant do it
			newMsgToSend = ServerMsgController.createERRORMsg("Error! unauthorized access");
			break;
		}
		return newMsgToSend;
	}

}
