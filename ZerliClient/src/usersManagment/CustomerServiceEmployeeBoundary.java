package usersManagment;

import java.util.ArrayList;



import complaint.Complaint;
import complaints.ComplaintsController;
import common.Status;
import survey.Survey;
import surveyController.SurveyController;


public class CustomerServiceEmployeeBoundary {

	private ComplaintsController complaintsController;
	private SurveyController surveyController;
	
	public void handlingComplaints(Complaint complaint, String answer, double compensation, Status status) throws Exception 
	{
		complaintsController.handleComplaint(complaint, answer, compensation, status);
	}
	
	/**
	 * get all the employee complaints
	 * @return
	 */
	public ArrayList<Complaint> getMyComplaints()
	{
		return  complaintsController.getAllComplaints();
	}
	
	/**
	 * need to get pdf file and send to the server as a blob
	 * @throws Exception 
	 */
	public void enterSurveyResult(int sNumber, String path) throws Exception //pdf file
	{
		surveyController.enterSurveyResults(sNumber,path);
	}
	
	public void enterSurveyAnswers(int[] answers,int surveyNumber) throws Exception
	{
		surveyController.enterSurveyAnswers(answers,surveyNumber);
	}

	public Survey getSurvey(int surveyNumber) throws Exception
	{
		return surveyController.getSurvey(surveyNumber);
		
	}
	
}
