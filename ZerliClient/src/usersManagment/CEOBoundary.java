package usersManagment;
<<<<<<< HEAD
import client.ClientController;
=======

import client.ClientController;

>>>>>>> origin/Ronen
import client.MsgController;
import msg.Msg;
import msg.MsgType;
import report.*;

public class CEOBoundary extends UserBoundary {
<<<<<<< HEAD
	private ClientController clientController= ClientController.getInstance();
	private Msg msg;
	private MsgController msgController;
	
	public Report requestViewReport(ReportType type, int Month,int year,String branch) {
		msg = MsgController.createGET_REPORTMsg(type,year,Month,branch);
=======
	private ClientController clientController;
	private Msg msg;
	private MsgController msgController;
	
	public CEOBoundary() {
		clientController=new ClientController();
	}
	
	public Report requestViewReport(ReportType type, int Month,int year,String branch) {
		//String branchNameOfUser = CurrentUser.getBranchName(); //get branchName 
		msg = MsgController.createGET_REPORTMsg(type,year,Month, branch);//branch be null in case we want for all branches
>>>>>>> origin/Ronen
		msgController=clientController.sendMsg(msg);
		if(msgController.getType().equals(MsgType.RETURN_REPORT))
		{
			return msgController.getReport();
		}
		return null;  //in case returned msg was ERROR for Example mean Report not found or exist 
	}
}
