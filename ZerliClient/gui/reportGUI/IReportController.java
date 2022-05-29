package reportGUI;

import javafx.scene.layout.Pane;
import report.Report;

public interface IReportController {
	
	void setReport(Report report);

	void openWindow();

	Pane getBasePane();
}
