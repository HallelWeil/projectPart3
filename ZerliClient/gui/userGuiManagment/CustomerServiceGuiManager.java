package userGuiManagment;

import customerService.NewComplaint;
import customerService.UpdateComplaint;
import main.GuiObjectsFactory;
import surveyGui.ShowChoosenSurvey;
import surveyGui.SurveyResults;
import usersManagment.CustomerServiceEmployeeBoundary;

public class CustomerServiceGuiManager implements IUserGuiManager {

	private static CustomerServiceGuiManager customerServiceGuiManager;

	private GuiObjectsFactory guiObjectsFactory = GuiObjectsFactory.getInstance();
	// the controllers
	private NewComplaint newComplaintController;
	private UpdateComplaint updateController;
	private SurveyResults surveyResultsController;
	private ShowChoosenSurvey showChoosenSurvey;
	// the boundaries
	private CustomerServiceEmployeeBoundary employeeServiceBoundary;

	private CustomerServiceGuiManager() {

	}

	public static CustomerServiceGuiManager getInstance() {
		if (customerServiceGuiManager == null)
			customerServiceGuiManager = new CustomerServiceGuiManager();
		return customerServiceGuiManager;
	}

	public NewComplaint getNewComplaintController() {
		if (newComplaintController == null) {
			newComplaintController = (NewComplaint) guiObjectsFactory
					.loadFxmlFile("/customerService/NewComplaint.fxml");
		}
		return newComplaintController;
	}

	public UpdateComplaint getUpdateController() {
		if (updateController == null) {
			updateController = (UpdateComplaint) guiObjectsFactory
					.loadFxmlFile("/customerService/UpdateComplaint.fxml");
		}
		return updateController;
	}

	public SurveyResults getSurveyResultsController() {
		if (surveyResultsController == null) {
			surveyResultsController = (SurveyResults) guiObjectsFactory.loadFxmlFile("/surveyGui/surveyResults.fxml");
		}
		return surveyResultsController;
	}

	public ShowChoosenSurvey getShowChoosenSurvey() {
		if (showChoosenSurvey == null) {
			showChoosenSurvey = (ShowChoosenSurvey) guiObjectsFactory.loadFxmlFile("/surveyGui/ChoosenSurvey.fxml");
		}
		return showChoosenSurvey;
	}

	public CustomerServiceEmployeeBoundary getEmployeeServiceBoundary() {
		if (employeeServiceBoundary == null) {
			employeeServiceBoundary = new CustomerServiceEmployeeBoundary();
		}
		return employeeServiceBoundary;
	}

	@Override
	public void logout() {
		newComplaintController = null;
		updateController = null;
		surveyResultsController = null;
		showChoosenSurvey = null;
		employeeServiceBoundary = null;
	}

}
