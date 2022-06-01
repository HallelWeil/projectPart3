package userGuiManagment;

import accessibility.AccessibilityPageController;
import buttons.BtnController;
import buttons.BtnMenuManager;
import customer.ProductsManager;
import main.GuiObjectsFactory;
import mainWindow.LoginGuiController;
import mainWindow.MainWindowController;
import usersHomeWindows.UserHomeWindowGuiController;
import usersManagment.UserBoundary;

public class MainWindowGuiManager implements IUserGuiManager {

	private static MainWindowGuiManager mainWindowGuiManager;
	private GuiObjectsFactory guiObjectsFactory = GuiObjectsFactory.getInstance();

	// the gui controllers
	private MainWindowController mainWindowController;
	private LoginGuiController loginGuiController;
	private BtnController btnController;
	private UserHomeWindowGuiController userHomeWindowController;
	private AccessibilityPageController accessibilityPageController;

	// gui managers
	private BtnMenuManager btnMenuManager;

	// Boundaries
	private UserBoundary userBaundary;

	public static MainWindowGuiManager getInstance() {
		if (mainWindowGuiManager == null) {
			mainWindowGuiManager = new MainWindowGuiManager();
			mainWindowGuiManager.initMainWindow();
		}
		return mainWindowGuiManager;
	}

	private void initMainWindow() {
		btnController = (BtnController) guiObjectsFactory.loadFxmlFile("/buttons/Buttons.fxml");
		mainWindowController = (MainWindowController) guiObjectsFactory.loadFxmlFile("/mainWindow/MainWindow.fxml");
		loginGuiController = (LoginGuiController) guiObjectsFactory.loadFxmlFile("/mainWindow/LoginWindow.fxml");
		userHomeWindowController = (UserHomeWindowGuiController) guiObjectsFactory
				.loadFxmlFile("/usersHomeWindows/UserHomeWindow.fxml");
		accessibilityPageController = (AccessibilityPageController) guiObjectsFactory
				.loadFxmlFile("/accessibility/AccessibilityWindow.fxml");
	}

	@Override
	public void logout() {
		// TODO Auto-generated method stub

	}

}
