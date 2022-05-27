package complaints;

import java.util.ArrayList;
<<<<<<< HEAD
import client.ClientController;
import client.MsgController;
=======

<<<<<<< Updated upstream
import common.Status;
=======
import client.ClientController2;
import client.MsgController;
>>>>>>> Stashed changes
>>>>>>> origin/Ronen
import complaint.Complaint;
import common.Status;
import msg.MsgType;



/**
 * manage complaints
 * 
 * @author halel
 *
 */
<<<<<<< HEAD
public class ComplaintsController 
{
	private Complaint complaint;
	private ClientController client;
=======
<<<<<<< Updated upstream
public class ComplaintsController {

	public void createComplaint() {

=======
public class ComplaintsController 
{
	private Complaint complaint;
	private ClientController2 client;
>>>>>>> origin/Ronen
	
	/**
	 * create the Complaint with the fields
	 * server
	 * 
	 * @param complaint
	 * @param answer
	 * @param compensation
	 * @param status
	 */
<<<<<<< HEAD
	public void createComplaint(String responsibleEmployeeUserName, String complaintText, String customerUserName) throws Exception 
	{
		complaint = new Complaint(responsibleEmployeeUserName, complaintText, customerUserName);
		MsgController msgController = client.sendMsg(MsgController.createCREATE_COMPLAINTMsg(complaint));
		if(msgController.getType() != MsgType.CREATE_COMPLAINT)
			throw new Exception("cannot create new complaint");
		
=======
	public void createComplaint(String answer, double compensation, Status status)
	{
		complaint = new Complaint(answer, compensation, status);
		MsgController msgController = client.sendMsg(msgController.createCREATE_COMPLAINTMsg(complaint));
		if(msgController.getType() != MsgType.CREATE_COMPLAINT)
			msgController.createERRORMsg();
		
>>>>>>> Stashed changes
>>>>>>> origin/Ronen
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
	public void handleComplaint(Complaint complaint, String answer, double compensation, Status status) throws Exception 
	{
		complaint.setAnswer(answer);
		complaint.setCompensation(compensation);
		complaint.setStatus(status);
		MsgController msgController = client.sendMsg(MsgController.createUPDATE_COMPLAINTMsg(complaint));
		if(msgController.getType() != MsgType.UPDATE_COMPLAINT)
			throw new Exception("cannot update the complaint");
	}

	/**
	 * get all the complaints related to the employee
	 * @param employeeID
	 * @return
	 */
	public ArrayList<Complaint> getAllComplaints() 
	{ 
		MsgController msgController = client.sendMsg(MsgController.createGET_ALL_COMPLAINTSMsg());
		if(msgController.getType() == MsgType.RETURN_ALL_COMPLAINTS)
			return msgController.getComplaints();
		else
			return null;
	}
}
