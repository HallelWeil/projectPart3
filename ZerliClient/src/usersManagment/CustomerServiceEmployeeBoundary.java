package usersManagment;

import java.util.ArrayList;

import client.ClientController;
import client.MsgController;
import complaint.Complaint;
import complaints.ComplaintsController;
import files.FilesController;
import files.SimpleFile;
import msg.MsgType;
import order.Order;
import common.Status;
import survey.Survey;
import surveyController.SurveyController;

public class CustomerServiceEmployeeBoundary {

	private ComplaintsController complaintsController = new ComplaintsController();
	private SurveyController surveyController = new SurveyController();
	private ClientController client = ClientController.getInstance();

	public void createComplaints(String responsibleEmployeeUserName, String complaintText, String customerUserName)
			throws Exception {
		complaintsController.createComplaint(responsibleEmployeeUserName, complaintText, customerUserName);
	}

	public void handlingComplaints(Complaint complaint, String answer, double compensation, Status status)
			throws Exception {
		complaintsController.handleComplaint(complaint, answer, compensation, status);
	}

	/**
	 * get all the employee complaints
	 * 
	 * @return
	 */
	public ArrayList<Complaint> getMyComplaints() {
		return complaintsController.getAllComplaints();
	}

	/**
	 * need to get pdf file and send to the server as a blob
	 * 
	 * @throws Exception
	 */
	public void enterSurveyResult(int sNumber, String path) throws Exception // pdf file
	{
		surveyController.enterSurveyResults(sNumber, path);
	}

	public void enterSurveyAnswers(int[] answers, int surveyNumber) throws Exception {
		surveyController.enterSurveyAnswers(answers, surveyNumber);
	}

	public Survey getSurvey(int surveyNumber) throws Exception {
		return surveyController.getSurvey(surveyNumber);
	}

	public void saveSurveyResultToLocalFile(SimpleFile sm, String path) {
		surveyController.saveSurveyResultToLocalFile(sm,path);
	}
	/**
	 * get all the Surveys
	 * 
	 * @param
	 * @return
	 */
	public ArrayList<Survey> getAllSurvey() throws Exception {
		MsgController msgController = client.sendMsg(MsgController.createGET_ALL_SURVEYMsg());
		if (msgController.getType() == MsgType.RETURN_ALL_SURVEY)
			return msgController.getSurveys();
		else
			throw new Exception("cannot get all survey");
	}

}
