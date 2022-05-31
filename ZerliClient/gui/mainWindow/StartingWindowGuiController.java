package mainWindow;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import main.GuiObjectsFactory;
import main.IGuiController;

public class StartingWindowGuiController implements IGuiController {

	GuiObjectsFactory guiObjectsFactory = GuiObjectsFactory.getInstance();

	@FXML
	private AnchorPane basePane;

	@FXML
	private Button loginButton;

	@FXML
	void login(ActionEvent event) {
		guiObjectsFactory.loginGuiController.openWindow();
	}

	@Override
	public Pane getBasePane() {
		return basePane;
	}

	@Override
	public void resetController() {
		// TODO Auto-generated method stub
	}

	@Override
	public void openWindow() {
		guiObjectsFactory.mainWindowController.showNewWindow(basePane);
	}

}
