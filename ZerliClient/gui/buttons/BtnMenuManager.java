package buttons;

import main.GuiObjectsFactory;
import mainWindow.MainWindowController;

public class BtnMenuManager {

	private GuiObjectsFactory guiObjectsFactory = GuiObjectsFactory.getInstance();
	private BtnController btns = guiObjectsFactory.btnController;
	private MainWindowController mainWindowController = guiObjectsFactory.mainWindowController;

	public void setUserBtns() {
		mainWindowController.setBtn(btns.getAccessibilityBtn(), 6);
		mainWindowController.setBtn(btns.getLogoutBtn(), 7);
		mainWindowController.setBtn(btns.getHomeBtn(), 1);
	}

	public void setCEObtns() {
		/////
	}

}
