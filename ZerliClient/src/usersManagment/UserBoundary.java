package usersManagment;

import client.MsgController;
import msg.MsgType;
import user.User;

/**
 * the users boundary clas, manage login and update user type and access
 * information
 * 
 * @author halel
 *
 */
public class UserBoundary {

	private UserController userController;
//in case we need the user details like UserID or Type to understand witch User GUI we should open 
	public static MsgController loginResults;
	public static User CurrentUser;
	
	public UserBoundary() {
		userController=new UserController();
	}
	
	/**
	 * call the user controller action to login, return the needed result
	 * 
	 * @param username
	 * @param password
	 * @return
	 */
	public boolean requestLogin(String username, String password) {
        
		// call the user controller action to login, return the needed result
         loginResults=userController.login(username, password);
         if(loginResults.getType().equals(MsgType.APPROVE_LOGIN)) 
         {
        	 CurrentUser=loginResults.getUser(); //CurrentUser contains the user data 
        	 return true;
         }
         return false;
	}

	/**
	 * //call the user controller action to logout,
	 */
	public void requestLogOut() {
		// call the user controller action to logout,
		userController.logout();
		CurrentUser=new User(); //reset the user
		loginResults=new MsgController();
	}

}
