package usersManagment;

import client.ClientController;
import client.MsgController;
import msg.Msg;
import msg.MsgType;
import report.*;

public class CEOBoundary extends UserBoundary {
	private ClientController clientController;
	private Msg msg;
	private MsgController msgController;
	
	public Report requestViewReport(ReportType type, String Month,String year,String branch) {
		String branchNameOfUser = loginResults.getUser().getBranchName(); //get branchName from branchManger user 
		Report report = new Report(Month, year, type, branchNameOfUser);
		msg = MsgController.createView_ReportMsg(report);
		msgController=clientController.sendMsg(msg);
		if(msgController.getType().equals(MsgType.RETURN_REPORT));
		{
			return msgController.getReport();
		}
		return null;  //in case returned msg was ERROR for Example mean Report not found or exist 
	}
}
