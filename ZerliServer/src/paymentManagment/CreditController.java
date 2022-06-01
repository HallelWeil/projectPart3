package paymentManagment;

import database.DBController;

public class CreditController {
	DBController dbController = DBController.getInstance();

	public double getShopCredit(String id) {
		double credit = dbController.getShopCredit(id);
		if (credit == -1)
			return 0;
		else
			return credit;
	}

	public boolean useShopCredit(String id, double amount) {
		// 1. get the credit
		double credit = dbController.getShopCredit(id);
		if (credit == -1)
			return false;// no credit was found
		if (credit < amount)
			return false;// not enough credit
		// 2. use the credit
		return dbController.updateShopCredit(id, -amount);
	}

	public void refund(String id, double refundAmount) {
		// 1. check if credit exist for the customer
		if (dbController.getShopCredit(id) == -1) {
			// 2. no credit exist, add the refund as credit
			dbController.saveShopCreditToDB(id, refundAmount);
		} else {
			// 2. credit exist, add the refund amount
			dbController.updateShopCredit(id, refundAmount);
		}

	}
}
