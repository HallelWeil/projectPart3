package usersManagment;

import database.DBController;
import paymentManagment.PaymentController;
import user.*;

public class UserManagmentController {
	private DBController dbController = DBController.getInstance();

	public void addCard(String userName, String cardInfo) throws Exception {
		// 1. check if the card info is ok
		PaymentController paymentController = new PaymentController();
		if (!paymentController.pay(cardInfo, 0.001)) {
			throw new Exception("The card info is bad!");
		}
		// 2. check if the customer already have a card
		String temp = dbController.getCardInfo(userName);
		if (temp == null) {
			// add new card
			if (!dbController.saveCardInfo(userName, cardInfo)) {
				throw new Exception("Failed to save the card info");
			}
		} else {
			if (!dbController.updateCardInfo(userName, cardInfo)) {
				throw new Exception("Failed to update the card info");
			}
		}
	}

	public void updateUserData(User user) throws Exception {
		// 1. update the user data
		if (!dbController.updateAllUserData(user)) {
			throw new Exception("Failed to update the user info");
		}
	}

}
