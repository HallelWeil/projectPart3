package accessibility;

import javafx.fxml.FXML;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import main.GuiObjectsFactory;
import main.IGuiController;
import userGuiManagment.MainWindowGuiManager;

public class AccessibilityPageController implements IGuiController {

	@FXML
	private AnchorPane basePane;

	@Override
	public Pane getBasePane() {
		return basePane;
	}

	@Override
	public void resetController() {
	}

	@Override
	public void openWindow() {
		MainWindowGuiManager mainWindowManager = MainWindowGuiManager.getInstance();
		mainWindowManager.mainWindowController.changeWindowName("Accessibility");
		mainWindowManager.mainWindowController.showNewWindow(basePane);
	}

}
