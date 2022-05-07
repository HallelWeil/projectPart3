package usersManagment;

/**
 * the users boundary clas, manage login and update user type and access
 * information
 * 
 * @author halel
 *
 */
public class UserBoundary {

	private UserController userController;
	
	/**
	 * call the user controller action to login, return the needed result
	 * 
	 * @param username
	 * @param password
	 * @return
	 */
	public boolean requestLogin(String username, String password) {

		// call the user controller action to login, return the needed result

		return true;
	}

	/**
	 * //call the user controller action to logout,
	 */
	public void requestLogOut() {
		// call the user controller action to logout,
	}

}
