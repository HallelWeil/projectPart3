package complaintsManagment;

import common.Status;
import complaint.Complaint;
import database.DBController;
import paymentManagment.CreditController;

public class ComplaintsController {
	DBController dbController = DBController.getInstance();

	public void handleComplaintAnswer(Complaint inputComplaint) throws Exception {
		// 1. get the complaint from the database
		Complaint complaint = dbController.getComplaint(inputComplaint.getComplaintsNumber());
		if (complaint == null) {
			throw new Exception("The complaint was not found!");
		}
		// 2. check the complaint status
		if (complaint.getStatus() != Status.Active) {
			throw new Exception("The complaint is already " + complaint.getStatus());
		}
		// 3. update the complaint in the database
		if (!dbController.updateComplaint(inputComplaint.getAnswer(), inputComplaint.getComplaintsNumber(),
				inputComplaint.getStatus())) {
			throw new Exception("Failed to update the complaints, try again later");
		}
		// 4. now handle the refund
		if (inputComplaint.getCompensation() > 0) {
			CreditController creditController = new CreditController();
			creditController.refund(complaint.getCustomerID(), inputComplaint.getCompensation());
		}
	}
	
	public boolean createComplaint(Complaint complaint) {
		if(dbController.createComplaint(complaint)!=-1)
			return true;
		return false;
	}

}
