package surveyController;

import java.util.ArrayList;

import client.ClientController;
import client.MsgController;
import files.FilesController;
import survey.Survey;
import msg.MsgType;

public class SurveyController {

	private Survey survey;
	private ClientController client;
	private MsgController msgController;

	public SurveyController() {
		client = ClientController.getInstance();
		msgController = new MsgController();
	}

	/**
	 * need to get pdf file and add it to the blob in the entity, to save in db
	 */
	public void enterSurveyResults(int sNumber, String path) throws Exception {
		FilesController fileController = new FilesController();
		fileController.savePdfFileToObject(path, "Survey_" + sNumber + "_Result");
	}

	/**
	 * to create new survey, need to get the survey number from the db using client
	 * controller then create the survey entity and send it to client controller
	 * 
	 * @param questions
	 * @throws Exception
	 */
	public void CreateSurvey(String[] questions) throws Exception {

		if (questions.length == 6)
			survey = new Survey(questions[0], questions[1], questions[2], questions[3], questions[4], questions[5]);
		msgController = client.sendMsg(MsgController.createCREATE_SURVEYMsg(survey));
		if (msgController.getType() != MsgType.CREATE_SURVEY)
			throw new Exception("cannot Create survey");

	}

	public void enterSurveyAnswers(int[] answers, int surveyNumber) throws Exception {
		// save the survey result usnig clientcontroller.saveSurveyResult
		if (answers.length == 6) {
			ArrayList<Integer> answersArrayList = new ArrayList<Integer>();
			answersArrayList.add(surveyNumber);
			for (int i = 1; i < 6; i++) {
				answersArrayList.add(answers[i]);
			}
			msgController = client.sendMsg(MsgController.createADD_SURVEY_ANSWERSMsg(answersArrayList));
			if (msgController.getType() != MsgType.ADD_SURVEY_ANSWERS)
				throw new Exception("cannot enter Survey answers");
		} else
			throw new Exception("incorrect number of answers");
	}

	public Survey getSurvey(int surveyNumber) throws Exception {
		msgController = client.sendMsg(MsgController.createGET_SURVEYMsg(surveyNumber));
		if (msgController.getType() != MsgType.GET_SURVEY)
			throw new Exception("cannot get survey");
		return msgController.getSurvey();
	}

	/**
	 * get all the Surveys
	 * 
	 * @param
	 * @return
	 */
	public ArrayList<Survey> getAllSurvey() throws Exception {
		MsgController msgController = client.sendMsg(MsgController.createGET_ALL_SURVEYMsg());
		if (msgController.getType() == MsgType.RETURN_ALL_COMPLAINTS)
			return msgController.getSurveys();
		else
			throw new Exception("cannot get all survey");
	}
}
