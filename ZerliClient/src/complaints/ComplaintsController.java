package complaints;

import java.util.ArrayList;

import common.Status;
import complaint.Complaint;

/**
 * manage complaints
 * 
 * @author halel
 *
 */
public class ComplaintsController {

	public void createComplaint() {

	}

	/**
	 * update the Complaint with the fields, and send the new Complaint to the
	 * server
	 * 
	 * @param complaint
	 * @param answer
	 * @param compensation
	 * @param status
	 */
	public void handleComplaint(Complaint complaint, String answer, double compensation, Status status) {
		//
	}

	/**
	 * get a Complaint with the given number
	 * @param complaintnumber
	 */
	public void getComplaints(int complaintnumber) {

	}
	
	/**
	 * get all the complaints related to the employee
	 * @param employeeID
	 * @return
	 */
	public ArrayList<Complaint> getAllComplaints(int employeeID){
		return null;
		
	}
}
