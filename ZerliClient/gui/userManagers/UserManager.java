package userManagers;

import buttons.BtnMenuManager;
import client.ClientBoundary;
import customer.ProductsManager;
import mainWindow.LoginGuiController;
import mainWindow.MainWindowController;
import usersManagment.UserBoundary;

/**
 * for each user type, manage the user's windows and save instances of the
 * different windows. create the needed boundaries and windows and manage them
 *
 */
public abstract class UserManager {

	private MainWindowController mainWindowController;
	private LoginGuiController loginGuiController;

	// gui manager
	private BtnMenuManager btnMenuManager;
	private ProductsManager productManager;

	//
	private UserBoundary userBaundary;

	//
	private ClientBoundary clientBoundary = new ClientBoundary();
}
