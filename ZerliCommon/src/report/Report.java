package report;

import java.io.Serializable;
import java.sql.Date;

public class Report implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int month;
	private int year;
	private ReportType type;
	private String BranchName;

	public Report(int month,int year, ReportType type, String branchName) {
		super();
		this.month = month;
		this.year=year;
		this.type = type;
		BranchName = branchName;
	}


	public int getMonth() {
		return month;
	}


	public int getYear() {
		return year;
	}


	public ReportType getType() {
		return type;
	}

	public String getBranchName() {
		return BranchName;
	}

}
