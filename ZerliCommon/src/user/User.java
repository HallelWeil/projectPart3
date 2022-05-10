package user;

import java.io.Serializable;

public class User implements Serializable {

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
	/**
	 * for branch employee and branch manager only
	 */
	private String branchName;

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public String getUsername() {
		return username;
	}

	public String getPassword() {
		return password;
	}

	public UserType getUserType() {
		return userType;
	}

	public boolean isConnected() {
		return connected;
	}

	public String getFirstName() {
		return firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public String getEmail() {
		return email;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public String getPersonID() {
		return personID;
	}

	public UserStatus getStatus() {
		return status;
	}

	public void setUserType(UserType userType) {
		this.userType = userType;
	}

	public void setConnected(boolean connected) {
		this.connected = connected;
	}

	public void setStatus(UserStatus status) {
		this.status = status;
	}

	public String getBranchName() {
		return branchName;
	}

	public void setBranchName(String branchName) {
		this.branchName = branchName;
	}

	
}
