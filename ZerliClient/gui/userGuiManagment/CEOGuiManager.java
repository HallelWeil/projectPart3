package userGuiManagment;

import ceo.CEOcontroller;
import main.GuiObjectsFactory;
import usersManagment.CEOBoundary;

public class CEOGuiManager implements IUserGuiManager {

	private static CEOGuiManager ceoGuiManager;

	private GuiObjectsFactory guiObjectsFactory = GuiObjectsFactory.getInstance();
	// the controllers
	private CEOcontroller ceoController;
	// Boundaries
	private CEOBoundary ceoBoundry;

	private CEOGuiManager() {

	}

	public static CEOGuiManager getInstance() {
		if (ceoGuiManager == null)
			ceoGuiManager = new CEOGuiManager();
		return ceoGuiManager;
	}

	
	
	public CEOcontroller getCeoController() {
		if(ceoController==null) {
			ceoController = (CEOcontroller) guiObjectsFactory.loadFxmlFile("/ceo/ceoWatchReport.fxml");
		}
		return ceoController;
	}

	public CEOBoundary getCeoBoundry() {
		if(ceoBoundry==null) {
			ceoBoundry = new CEOBoundary();

		}
		return ceoBoundry;
	}

	@Override
	public void logout() {
		ceoController = null;
		ceoBoundry = null;
	}

}
