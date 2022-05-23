package ceo;


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
import usersManagment.CEOBoundary;

public class CEOcontroller implements IGuiController {
	private GuiObjectsFactory guiObjectsFactory = GuiObjectsFactory.getInstance();
	private CEOBoundary ceoBoundry = guiObjectsFactory.ceoBoundry;
	private Report report;

    @FXML
    private Button ceoGetReport;

    @FXML
    private Button ceoOpenReport;

    @FXML
    private Button ceoPreviewReportLeft;

    @FXML
    private Button ceoPreviewReportRight;

    @FXML
    private ComboBox<?> ceoReportMonth;

    @FXML
    private ComboBox<?> ceoReportPeriod;

    @FXML
    private AnchorPane ceoReportScreen;

    @FXML
    private ComboBox<?> ceoReportType;

    @FXML
    private ComboBox<?> ceoReportYear;

    @FXML
    private Button ceoSaveReport;

    @FXML
    private AnchorPane ceoWatchReportPane;

    @FXML
    private AnchorPane leftReport;

    @FXML
    private Label reportMessage;

    @FXML
    private AnchorPane rightReport;
    
    @FXML
    private ComboBox<?> ceoReportBranch;

    @FXML
    void OpenReport(ActionEvent event) {

    }

    @FXML
    void PreviewReportLeft(ActionEvent event) {

    }

    @FXML
    void PreviewReportRight(ActionEvent event) {

    }

    @FXML
    void SaveReport(ActionEvent event) {

    }

    @FXML
    void getReport(ActionEvent event) {
    	ceoBoundry.requestViewReport(ceoReportType, ceoReportMonth, ceoReportType, ceoReportBranch.getSelectionModel().getSelectedItem());
    	

    }

	@Override
	public Pane getBasePane() {
		return ceoWatchReportPane;
	}

	@Override
	public void resetController() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void openWindow() {
		// TODO Auto-generated method stub
		
	}

}
