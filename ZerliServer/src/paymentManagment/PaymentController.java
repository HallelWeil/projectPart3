package paymentManagment;

/**
 * not implemented, for future update manage payment and refund
 * 
 * @author halel
 *
 */
public class PaymentController {

	/**
	 * pay the given amount using the credit cart info
	 * 
	 * @param cardInfo
	 * @param amount   the amount to charge
	 * @return true on success
	 * @throws Exception if the card info is not ok
	 */
	public boolean pay(String cardInfo, double amount) throws Exception {
		if (amount <= 0)
			throw new Exception("Cant pay " + amount);
		// if the credit card info is bad return false
		// pay the amount
		return true;
	}

	/**
	 * refund the given amount
	 * 
	 * @param cardInfo
	 * @param amount   the amount to refund
	 * @return true on success
	 * @throws Exception if the card info is not ok
	 */
	public boolean refund(String cardInfo, double amount) throws Exception {
		if (amount <= 0)
			throw new Exception("Cant refund " + amount);
		// if the credit card info is bad return false
		// refund the amount
		return true;
	}
}
