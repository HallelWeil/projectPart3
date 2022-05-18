package surveyController;

import java.util.ArrayList;
import java.Exception;

import client.ClientController;
import client.MsgController;
import complaint.Complaint;
import complaints.Exception;
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
	public void enterSurveyResults(int sNumber, String path) throws Exception
	{
		FileController FileController = new FileController();
		FileController.savePdfFileToObject(path, sNumber);
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
			 throw new Exception("cannot Create survey");
		 
	}

	public void enterSurveyAnswers(int[] answers) throws Exception
	{
		// save the survey result usnig clientcontroller.saveSurveyResult
		if(answers.length == 6)
		{
		msgController = client.sendMsg(msgController.createADD_SURVEY_ANSWERSMsg(answers));
		if(msgController.getType() != MsgType.ADD_SURVEY_ANSWERS)
			throw new Exception("cannot enter Survey answers");
		}
		else
			throw new Exception("incorrect number of answers");
	}
	
	public Survey getSurvey(int surveyNumber) throws Exception
	{
		msgController = client.sendMsg(msgController.getSurvey(surveyNumber));
		if(msgController.getType() != MsgType.GET_SURVEY)
			throw new Exception("cannot get survey");
		return msgController.getSurvey(surveyNumber);
	}
	
	/**
	 * get all the Surveys 
	 * @param 
	 * @return
	 */
	public ArrayList<Survey> getAllSurvey() throws Exception
	{ 
		MsgController msgController = client.sendMsg(msgController.createGET_ALL_SURVEYMsg());
		if(msgController.getType() == MsgType.RETURN_ALL_COMPLAINTS)
			return msgController.getSurveys();
		else
			throw new Exception("cannot get all survey");
	}
	}

}
