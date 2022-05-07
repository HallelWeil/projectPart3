package usersManagment;

import java.util.ArrayList;

import complaint.Complaint;
import complaints.ComplaintsController;
import survey.Survey;
import surveyController.SurveyController;


public class CustomerServiceEmployeeBoundary {

	private ComplaintsController complaintsController;
	private SurveyController surveyController;
	
	public void handlingComplaints() {
		
	}
	/**
	 * get all the employee comlaints
	 * @return
	 */
	public ArrayList<Complaint> getMyComplaints(){
		return null;
		
	}
	/**
	 * need to get pdf file and send to the server as a blob
	 */
	public void enterSurveyResult()//pdf file)
	{
		
	}
	public void enterSurveyAnswers(int answers) {
		
	}

	public Survey getSurvey(int surveyNumber) {
		return null;
		
	}
	
}
