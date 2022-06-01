package userGuiManagment;

import branchEmployee.SearchSurverControllerGUI;
import branchEmployee.SurveyControllerGUI;
import courier.CourierControllerGUI;
import main.GuiObjectsFactory;
import usersManagment.BranchEmployeeBoundary;

public class BranchEmployeeGuiManager implements IUserGuiManager {

	private static BranchEmployeeGuiManager branchEmployeeGuiManager;

	private GuiObjectsFactory guiObjectsFactory = GuiObjectsFactory.getInstance();
	// the gui controllers
	private SearchSurverControllerGUI searchSurvey;
	private SurveyControllerGUI showSurvey;
	// boundaries
	private BranchEmployeeBoundary branchEmployeeBoundary;

	private BranchEmployeeGuiManager() {

	}

	public static BranchEmployeeGuiManager getInstance() {
		if (branchEmployeeGuiManager == null)
			branchEmployeeGuiManager = new BranchEmployeeGuiManager();
		return branchEmployeeGuiManager;
	}

	public SearchSurverControllerGUI getSearchSurvey() {
		if (searchSurvey == null) {
			searchSurvey = (SearchSurverControllerGUI) guiObjectsFactory
					.loadFxmlFile("/branchEmployee/BranchEmpolyeeSearchSurvey.fxml");
		}
		return searchSurvey;
	}

	public SurveyControllerGUI getShowSurvey() {
		if (showSurvey == null) {
			showSurvey = (SurveyControllerGUI) guiObjectsFactory
					.loadFxmlFile("/branchEmployee/BranchEmployeeSurveyWindow.fxml");
		}
		return showSurvey;
	}

	public BranchEmployeeBoundary getBranchEmployeeBoundary() {
		if (branchEmployeeBoundary == null) {
			branchEmployeeBoundary = new BranchEmployeeBoundary();
		}
		return branchEmployeeBoundary;
	}

	@Override
	public void logout() {
		searchSurvey = null;
		showSurvey = null;
		branchEmployeeBoundary = null;

	}

}
