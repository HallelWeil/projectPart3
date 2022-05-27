package complaints;

import java.util.ArrayList;

<<<<<<< Updated upstream
import common.Status;
=======
import client.ClientController2;
import client.MsgController;
>>>>>>> Stashed changes
import complaint.Complaint;

/**
 * manage complaints
 * 
 * @author halel
 *
 */
<<<<<<< Updated upstream
public class ComplaintsController {

	public void createComplaint() {

=======
public class ComplaintsController 
{
	private Complaint complaint;
	private ClientController2 client;
	
	/**
	 * create the Complaint with the fields
	 * server
	 * 
	 * @param complaint
	 * @param answer
	 * @param compensation
	 * @param status
	 */
	public void createComplaint(String answer, double compensation, Status status)
	{
		complaint = new Complaint(answer, compensation, status);
		MsgController msgController = client.sendMsg(msgController.createCREATE_COMPLAINTMsg(complaint));
		if(msgController.getType() != MsgType.CREATE_COMPLAINT)
			msgController.createERRORMsg();
		
>>>>>>> Stashed changes
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
