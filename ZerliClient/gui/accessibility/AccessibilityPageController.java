package accessibility;

import javafx.fxml.FXML;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import main.GuiObjectsFactory;
import main.IGuiController;

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
		GuiObjectsFactory guiObjectsFactory = GuiObjectsFactory.getInstance();
		guiObjectsFactory.mainWindowController.changeWindowName("Accessibility");
		guiObjectsFactory.mainWindowController.showNewWindow(basePane);
	}

}
