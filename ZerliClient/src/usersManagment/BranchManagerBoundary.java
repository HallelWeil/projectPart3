package usersManagment;

import client.ClientController;
import report.Report;
import report.ReportType;
import user.UserStatus;
import user.UserType;

/**
 * use the branch manager contoller and the different controllers for the needed
 * actions
 * 
 * @author halel
 *
 */
public class BranchManagerBoundary extends UserBoundary {

	private ClientController clientController;

	/**
	 * request the order approval, using order number and approve\not approve
	 * 
	 * @param orderNumber
	 * @return true if the request succeed
	 */
	public boolean requestApproveOrder(int orderNumber, boolean isApproved) {
		return true;
	}

	/**
	 * request the order cancellation approval, using order number and approve\not
	 * approve
	 * 
	 * @param orderNumber
	 * @return true if the request succeed
	 */
	public boolean requestApproveCancelation(int orderNumber, boolean isApproved, double refundAmount) {

		return true;
	}

	/**
	 * update user type + status, can be null if only wanted to update one of the
	 * fields return boolean if the request succeed
	 * 
	 * @param userName
	 * @param type
	 * @param status
	 * @return
	 */
	public boolean requestUpdateUserData(String userName, UserType type, UserStatus status) {

		return true;
	}

	/**
	 * return the report get the report using the client controller
	 * 
	 * @return
	 */
	public Report requestViewReport(ReportType type, String Month) {
		return null;
		// get the report using the client controller
	}

}
