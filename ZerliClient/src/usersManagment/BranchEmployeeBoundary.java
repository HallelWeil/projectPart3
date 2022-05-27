package usersManagment;

<<<<<<< Updated upstream
public class BranchEmployeeBoundary extends UserBoundary {

	public void requestEnterSurveyAnswers(int [] answers) {
=======
import java.util.ArrayList;
import client.ClientController2;
import client.MsgController;
import msg.MsgType;
import msg.Msg;



public class BranchEmployeeBoundary extends UserBoundary 
{
	private ClientController2 client;
	private MsgController msgController;
	
	 public void requestEnterSurveyAnswers(int [] answers)
	 {
		 msgController = client.sendMsg(msgController.createADD_SURVEY_ANSWERSMsg(answers));
		if(msgController.getType() != MsgType.ADD_SURVEY_ANSWERS)
			msgController.createERRORMsg();
>>>>>>> Stashed changes
		
	}
}
