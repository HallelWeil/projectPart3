package branchEmployee;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import main.GuiObjectsFactory;
import main.IGuiController;
import survey.Survey;

public class SearchSurverControllerGUI implements IGuiController {
	private GuiObjectsFactory guiObjectsFactory = GuiObjectsFactory.getInstance();
	private int surveyNumber;
	private Survey selectedSurvey;

	@FXML
	private Button PrevPageButton;

	@FXML
	private Button SearchButton;

	@FXML
	private Label Error_label;

	@FXML
	private TextField searchBox;

	@FXML
	private Pane searchSurveyPane;

	@FXML
	void enterSurveyNumber(ActionEvent event) {
		String searchSurveyNumber = searchBox.getText();
		if (!searchSurveyNumber.matches("[0-9]+")) {
			setError("Survey number must contain numbers only!");
		} else {
			setSurveyNumber(Integer.valueOf(searchSurveyNumber));

		}
	}

	private void setError(String errorString) {
		Error_label.setText(errorString);

	}

	@FXML
	void goBack(ActionEvent event) {
		guiObjectsFactory.userHomeWindowController.openWindow();
	}

	@FXML
	void searchSurvey(ActionEvent event) {
		try {
			surveyNumber = Integer.valueOf(searchBox.getText());
		} catch (NumberFormatException e) {
			setError("Survey number must be a number!");
			return;
		}
		try {
			selectedSurvey = guiObjectsFactory.branchEmployeeBoundary.getSurvey(surveyNumber);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			setError(e.getMessage());
			return;
		}
		if (selectedSurvey == null) {
			setError("Survey does not exist! please try again");
		} else {
			guiObjectsFactory.showSurvey.setSelectedSurvey(selectedSurvey);
			guiObjectsFactory.showSurvey.openWindow();
		}
	}

	@Override
	public Pane getBasePane() {
		return searchSurveyPane;
	}

	@Override
	public void resetController() {
		searchBox.setText("");

	}

	@Override
	public void openWindow() {
		guiObjectsFactory.mainWindowController.showNewWindow(searchSurveyPane);
		guiObjectsFactory.mainWindowController.changeWindowName("Choose a Survey");

	}

	public int getSurveyNumber() {
		return surveyNumber;
	}

	public void setSurveyNumber(int surveyNumber) {
		this.surveyNumber = surveyNumber;
	}

}
