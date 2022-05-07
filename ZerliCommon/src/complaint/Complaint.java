package complaint;

import java.io.Serializable;

import common.Status;

public class Complaint implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private int complaintsNumber;
	private int responsibleEmployeeID;
	private String complaint;
	private String answer;
	private double compensation;
	private Status status;
	private int customerID;

	public Complaint(int responsibleEmployeeID, String complaint, int customerID) {
		super();
		this.complaintsNumber = -1;
		this.responsibleEmployeeID = responsibleEmployeeID;
		this.complaint = complaint;
		this.customerID = customerID;
		status = Status.Active;
		compensation = 0;
	}

	public int getComplaintsNumber() {
		return complaintsNumber;
	}

	public void setComplaintsNumber(int complaintsNumber) {
		this.complaintsNumber = complaintsNumber;
	}

	public String getAnswer() {
		return answer;
	}

	public void setAnswer(String answer) {
		this.answer = answer;
	}

	public int getCustomerID() {
		return customerID;
	}

	public double getCompensation() {
		return compensation;
	}

	public void setCompensation(double compensation) {
		this.compensation = compensation;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public String getComplaint() {
		return complaint;
	}

}
