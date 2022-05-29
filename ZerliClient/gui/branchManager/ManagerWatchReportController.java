package branchManager;

import java.util.ArrayList;
import java.util.Collections;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import javafx.collections.ObservableList;
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

	private Integer[] monthsList = { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12 };
	private Integer[] yearsList = {2022, 2021, 2020, 2019, 2018, 2017, 2016, 2015, 2014, 2013, 2012, 2011, 2010};

	private ObservableList<Integer> yearObs;
	private ObservableList<Integer> monthObs;
	private ObservableList<ReportType> reportObs;

	@Override
	public Pane getBasePane() {
		return managerWatchReportPane;
	}

	@Override
	public void resetController() {

		managerOpenReport.setDisable(true);
		managerGetReport.setDisable(false);
		managerReportMonth.getSelectionModel().clearSelection();
		managerReportYear.getSelectionModel().clearSelection();
		managerReportType.getSelectionModel().clearSelection();
	}

	@Override
	public void openWindow() {
		monthObs.setAll(monthsList);
		yearObs.setAll(yearsList);
		reportObs.setAll(ReportType.MONTHLY_ORDERS_REPORT, ReportType.MONTHLY_REVENU_EREPORT);
		managerReportMonth.setItems(monthObs);
		managerReportYear.setItems(yearObs);
		managerReportType.setItems(reportObs);
	}

	@FXML
	void getReport(ActionEvent event) {
		report = managerBoundry.requestViewReport(managerReportType.getSelectionModel().getSelectedItem(),
				managerReportMonth.getSelectionModel().getSelectedItem(),
				managerReportYear.getSelectionModel().getSelectedItem());
		managerOpenReport.setDisable(false);
		managerGetReport.setDisable(true);
	}

	@FXML
	void openReport(ActionEvent event) {
		managerOpenReport.setDisable(true);
		managerGetReport.setDisable(false);
		reportController = getController();
		reportController.setReport(report);
		reportController.openWindow();
		ceoReportScreen.getChildren().setAll(reportController.getBasePane());
	}

	private IReportController getController() {
		switch (report.getType().ordinal()) {
		case 0:
			return new OrderReportController();
		case 1:
			return new RevenueReportController();
		}
		return null;
	}

}
