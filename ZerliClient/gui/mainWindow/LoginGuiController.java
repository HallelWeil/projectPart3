package mainWindow;

import java.io.File;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.FileChooser;
import main.ClientUI;
import main.GuiObjectsFactory;
import main.IGuiController;

public class LoginGuiController implements IGuiController {

	private GuiObjectsFactory guiObjectsFactory = GuiObjectsFactory.getInstance();

	@FXML
	private AnchorPane basePane;

	@FXML
	private Label errorLabel;

	@FXML
	private Button login;

	@FXML
	private PasswordField passwordField;

	@FXML
	private TextField userNameField;

	@FXML
	void tryLogin(ActionEvent event) {
		// get the username and password
		String username = userNameField.getText();
		String password = passwordField.getText();
		// check the legal input values
		if (username.length() < 4) {
			setError("Username must be at least 4 characters");
			return;
		}
		if (password.length() < 6) {
			setError("Password must be at least 6 characters");
			return;
		}
		if (username.contains("\\") || username.contains("/") || password.contains("\\") || password.contains("\\")) {
			setError("Illigal characters!");
			return;
		}
		// try to log in!
		if (guiObjectsFactory.userBaundary.requestLogin(username, password)) {
			goToStartWindow();
		} else {
			setError(guiObjectsFactory.userBaundary.errorMsg);
		}
	}

	/**
	 * set the error label to the given string
	 * 
	 * @param errorMsg
	 */
	private void setError(String errorMsg) {
		errorLabel.setText(errorMsg);
	}

	public void openWindow() {
		// move to the next window
		guiObjectsFactory.mainWindowController.showNewWindow(basePane);
		// change the window name
		guiObjectsFactory.mainWindowController.changeWindowName("Login");
		//empty the error string
		setError("");
	}

	@Override
	public Pane getBasePane() {
		return basePane;
	}

	@Override
	public void resetController() {
		setError("");
		passwordField.setText("");
		userNameField.setText("");
	}

	/**
	 * go to the user's start window
	 */
	private void goToStartWindow() {
		// add the global buttons
		guiObjectsFactory.btnMenuManager.setUserBtns();
		guiObjectsFactory.userHomeWindowController.openWindow();
	}
}
