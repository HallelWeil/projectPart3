package user;

import java.io.Serializable;

public class User implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String username;
	private String password;
	private UserType userType;
	private boolean connected;
	private String firstName;
	private String lastName;
	private String email;
	private String phoneNumber;
	private String personID;
	private UserStatus status;
}
