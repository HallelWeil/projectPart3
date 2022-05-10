package complaints;

import java.util.ArrayList;

import client.ClientController;
import client.MsgController;
import common.Status;
import complaint.Complaint;
import msg.MsgType;

/**
 * manage complaints
 * 
 * @author halel
 *
 */
public class ComplaintsController {

	private ClientController clinet;
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
		Complaint complaint= new Complaint();
		MsgController.createCREATE_COMPLAINTMsg(complaint);
		MsgType.COMPLETED;
	}

	/**
	 * get a Complaint with the given number
	 * @param complaintnumber
	 */
	public void getComplaints(int complaintnumber) {

		MsgController msgController = clinet.sendMsg(MsgController.createGET_ALL_COMPLAINTSMsg());
		if(msgController.getType()==MsgType.RETURN_ALL_COMPLAINTS) {
			msgController.getComplaints();
		}
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
