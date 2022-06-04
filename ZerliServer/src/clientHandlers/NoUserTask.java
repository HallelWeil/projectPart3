package clientHandlers;

import java.util.ArrayList;

import msg.Msg;
import server.ServerMsgController;

/**
 * the action for non connected user, can login
 */
public class NoUserTask extends ClientTasks {

	public NoUserTask(HandleClientTask clientTaskHandler) {
		super(clientTaskHandler);
	}

	/**
	 * the action for non connected user, can login
	 */
	@Override
	public Msg handleTask(ServerMsgController msgController) {

		// action for none connected clients
		switch (msgController.getType()) {
		case GET_BRANCH_LIST:
			ArrayList<String> branches = dbController.getAllBranches();
			newMsgToSend = ServerMsgController.createRETURN_BRANCH_NAMESMsg(branches);
			break;
		case LOGIN_REQUEST:
			// get User with userName and Password from db
			try {
				if (dbController.connectUser(msgController.getUserName(), msgController.getPassword())) {
					user = dbController.getUser(msgController.getUserName());
					newMsgToSend = ServerMsgController.createAPPROVE_LOGINMsg(user);
					this.clientTaskHandler.login(user);
				} else {// wrong username or password
					newMsgToSend = ServerMsgController.createERRORMsg("Wrong username and password");
				}
			} catch (Exception e) {
				newMsgToSend = ServerMsgController.createERRORMsg("The user already connected");// already connected msg
			}
			break;
		case ERROR:
			// none
			break;
		default:
			// handle cant do it
			newMsgToSend = ServerMsgController.createERRORMsg("Error! unauthorized access");
			break;
		}
		return newMsgToSend;
	}

}
