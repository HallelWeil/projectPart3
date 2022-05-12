package usersManagment;

import java.util.ArrayList;


import complaint.Complaint;
import complaints.ComplaintsController;
import complaints.Status;
import survey.Survey;
import surveyController.SurveyController;


public class CustomerServiceEmployeeBoundary {

	private ComplaintsController complaintsController;
	private SurveyController surveyController;
	
	public void handlingComplaints(Complaint complaint, String answer, double compensation, Status status) 
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
	 */
	public void enterSurveyResult() //pdf file
	{
		surveyController.enterSurveyResults();
	}
	
	public void enterSurveyAnswers(int[] answers)
	{
		surveyController.enterSurveyAnswers(answers);
	}

	public Survey getSurvey(int surveyNumber)
	{
		return surveyController.getSurvey(surveyNumber);
		
	}
	
}
