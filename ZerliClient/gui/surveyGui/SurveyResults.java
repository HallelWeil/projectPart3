package surveyGui;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import main.GuiObjectsFactory;
import main.IGuiController;
import survey.Survey;
import usersManagment.CustomerServiceEmployeeBoundary;

public class SurveyResults implements IGuiController {

	private GuiObjectsFactory guiObjectsFactory = GuiObjectsFactory.getInstance();
	private CustomerServiceEmployeeBoundary surveyBoundary = guiObjectsFactory.employeeServiceBoundary;

	@FXML
	private AnchorPane showSurveyBasePane;

	@FXML
	private TableView<Survey> surveysTable;

	@FXML
	private Button selectsurveyBtn;

	@FXML
	private TableColumn<Survey, Integer> surveyNumberlCol;

	@FXML
	private TableColumn<Survey, Integer> numberOfParticipantsCol;

	ObservableList<Survey> surveysObs = FXCollections.observableArrayList();
	Survey selectedsurvey;

	public void initializeSurveysTable() {
		surveysObs.clear();
		surveysTable.getItems().clear();
		surveyNumberlCol.setCellValueFactory(new PropertyValueFactory<>("surveyNumber"));
		numberOfParticipantsCol.setCellValueFactory(new PropertyValueFactory<>("numberOfParticipants"));
	}

	@Override
	public void openWindow() {
		initializeSurveysTable();
		try {
			surveysObs.setAll(surveyBoundary.getAllSurvey());
		} catch (Exception e) {
			//
		}
		surveysTable.setItems(surveysObs);
		guiObjectsFactory.mainWindowController.changeWindowName("Surveys");
		guiObjectsFactory.mainWindowController.showNewWindow(showSurveyBasePane);
	}

	@Override
	public Pane getBasePane() {
		return showSurveyBasePane;
	}

	@Override
	public void resetController() {
		surveysObs.clear();
		surveysTable.getItems().clear();
		selectedsurvey = null;
	}

	@FXML
	void selectSurvey(ActionEvent event) {
		selectedsurvey = surveysTable.getSelectionModel().getSelectedItem();
		if (selectedsurvey != null) {
			guiObjectsFactory.mainWindowController.changeWindowName("Survey details - survey #" + selectedsurvey.getSurveyNumber());
			guiObjectsFactory.showChoosenSurvey.setSelectedSurvey(selectedsurvey);
			guiObjectsFactory.showChoosenSurvey.openWindow();
		}
	}
}