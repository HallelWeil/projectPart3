package survey;

import java.io.Serializable;

/**
 * each survey is 6 questions, with the answers 1-10 to create a new survey:
 * place 6 questions in the constructor to add result: add 6 numbers from 1-10,
 * each for each question the survey result are the average of the answers
 * 
 * @author halel
 *
 */
public class Survey implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * the survey id
	 */
	int surveyNumber;
	/**
	 * the survey questions(6 questions)
	 */
	private String[] questions;
	/**
	 * for each question, the answers result(sum of the answers)
	 */
	private int[] result;
	/**
	 * the number of the survey's participants
	 */
	private int numberOfParticipants;

	public Survey(String question1, String question2, String question3, String question4, String question5,
			String question6) {
		questions = new String[6];
		result = new int[6];
		for (int i = 0; i < 6; i++)
			result[i] = 0;
		numberOfParticipants = 0;
		questions[0] = question1;
		questions[1] = question2;
		questions[2] = question3;
		questions[3] = question4;
		questions[4] = question5;
		questions[5] = question6;
	}

	public void setSurveyNumber(int surveyNumber) {
		this.surveyNumber = surveyNumber;
	}

	/**
	 * add the answers to the survey result
	 * 
	 * @param answers -> array with length 6, for the 6 answers must be number
	 *                between 1 to 10
	 * @throws Exception when the answers array is not ok
	 */
	public void addAnswers(int[] answers) throws Exception {
		if (!(answers.length == 6)) {
			throw new Exception("Must be 6 answers!");
		}
		for (int i = 0; i < 6; i++) {
			if (answers[i] > 10 || answers[i] < 0)
				throw new Exception("Answer " + (i + 1) + "out of range, must be value between 1 to 10");
		}
		// the answers are in range, save the answers in the survey result
		for (int i = 0; i < 6; i++) {
			result[i] += answers[i];
		}
		numberOfParticipants++;
	}

	/**
	 * create string array, 7 string
	 * 
	 * @return string array with length 7
	 */
	public String[] getResult() {

		String[] reultString = new String[7];
		reultString[0] = "survey #" + surveyNumber + " :";
		for (int i = 1; i < 7; i++) {
			reultString[i] = "Q" + i + " : " + questions[i] + " result are: "
					+ (double) result[i] / numberOfParticipants;
		}
		return reultString;
	}

	public String[] getQuestions() {
		return questions;
	}

}
