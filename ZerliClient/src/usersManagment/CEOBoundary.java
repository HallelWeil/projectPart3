package usersManagment;
import client.ClientController;
import client.MsgController;
import msg.Msg;
import msg.MsgType;
import report.*;

public class CEOBoundary extends UserBoundary {
	private ClientController clientController= ClientController.getInstance();
	private Msg msg;
	private MsgController msgController;
	
	public Report requestViewReport(ReportType type, int Month,int year,String branch) {
		msg = MsgController.createGET_REPORTMsg(type,year,Month,branch);
		msgController=clientController.sendMsg(msg);
		if(msgController.getType().equals(MsgType.RETURN_REPORT))
		{
			return msgController.getReport();
		}
		return null;  //in case returned msg was ERROR for Example mean Report not found or exist 
	}

	public String[] getBranches() {
		// TODO Auto-generated method stub
		return null;
	}
}
