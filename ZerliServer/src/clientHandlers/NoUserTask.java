package clientHandlers;

import msg.Msg;
import server.ServerMsgController;
import user.User;

public class NoUserTask extends ClientTasks {

	public NoUserTask(HandleClientTask clientTaskHandler) {
		super(clientTaskHandler);
		// TODO Auto-generated constructor stub
	}

	@Override
	public Msg handleTask(Object msg) {
		// if no correct msg was found
		if (msgController.mgsParser(msg) == false)
			return ErrorMsg;
		// action for none connected clients
		switch (msgController.getType()) {
		case LOGIN_REQUEST:
			// get User with userName and Password from db
			try {
				if (dbController.connectUser(msgController.getUserName(), msgController.getPassword())) {
					User user = dbController.getUser(msgController.getUserName());
					clientTaskHandler.setActiveUser(user);
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
		return newMsgToSend;
	}

}
