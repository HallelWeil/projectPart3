package paymentManagment;
/**
 * not implemented, for future update
 * manage paymnet and refund
 * @author halel
 *
 */
public class PaymentController {

	/**
	 * pay the given amount using the credit cart info
	 * @param cardInfo
	 * @return
	 * @throws Exception 
	 */
	public boolean pay(String cardInfo,double amount) throws Exception {
		if(amount <= 0)
			throw new Exception("Cant pay "+ amount);
		//if the credit card info is bad return false
		//pay the amount
		return true;
	}
	
	/**
	 * refund the given amount
	 * @param cardInfo
	 * @param amount
	 * @return
	 * @throws Exception
	 */
	public boolean refund(String cardInfo,double amount) throws Exception {
		if(amount <= 0)
			throw new Exception("Cant refund "+ amount);
		//if the credit card info is bad return false
		//refund the amount
		return true;
	}
}
