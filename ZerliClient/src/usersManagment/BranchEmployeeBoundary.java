package usersManagment;

import client.ClientController;
import surveyController.SurveyController;

public class BranchEmployeeBoundary extends UserBoundary {
	private ClientController client;
	private SurveyController surveyController;

	public BranchEmployeeBoundary() {
		client = ClientController.getInstance();
		surveyController = new SurveyController();
	}

	public void enterSurveyAnswers(int[] answers, int surveyNumber) throws Exception {
		surveyController.enterSurveyAnswers(answers, surveyNumber);
	}
}
