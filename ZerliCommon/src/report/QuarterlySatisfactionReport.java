package report;

public class QuarterlySatisfactionReport extends Report {

	private int[] complaintsPerMonth;

	public QuarterlySatisfactionReport(int month, int year) {
		super(month, year, ReportType.QUARTERLY_SATISFACTION_REPORT, "ALL");
	}

	public void setComplaintsPerMonth(int[] complaintsPerMonth) {
		this.complaintsPerMonth = complaintsPerMonth;
	}

	public String getStartMonth() {
		return (getMonth() * 3 - 2) + "";
	}

	public String getStartYear() {
		return getYear() + "";
	}

	public String getEndMonth() {
		return (getMonth() * 3) + "";
	}

	public String getEndYear() {
		return getYear() + "";
	}

	public int[] getComplaintsPerMonth() {
		return complaintsPerMonth;
	}

	public int getNumberOfComplaints() {
		return complaintsPerMonth[0] + complaintsPerMonth[1] + complaintsPerMonth[2];
	}

}