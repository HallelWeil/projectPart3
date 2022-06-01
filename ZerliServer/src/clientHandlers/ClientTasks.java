package clientHandlers;

import database.DBController;
import msg.Msg;
import ocsf.server.ConnectionToClient;
import orderManagment.OrderProcessManager;
import server.ServerMsgController;
import user.User;

public abstract class ClientTasks {
	// class variables
	/**
	 * to parse the massages
	 */
	protected ServerMsgController msgController;
	/**
	 * the database controller
	 */
	protected DBController dbController;
	/**
	 * static msg
	 */
	protected Msg CompletedMsg;
	protected Msg ErrorMsg;
	/**
	 * the new msg we want to sand,
	 */
	protected Msg newMsgToSend;
	/**
	 * the client task handler
	 */
	protected HandleClientTask clientTaskHandler;

	public ClientTasks(HandleClientTask clientTaskHandler) {
		this.clientTaskHandler = clientTaskHandler;
		this.dbController = DBController.getInstance();
		msgController = new ServerMsgController();
		CompletedMsg = ServerMsgController.createCOMPLETEDMsg();
		ErrorMsg = ServerMsgController.createERRORMsg("");
	}

	/**
	 * get a msg, handle the task and return the msg to send back
	 * 
	 * @param msg
	 * @return
	 */
	public abstract Msg handleTask(Object msg);
}
