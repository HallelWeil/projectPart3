package main;

import java.io.IOException;
import java.net.URL;

import buttons.BtnController;
import buttons.BtnMenuManager;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.Pane;
import mainWindow.*;
import usersHomeWindows.UserHomeWindowGuiController;
import usersManagment.UserBoundary;

public class GuiObjectsFactory {

	// gui controllers
	private static GuiObjectsFactory guiObjectsFactoryInstance;
	public MainWindowController mainWindowController;
	public LoginGuiController loginGuiController;
	public BtnController btnController;
	public UserHomeWindowGuiController userHomeWindowController;

	public BtnMenuManager btnMenuManager;

	// Boundaries
	public UserBoundary userBaundary = new UserBoundary();

	private GuiObjectsFactory() {
		//
	}

	public static GuiObjectsFactory getInstance() {
		if (guiObjectsFactoryInstance == null) {
			guiObjectsFactoryInstance = new GuiObjectsFactory();
		}
		return guiObjectsFactoryInstance;
	}

	public void loadAllFxmlFiles() throws IOException {
		btnController = (BtnController) loadFxmlFile("/buttons/Buttons.fxml");
		mainWindowController = (MainWindowController) loadFxmlFile("/mainWindow/MainWindow.fxml");
		loginGuiController = (LoginGuiController) loadFxmlFile("/mainWindow/LoginWindow.fxml");
		userHomeWindowController = (UserHomeWindowGuiController) loadFxmlFile("/usersHomeWindows/UserHomeWindow.fxml");
		
		
		//init members
		btnMenuManager = new BtnMenuManager();
	}

	/**
	 * Load fxml file, return its controller
	 * 
	 * @param filePath should be "/package/filename.fxml"
	 * @return the controller
	 * @throws IOException if failed to load the fxml file
	 */
	public IGuiController loadFxmlFile(String filePath) throws IOException {
		Pane rootPane;
		FXMLLoader loader = new FXMLLoader();
		final URL resource = getClass().getResource(filePath);
		loader.setLocation(resource);
		rootPane = loader.load();
		return (IGuiController) loader.getController();
	}
}
