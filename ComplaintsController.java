package complaints;

import java.util.ArrayList;

import client.ClientController;
import client.MsgController;
import complaint.Complaint;
import msg.MsgType;


/**
 * manage complaints
 * 
 * @author halel
 *
 */
public class ComplaintsController 
{
	private Complaint complaint;
	private ClientController client;
	
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
	public void handleComplaint(Complaint complaint, String answer, double compensation, Status status)
	{
		complaint.setAnswer(answer);
		complaint.setCompensation(compensation);
		complaint.setStatus(status);
		MsgController msgController = client.sendMsg(msgController.createUPDATE_COMPLAINTMsg(complaint));
		if(msgController.getType() != MsgType.UPDATE_COMPLAINT)
			msgController.createERRORMsg();
	}

	/**
	 * get all the complaints related to the employee
	 * @param employeeID
	 * @return
	 */
	public ArrayList<Complaint> getAllComplaints()
	{ 
		MsgController msgController = client.sendMsg(msgController.createGET_ALL_COMPLAINTSMsg());
		if(msgController.getType() == MsgType.RETURN_ALL_COMPLAINTS)
			return msgController.getComplaints();
		else
			msgController.createERRORMsg();
	}
}
