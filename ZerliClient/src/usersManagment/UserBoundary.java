package usersManagment;

import client.MsgController;

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
         if(loginResults!=null)
         {
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
		loginResults=
	}

}
