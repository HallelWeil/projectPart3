package remindersManagment;

/**
 * Manage reminders, send SMS or Email. not implemented for future use.
 * 
 * @author halel
 *
 */
public class ReminderController {

	private String SMSend = "";
	private String mailStart = "";

	/**
	 * Send EMail
	 * 
	 * @param emailAddress
	 * @param msgText      text to send
	 */
	public void sendEmail(String emailAddress, String msgText) {
		// send the email
		// with the given text
	}

	/**
	 * Send SMS
	 * 
	 * @param phoneNumber
	 * @param msgText     text to send
	 */
	public void sendSMS(String phoneNumber, String msgText) {
		// send sms
		// with the given text
	}
}
