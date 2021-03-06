package surveyGui;

import java.io.File;
import java.util.ArrayList;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import main.ClientUI;
import main.GuiObjectsFactory;
import main.IGuiController;
import survey.Survey;
import usersManagment.CustomerServiceEmployeeBoundary;

public class ShowChoosenSurvey implements IGuiController {

	private GuiObjectsFactory guiObjectsFactory = GuiObjectsFactory.getInstance();
	private CustomerServiceEmployeeBoundary surveyBoundary = guiObjectsFactory.employeeServiceBoundary;

	@FXML
	private AnchorPane SurveyBasePane;

	@FXML
	private Button saveFileBtn;

	@FXML
	private Button showConclusionsBtn;

	@FXML
	private TextField pathText;

	@FXML
	private Label resultLabel;

	@FXML
	private Label msgLabel;

	private Survey selectedSurvey;

	public void setSelectedSurvey(Survey selectedsurvey) {
		selectedSurvey = selectedsurvey;
	}

	@Override
	public void openWindow() {
		msgLabel.setText("");
		String resultString;
		if (selectedSurvey.getResultFile() == null)
			showConclusionsBtn.setDisable(true);
		else
			showConclusionsBtn.setDisable(false);
		resultString = selectedSurvey.getResult();
		resultLabel.setText(resultString);
		// guiObjectsFactory.mainWindowController.changeWindowName("Survey details");
		guiObjectsFactory.mainWindowController.showNewWindow(SurveyBasePane);
	}

	@FXML
	void saveFile(ActionEvent event) {
		FileChooser fc = new FileChooser();
		fc.setSelectedExtensionFilter(new ExtensionFilter("PDF files", "pdf"));
		File f = fc.showOpenDialog(ClientUI.globalstage);
		if (f == null)
			return;
		String path = f.getAbsolutePath();
		try {
			surveyBoundary.enterSurveyResult(selectedSurvey.getSurveyNumber(), path);
			msgLabel.setText("Result file saved !");
		} catch (Exception e) {
			msgLabel.setText("Failed " + e.getMessage());
		}
	}

	@FXML
	public void conclusions() {
		FileChooser fc = new FileChooser();
		fc.setSelectedExtensionFilter(new ExtensionFilter("PDF files", "pdf"));
		File f = fc.showSaveDialog(ClientUI.globalstage);
		if (f == null)
			return;
		String path = f.getAbsolutePath();
		try {
			surveyBoundary.saveSurveyResultToLocalFile(selectedSurvey.getResultFile(), path);
			msgLabel.setText("Result file saved !");
		} catch (Exception e) {
			msgLabel.setText("Failed to save the file");
		}
	}

	@FXML
	void goBack(ActionEvent event) {
		guiObjectsFactory.surveyResultsController.openWindow();
	}

	@Override
	public Pane getBasePane() {
		return SurveyBasePane;
	}

	@Override
	public void resetController() {
		resultLabel.setText("");
		msgLabel.setText("");

	}
}