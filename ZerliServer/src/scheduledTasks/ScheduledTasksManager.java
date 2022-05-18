package scheduledTasks;

import java.io.IOException;
import java.nio.CharBuffer;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Comparator;

import complaint.Complaint;
import database.DBController;
import report.Report;
import report.ReportType;
import user.User;

/**
 * manage all the scheduled tasks. singleton. there is only one tasks manager.
 * any thread can add tasks and the manager will be updated accordingly. create
 * threads to run each scheduled task when its time. get all the needed tasks
 * from the database
 * 
 *
 */
public class ScheduledTasksManager implements Runnable {

	private static final long DAY_IN_MILLISECONDS = 86400000;

	private DBController dbController;

	/**
	 * The single instance of ScheduledTasksManager
	 */
	private static ScheduledTasksManager scheduledTasksManager;

	/**
	 * sorted arraylist, the earliest task appear first in the array, access is
	 * synchronized
	 */
	private ArrayList<ScheduledTask> tasks;

	/**
	 * true if the list is sorted
	 */
	private boolean isSorted;

	private boolean run;

	/**
	 * private constructor. init the empty list
	 */
	private ScheduledTasksManager() {
		tasks = new ArrayList<ScheduledTask>();
		isSorted = true;
		run = true;
	}

	/**
	 * get the scheduled tasks manager instance. create one if its not already exist
	 * 
	 * @return
	 */
	public static ScheduledTasksManager getInstance() {
		if (scheduledTasksManager == null) {
			scheduledTasksManager = new ScheduledTasksManager();
		}
		return scheduledTasksManager;
	}

	/**
	 * should run while the system is active, manage all the scheduled tasks, sleep
	 * between tasks
	 */
	@Override
	public void run() {
		run = true;
		// on start, get all the tasks from the database and add the default task
		getTasks();
		// the time antil the next task
		long timeToWait = 0;
		// run forever,sleep when not needed
		while (run) {
			// check the first in line
			synchronized (tasks) {
				if (tasks.isEmpty()) {
					timeToWait = DAY_IN_MILLISECONDS;
				} else {
					ScheduledTask task = tasks.get(0);
					// if the top task really needed to be done, run it
					if (task.getTimeLeftInMilli(System.currentTimeMillis()) == 0) {
						tasks.remove(0);
						Thread thread = new Thread(task);
						thread.start();
						timeToWait = 0;
					} else {
						timeToWait = tasks.get(0).getTimeLeftInMilli(System.currentTimeMillis());
					}
				}
			}
			// check if need to sleep
			if (timeToWait > 0) {
				try {
					Thread.sleep(timeToWait);
				} catch (InterruptedException e) {
					// if interrupted before the time ended -> new task was added,
					// go back to the start of the loop and check the first task again
					// sort the array
					sortTasks();
				}
			}
		}
	}

	/**
	 * get all the scheduled tasks from the database. get all the active complaints
	 * from the database(need to send reminders). check if we need to create report,
	 * if we need set the report creation task to 2am
	 * 
	 * @return array list with all the new tasks
	 */
	public ArrayList<ScheduledTask> getTasks() {
		long currentTime = System.currentTimeMillis();
		ArrayList<ScheduledTask> newScheduledTask = new ArrayList<ScheduledTask>();
		// get the active complaints from yesterday(wasnt handled yet)
		ArrayList<Complaint> complaints = dbController.getAllComplaints(null);
		if (complaints != null) {
			// for each complaint
			for (Complaint complaint : complaints) {
				// if its from the last 24 hours
				if ((complaint.getCreationTime().getTime() - currentTime) <= DAY_IN_MILLISECONDS) {
					// add new reminder 24 hours after the creation of the complaint
					ScheduledReminder newReminder = new ScheduledReminder(
							new Timestamp(complaint.getCreationTime().getTime() + DAY_IN_MILLISECONDS));
					// get the responsible employee details from the database
					User responsibleEmployee = dbController.getUser(complaint.getResponsibleEmployeeUserName());
					String complaintReminderText = "please handle complaint " + complaint.getComplaintsNumber() + "\n"
							+ "thanks";
					newReminder.setEmail(responsibleEmployee.getEmail(), complaintReminderText);
					newReminder.setSMS(responsibleEmployee.getPhoneNumber(), complaintReminderText);
					newScheduledTask.add(newReminder);
				}
			}
		}
		// get the last month month + year values
		// get the month and year of the last month
		int month = LocalDateTime.now().getMonthValue();
		int year = LocalDateTime.now().getYear();
		// if we stated a new year- get the 12 of last year
		if (month == 1) {
			year = year - 1;
			month = 12;
		}
		// if not - get the last month
		else {
			month = month - 1;
		}
		// check if the last month reports where not created
		if (dbController.getAllQuarterReports(month, month, year).isEmpty()) {
			// add report creation tasks, set to 2am
			ScheduledReportCreationTask newScheduledReportCreationTask = new ScheduledReportCreationTask(
					Timestamp.valueOf(LocalDateTime.of(LocalDate.now(), LocalTime.of(2, 0))));
			newScheduledTask.add(newScheduledReportCreationTask);
		}
		// add end of day task -> check again tomorrow
		ScheduledEndOfDayTask newScheduledEndOfDayTask = new ScheduledEndOfDayTask();
		newScheduledTask.add(newScheduledEndOfDayTask);
		return newScheduledTask;
	}

	/**
	 * add new task to the tasks list
	 * 
	 * @param task
	 */
	public void addTask(ScheduledTask task) {
		synchronized (tasks) {
			tasks.add(task);
			isSorted = false;
		}
	}

	/**
	 * add new tasks to the tasks list
	 * 
	 * @param tasksToAdd
	 */
	public void addTasks(ArrayList<ScheduledTask> tasksToAdd) {
		synchronized (tasks) {
			tasks.addAll(tasksToAdd);
			isSorted = false;
		}
	}

	/**
	 * sort the tasks if needed
	 */
	public void sortTasks() {
		synchronized (tasks) {
			if (isSorted == false) {
				tasks.sort(new TasksComparator());
				isSorted = true;
			}
		}
	}

	/**
	 * end the running of the scheduled task manager(if its currently running)
	 */
	public void endRunning() {
		run = false;

	}

	/**
	 * compare 2 tasks by time, the shortest time(closest time) should be first
	 *
	 */
	private class TasksComparator implements Comparator<ScheduledTask> {
		@Override
		public int compare(ScheduledTask task1, ScheduledTask task2) {
			if (task1 == null || task2 == null)
				throw new NullPointerException();
			return (int) (task2.getTimeInMilli() - task1.getTimeInMilli());
		}

	}

}
