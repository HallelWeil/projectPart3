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

	/**
	 * loginResults saved the returned data from sendMsg method in clientController 
	 */
<<<<<<< HEAD
	private ClientController clientController= ClientController.getInstance();
=======
	private ClientController clientcontroller;
>>>>>>> origin/Ronen
	private MsgController loginResults;
	// public boolean login(String username, String password)

	/**
	 * received the username and password details from a users who extend a userboundary class
	 * then make a login request msg
	 * and send it the the server by clientController 
	 * @param username
	 * @param password
	 * @return
	 */
	public MsgController login(String username, String password) {
<<<<<<< HEAD
		//clientController=clientController.getInstance();
		Msg msg = MsgController.createLOGIN_REQUESTMsg(username, password);
		loginResults =clientController.sendMsg(msg);
=======
		Msg msg = MsgController.createLOGIN_REQUESTMsg(username, password);
		loginResults = clientcontroller.sendMsg(msg);
>>>>>>> origin/Ronen
	//	if (loginResults.getType().equals(MsgType.APPROVE_LOGIN)) {
			return loginResults; //returned what loginResults contains (ERROR with String of error type/User data)
	//	}
	//	return null;
	}

	/**
	 * request a logout from users who extends userboundary. create logout request msg and send 
	 * the request to server by clientController 
	 */
	public void logout() {
		Msg msg = MsgController.createLOG_OUT_REQUESTMsg();
<<<<<<< HEAD
		MsgController loginResults =clientController.sendMsg(msg);
=======
		MsgController loginResults = clientcontroller.sendMsg(msg);
>>>>>>> origin/Ronen
		loginResults = new MsgController(); // to reset the User msgController
		/*
		 * if(!loginResults.getType().equals(MsgType.APPROVE_LOGOUT)) {
		 * 
		 * }
		 */
	}
}
