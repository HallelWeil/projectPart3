package scheduledTasks;

import java.sql.Timestamp;

import remindersManagment.ReminderController;

/**
 * Scheduled reminder, in the given time a reminder will be sent. can be sms or
 * email or both
 *
 */
public class ScheduledReminder extends ScheduledTask {

	private String SMSReminderText;
	private String EmailReminderText;
	private String phoneNumber;
	private String emailAddress;
	/**
	 * true if sms
	 */
	private boolean isSMS;
	/**
	 * true if its email
	 */
	private boolean isEmail;

	/**
	 * reminder scheduled to the given time
	 * 
	 * @param tasktime
	 */
	public ScheduledReminder(Timestamp tasktime) {
		super(tasktime);
		isSMS = false;
		isEmail = false;
	}

	/**
	 * add sms text and phone number to send to
	 * 
	 * @param phoneNumber
	 * @param reminderText
	 */
	public void setSMS(String phoneNumber, String reminderText) {
		this.SMSReminderText = reminderText;
		this.phoneNumber = phoneNumber;
		isSMS = true;
	}

	/**
	 * add email text and email to send it to
	 * 
	 * @param emailAddress
	 * @param reminderText
	 */
	public void setEmail(String emailAddress, String reminderText) {
		this.emailAddress = emailAddress;
		this.EmailReminderText = reminderText;
		isEmail = true;
	}

	@Override
	public void run() {
		ReminderController reminderController = new ReminderController();
		if (isEmail)
			reminderController.sendEmail(emailAddress, EmailReminderText);
		if (isSMS)
			reminderController.sendSMS(phoneNumber, SMSReminderText);
	}

}
