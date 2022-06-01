package clientHandlers;

import database.DBController;
import msg.Msg;
import ocsf.server.ConnectionToClient;
import orderManagment.OrderProcessManager;
import server.ServerMsgController;
import user.User;

/**
 * handle the client task
 *
 */
public class HandleClientTask {
	// class variables
	private User ActiveUser;

	public User getActiveUser() {
		return ActiveUser;
	}

	public void setActiveUser(User activeUser) {
		ActiveUser = activeUser;
	}
	
	
	
	
	
	
	
}
