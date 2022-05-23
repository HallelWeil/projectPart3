package branchManager;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import main.IGuiController;


public class ManagerWatchReportController implements IGuiController {

    @FXML
    private ScrollPane ceoReportScreen;

    @FXML
    private Button managerGetReport;

    @FXML
    private Button managerOpenReport;

    @FXML
    private Button managerPreviewReport;

    @FXML
    private ComboBox<?> managerReportMonth;

    @FXML
    private ComboBox<?> managerReportType;

    @FXML
    private ComboBox<?> managerReportYear;

    @FXML
    private Button managerSaveReport;

    @FXML
    private AnchorPane managerWatchReportPane;

    @FXML
    private Label reportMessage;

	@Override
	public Pane getBasePane() {
		// TODO Auto-generated method stub
		return managerWatchReportPane;
	}

	@Override
	public void resetController() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void openWindow() {
		// TODO Auto-generated method stub
		
	}

    @FXML
    void GetReport(ActionEvent event) {
    	

    }

    @FXML
    void OpenReport(ActionEvent event) {

    }

    @FXML
    void PreviewReport(ActionEvent event) {

    }

    @FXML
    void saveReport(ActionEvent event) {

    }


}

