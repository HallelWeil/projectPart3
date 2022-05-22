package main;

import java.io.IOException;
import java.net.URL;

import buttons.BtnController;
import buttons.BtnMenuManager;
import client.ClientBoundary;
import customer.ProductsManager;
import customer.ShopWindowController;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.Pane;
import mainWindow.*;
import shop.ShopBoundary;
import usersHomeWindows.UserHomeWindowGuiController;
import usersManagment.UserBoundary;

public class GuiObjectsFactory {

	// gui controllers
	private static GuiObjectsFactory guiObjectsFactoryInstance;
	public MainWindowController mainWindowController;
	public LoginGuiController loginGuiController;
	public BtnController btnController;
	public UserHomeWindowGuiController userHomeWindowController;
	public ShopWindowController shopWindowController;

	// gui manager
	public BtnMenuManager btnMenuManager;
	public ProductsManager productManager;

	// Boundaries
	public UserBoundary userBaundary;
	public ShopBoundary shopBoundary;
	public ClientBoundary clientBoundary = new ClientBoundary();

	private GuiObjectsFactory() {
		//
	}

	public static GuiObjectsFactory getInstance() {
		if (guiObjectsFactoryInstance == null) {
			guiObjectsFactoryInstance = new GuiObjectsFactory();
		}
		return guiObjectsFactoryInstance;
	}

	/**
	 * Init the different boundaries, should be called after connected to the server
	 */
	public void initBoundaries() {

		userBaundary = new UserBoundary();
		shopBoundary = new ShopBoundary();
	}

	public void loadAllFxmlFiles() throws IOException {
		btnController = (BtnController) loadFxmlFile("/buttons/Buttons.fxml");
		mainWindowController = (MainWindowController) loadFxmlFile("/mainWindow/MainWindow.fxml");
		loginGuiController = (LoginGuiController) loadFxmlFile("/mainWindow/LoginWindow.fxml");
		userHomeWindowController = (UserHomeWindowGuiController) loadFxmlFile("/usersHomeWindows/UserHomeWindow.fxml");
		shopWindowController = (ShopWindowController) loadFxmlFile("/customer/ShopWindow.fxml");
		// init members
		btnMenuManager = new BtnMenuManager();
		productManager = new ProductsManager();
	}

	/**
	 * Load fxml file, return its controller
	 * 
	 * @param filePath should be "/package/filename.fxml"
	 * @return the controller
	 * @throws IOException if failed to load the fxml file
	 */
	public IGuiController loadFxmlFile(String filePath) throws IOException {
		FXMLLoader loader = new FXMLLoader();
		final URL resource = getClass().getResource(filePath);
		loader.setLocation(resource);
		System.out.println(resource);
		loader.load();
		return (IGuiController) loader.getController();
	}

	public void resetAll() {
		mainWindowController.resetController();
		loginGuiController.resetController();
		userHomeWindowController.resetController();

	}
}
