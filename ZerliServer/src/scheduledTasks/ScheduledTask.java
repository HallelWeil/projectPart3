package scheduledTasks;

import java.sql.Timestamp;

/**
 * represent scheduled task, needed to be at a specific time
 * 
 * @author halel
 *
 */
abstract class ScheduledTask {
	private Timestamp tasktime;

	/**
	 * handle the task, should be called on or after the task time
	 */
	public abstract void handleTheTask();

	/**
	 * get the time left antil the task in milliseconds
	 */
	public long returnTimeLeftInMilli() {
		long time = tasktime.getTime() - System.currentTimeMillis();
		return time;
	}
}
