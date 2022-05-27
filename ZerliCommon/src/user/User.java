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

<<<<<<< HEAD
=======
<<<<<<< Updated upstream
=======
>>>>>>> origin/Ronen
	public String getBranchName() {
		return branchName;
	}

	public void setBranchName(String branchName) {
		this.branchName = branchName;
	}

	public void setUsername(String username) {
		this.username = username;
	}

<<<<<<< HEAD
	public void setPassword(String password) {
		this.password = password;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public void setPersonID(String personID) {
		this.personID = personID;
	}

	
=======
	
>>>>>>> Stashed changes
>>>>>>> origin/Ronen
}
