package ceo;

import java.util.stream.IntStream;

import branch.Branch;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.SplitPane;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import main.GuiObjectsFactory;
import main.IGuiController;
import report.Report;
import report.ReportType;
import reportGUI.IReportController;
import reportGUI.OrderReportController;
import reportGUI.QuarterlyOrdersReportController;
import reportGUI.QuarterlyRevenueReportController;
import reportGUI.RevenueReportController;
import reportGUI.SatisfactionReportController;
import usersManagment.CEOBoundary;

public class CEOcontroller implements IGuiController {
	private GuiObjectsFactory guiObjectsFactory = GuiObjectsFactory.getInstance();
	private CEOBoundary ceoBoundry = guiObjectsFactory.ceoBoundry;
	private IReportController leftReportController;
	private IReportController rightReportController;
	private IReportController middleReportController;
	private Report report = null;

	@FXML
	private Button getReportBot;

	@FXML
	private Button openReportBot;

	@FXML
	private Button previewReportLeftBot;

	@FXML
	private Button previewReportRightBot;

	@FXML
	private ComboBox<Integer> ceoReportMonth;

	@FXML
	private AnchorPane ceoReportScreen;

	@FXML
	private ComboBox<ReportType> ceoReportType;

	@FXML
	private ComboBox<Integer> ceoReportYear;

	@FXML
	private Button ceoSaveReport;

	@FXML
	private AnchorPane ceoWatchReportPane;
	
    @FXML
    private SplitPane ceoSplitScreen;

	@FXML
	private AnchorPane leftReport;

	@FXML
	private Label reportMessage;

	@FXML
	private AnchorPane rightReport;

	@FXML
	private ComboBox<String> ceoReportBranch;
	
	private Integer[] monthsList = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12};
	private Integer[] periodsList = {1, 2, 3, 4};
	private Integer[] yearsList = {2022, 2021, 2020, 2019, 2018, 2017, 2016, 2015, 2014, 2013, 2012, 2011, 2010};
	private String[] branches = {"Main Branch", "Second Breanch", "Third Branch"};
	private ObservableList<Integer> monthObs;
	private ObservableList<String> branchObs;
	private ObservableList<Integer> yearObs;

	@FXML
	void OpenReport(ActionEvent event) {
		previewReport(ceoReportScreen, middleReportController);
	}

	@FXML
	void PreviewReportLeft(ActionEvent event) {
		previewReport(leftReport, leftReportController);
		ceoReportScreen.getChildren().setAll(ceoSplitScreen);

	}

	@FXML
	void PreviewReportRight(ActionEvent event) {
		previewReport(rightReport, rightReportController);
		ceoReportScreen.getChildren().setAll(ceoSplitScreen);
	}
	
	private void previewReport(AnchorPane anchor, IReportController reportController ) {
		openReportBot.setDisable(true);
		previewReportLeftBot.setDisable(true);
		previewReportRightBot.setDisable(true);
		getReportBot.setDisable(false);
		reportController = getController();
		reportController.setReport(report);
		reportController.openWindow();
		anchor.getChildren().setAll(reportController.getBasePane());	
	}
	
	

	@FXML
	void getReport(ActionEvent event) {
		report = ceoBoundry.requestViewReport(ceoReportType.getSelectionModel().getSelectedItem(),
				ceoReportMonth.getSelectionModel().getSelectedItem(),
				ceoReportYear.getSelectionModel().getSelectedItem(),
				ceoReportBranch.getSelectionModel().getSelectedItem());
		if(report != null) {
			openReportBot.setDisable(false);
			previewReportLeftBot.setDisable(false);
			previewReportRightBot.setDisable(false);
			getReportBot.setDisable(true);
		}
	}

	@Override
	public Pane getBasePane() {
		return ceoWatchReportPane;
	}

	@Override
	public void resetController() {
		report = null;
		ceoReportMonth.setDisable(true);
		ceoReportMonth.setPromptText("month");
		ceoReportType.getSelectionModel().clearSelection();
		ceoReportMonth.getSelectionModel().clearSelection();
		ceoReportYear.getSelectionModel().clearSelection();
		ceoReportBranch.getSelectionModel().clearSelection();
		branchObs.clear();
		

	}

	@Override
	public void openWindow() {
		guiObjectsFactory.mainWindowController.changeWindowName("CEO - view report");
		branchObs.setAll(branches);
		monthObs.setAll(monthsList);
		yearObs.setAll(yearsList);
		ceoReportBranch.setItems(branchObs);
		ceoReportMonth.setItems(monthObs);
		ceoReportYear.setItems(yearObs);
		ceoReportType.getItems().setAll(ReportType.values());
	}
	
    @FXML
    void changeMonthChoice(ActionEvent event) {
    	ceoReportMonth.setDisable(false);
    	switch(ceoReportType.getSelectionModel().getSelectedItem().ordinal()) {
    	case 0:
    	case 1:
    		if(monthObs.contains(5))
    			break;
    		monthObs.setAll(monthsList);
    		ceoReportMonth.setPromptText("month");
    		break;
    	case 2:
    	case 3:
    	case 4:
    		if(monthObs.contains(12))
        		monthObs.setAll(periodsList);
        		ceoReportMonth.setPromptText("period");
    			break;
    	}
    }
    
    private IReportController getController() {
    	switch(report.getType().ordinal()) {
    	case 0:
    		return new OrderReportController();
    	case 1:
    		return new RevenueReportController();
    	case 2:
    		return new QuarterlyOrdersReportController();
    	case 3:
    		return new QuarterlyRevenueReportController();
    	case 4:
    		return new SatisfactionReportController();
    	}
		return null;
	}

}
