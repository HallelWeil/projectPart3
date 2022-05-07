package surveyController;

public class SurveyController {

	/**
	 * need to get pdf file and add it to the blob in the entity, to save in db
	 */
	public void enterSurveyResults() {

	}

	public void CreateSurvey(String[] questions) {
		// to create new survey, need to get the survey number from the db using client
		// controller
		// then create the survey entity and send it to client controller
	}

	public void enterSurveyAnswers(int[] answers) {
		// save the survey result usnig clientcontroller.saveSurveyResult
	}

}
