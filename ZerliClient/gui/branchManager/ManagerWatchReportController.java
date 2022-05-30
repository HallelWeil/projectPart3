package branchManager;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import main.GuiObjectsFactory;
import main.IGuiController;
import report.Report;
import report.ReportType;
import reportGUI.IReportController;
import reportGUI.OrderReportController;
import reportGUI.RevenueReportController;
import usersManagment.BranchManagerBoundary;

public class ManagerWatchReportController implements IGuiController {
	IReportController reportController = null;
	BranchManagerBoundary managerBoundry = new BranchManagerBoundary();
	private Report report = null;
	private GuiObjectsFactory guiObjectsFactory = GuiObjectsFactory.getInstance();

	@FXML
	private AnchorPane ceoReportScreen;

	@FXML
	private Button managerGetReport;

	@FXML
	private Button managerOpenReport;

	@FXML
	private ComboBox<Integer> managerReportMonth;

	@FXML
	private ComboBox<ReportType> managerReportType;

	@FXML
	private ComboBox<Integer> managerReportYear;

	@FXML
	private AnchorPane managerWatchReportPane;

	@FXML
	private Label reportMessage;

	private ArrayList<Integer> monthsList;
	private ArrayList<Integer> yearsList;

	// private ObservableList<Integer> yearObs;
	// private ObservableList<Integer> monthObs;
	// private ObservableList<ReportType> reportObs;

	@Override
	public Pane getBasePane() {
		return managerWatchReportPane;
	}

	@Override
	public void resetController() {

		managerOpenReport.setDisable(true);
		managerGetReport.setDisable(false);
		managerReportMonth.getSelectionModel().clearAndSelect(0);
		managerReportYear.getSelectionModel().clearSelection();
		managerReportType.getSelectionModel().clearSelection();
	}

	@Override
	public void openWindow() {
		guiObjectsFactory.mainWindowController.showNewWindow(managerWatchReportPane);
		guiObjectsFactory.mainWindowController.changeWindowName("Manager - watch report");
		ArrayList<Integer> yearsList = (ArrayList<Integer>) IntStream.range(2000, LocalDate.now().getYear() + 1).boxed()
				.collect(Collectors.toList());
		Collections.reverse(yearsList);
		monthsList = (ArrayList<Integer>) IntStream.range(1, 13).boxed().collect(Collectors.toList());
		managerReportMonth.getItems().setAll(monthsList);
		managerReportYear.getItems().setAll(yearsList);
		managerReportType.getItems().setAll(ReportType.MONTHLY_ORDERS_REPORT, ReportType.MONTHLY_REVENU_EREPORT);
	}

	@FXML
	void getReport(ActionEvent event) {
		report = managerBoundry.requestViewReport(managerReportType.getSelectionModel().getSelectedItem(),
				managerReportMonth.getSelectionModel().getSelectedItem(),
				managerReportYear.getSelectionModel().getSelectedItem());
		System.out.println(report.getBranchName());
		managerOpenReport.setDisable(false);
		managerGetReport.setDisable(true);
	}

	@FXML
	void openReport(ActionEvent event) throws IOException {
		managerOpenReport.setDisable(true);
		managerGetReport.setDisable(false);
		reportController = getController();
		reportController.setReport(report);
		reportController.openWindow();
		ceoReportScreen.getChildren().setAll(reportController.getBasePane());
	}

	private IReportController getController() throws IOException {
		System.out.println(report.getType().ordinal());
		switch (report.getType()) {
		case MONTHLY_ORDERS_REPORT:
			return (OrderReportController) guiObjectsFactory.loadFxmlFile("/reportGUI/ordersReport.fxml");
		case MONTHLY_REVENU_EREPORT:
			return (RevenueReportController) guiObjectsFactory.loadFxmlFile("/reportGUI/revenueReport.fxml");
		default:
			return null;
		}

	}

}
