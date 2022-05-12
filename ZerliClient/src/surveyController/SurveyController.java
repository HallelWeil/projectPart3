package surveyController;

import java.util.ArrayList;

import client.ClientController;
import client.MsgController;
import complaint.Complaint;
import survey.Survey;
import msg.MsgType;
import msg.Msg;

public class SurveyController
{
   
	private Survey survey;
	private ClientController client;
	private MsgController msgController;
	
	/**
	 * need to get pdf file and add it to the blob in the entity, to save in db
	 */
	public void enterSurveyResults() 
	{
		
	}

	// to create new survey, need to get the survey number from the db using client
	// controller
	// then create the survey entity and send it to client controller
	public void CreateSurvey(String[] questions) throws Exception
	{
		
		if(questions.length == 6)
		survey = new Survey(questions[0], questions[1], questions[2], questions[3], questions[4], questions[5]);
		 msgController = client.sendMsg(msgController.createCREATE_SURVEYMsg(survey));
		 if(msgController.getType() != MsgType.CREATE_SURVEY)
				msgController.createERRORMsg();
		 
	}

	public void enterSurveyAnswers(int[] answers)
	{
		// save the survey result usnig clientcontroller.saveSurveyResult
		if(answers.length == 6)
		{
		msgController = client.sendMsg(msgController.createADD_SURVEY_ANSWERSMsg(answers));
		if(msgController.getType() != MsgType.ADD_SURVEY_ANSWERS)
			msgController.createERRORMsg();
		}
		else
			msgController.createERRORMsg();
	}
	
	public Survey getSurvey(int surveyNumber)
	{
		msgController = client.sendMsg(msgController.getSurvey(surveyNumber));
		if(msgController.getType() != MsgType.GET_SURVEY)
			msgController.createERRORMsg();
		return msgController.getSurvey(surveyNumber);
	}
	
	/**
	 * get all the Surveys 
	 * @param 
	 * @return
	 */
	public ArrayList<Survey> getAllSurvey()
	{ 
		MsgController msgController = client.sendMsg(msgController.createGET_ALL_SURVEYMsg());
		if(msgController.getType() == MsgType.RETURN_ALL_COMPLAINTS)
			return msgController.getSurveys();
		else
			msgController.createERRORMsg();
	}
	}

}
