package usersHomeWindows;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import main.*;
import userGuiManagment.MainWindowGuiManager;

public class UserHomeWindowGuiController implements IGuiController {

	private static final String userWelcomeString = "Welcome ";

	private MainWindowGuiManager mainWindowManager = MainWindowGuiManager.getInstance();

	@FXML
	private AnchorPane basePane;

	@FXML
	private AnchorPane innerPane;

	@FXML
	private Label welcomeLabel;

	/**
	 * Go to the user home window
	 */
	public void openWindow() {
		onEntering();
		// move to the next window
		mainWindowManager.mainWindowController.showNewWindow(basePane);
		// change to the new menu name
		mainWindowManager.mainWindowController.changeWindowName("Home");
	}

	/**
	 * do something while entering the new window
	 */
	public void onEntering() {
		// get the users full name
		String fullName = mainWindowManager.userBaundary.CurrentUser.getFirstName() + " "
				+ mainWindowManager.userBaundary.CurrentUser.getLastName();
		welcomeLabel.setText(userWelcomeString + fullName);
	}

	@Override
	public Pane getBasePane() {
		return basePane;
	}

	@Override
	public void resetController() {
		welcomeLabel.setText("");
	}

}
