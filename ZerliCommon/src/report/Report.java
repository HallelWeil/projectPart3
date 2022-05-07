package report;

import java.io.Serializable;
import java.sql.Date;

public abstract class Report implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Date startDate;
	private ReportType type;
	private String BranchName;
	public Report(Date startDate, ReportType type, String branchName) {
		super();
		this.startDate = startDate;
		this.type = type;
		BranchName = branchName;
	}
	public Date getStartDate() {
		return startDate;
	}
	public ReportType getType() {
		return type;
	}
	public String getBranchName() {
		return BranchName;
	}
	
}
