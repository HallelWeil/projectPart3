package usersManagment;

import client.ClientController;
import client.MsgController;
import msg.Msg;
import msg.MsgType;
import user.User;

/**
 * use the clientcontroller to get the requests to the server and handle the
 * result
 * 
 * @author halel
 *
 */
public class UserController {

	private ClientController clientcontroller;
	private MsgController loginResults;
	// public boolean login(String username, String password)

	/**
	 * 
	 * @param username
	 * @param password
	 * @return
	 */
	public MsgController login(String username, String password) {
		Msg msg = MsgController.createLOGIN_REQUESTMsg(username, password);
		loginResults = clientcontroller.sendMsg(msg);
		if (loginResults.getType().equals(MsgType.APPROVE_LOGIN)) {
			return loginResults;
		}
		return null;
	}

	public void logout() {
		Msg msg = MsgController.createLOG_OUT_REQUESTMsg();
		MsgController loginResults = clientcontroller.sendMsg(msg);
		loginResults = new MsgController(); // to reset the User msgController
		/*
		 * if(!loginResults.getType().equals(MsgType.APPROVE_LOGOUT)) {
		 * 
		 * }
		 */
	}
}
