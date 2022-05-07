package client;

import java.io.Serializable;
import java.util.ArrayList;

import report.*;
import survey.*;
import user.*;


public class ClientController {

	/**
	 * return the user entity if login successfully , throw exception if not
	 * 
	 * @param username
	 * @param password
	 * @return
	 * @throws Exception
	 */
	public User requestLogin(String username, String password) throws Exception {
		return null;

	}

	/**
	 * log out, reset all saved data and so on
	 */
	public void requestLogout() {
		// log out
	}
	
	public boolean requestApproveOrder(int orderNumber, boolean isApproved) {
		return true;
		
	}
	public boolean requestApproveCancelation(int orderNumber, boolean isApproved, double refundAmount) {

		return true;
	}
	
	public boolean requestUpdateUserData(String userName, UserType type, UserStatus status) {

		return true;
	}

	/**
	 * return the report
	 *  get the report using the client controller
	 * @return
	 */
	public Report requestViewReport(ReportType type, String StartMonth) {
		return null;
		// get the report using the client controller
	}


	/**
	 * save the result in the following way: get array list size 7, first number is the surveyID, next 6 are the answers
	 * @param answers
	 */
	public void saveSurveyResult(ArrayList<Integer> answers) {
		//save the survey result in the db using/
		
	}
	/**
	 * create new survey
	 * @param survey
	 */
	public void createNewSurvey(Survey survey) {
		
	}
	
	/**
	 * get all the entities of "type" with the id filed
	 * can be used for orders, complaints and more
	 * @param id
	 * @param type
	 * @return
	 */
	public ArrayList<Serializable> getAll(int id, String type){
		return null;
	}
	
	
}
