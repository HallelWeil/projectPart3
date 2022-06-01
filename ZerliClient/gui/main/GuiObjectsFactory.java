package main;

import java.io.IOException;
import java.net.URL;

import PromotionWindow.*;
import accessibility.AccessibilityPageController;
import branchEmployee.SearchSurverControllerGUI;
import branchEmployee.SurveyControllerGUI;
import branchManager.ManagerApproveController;
import branchManager.ManagerUpdateUser;
import branchManager.ManagerWatchReportController;
import buttons.*;
import ceo.CEOcontroller;
import client.ClientBoundary;
import courier.CourierControllerGUI;
import customer.*;
import customerService.*;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.Pane;
import mainWindow.*;
import order.Order;
import ordersPayment.*;
import shop.ShopBoundary;
import surveyGui.ShowChoosenSurvey;
import surveyGui.SurveyResults;
import usersHomeWindows.*;
import usersManagment.AuthorizedCustomerBoundary;
import usersManagment.BranchEmployeeBoundary;
import usersManagment.BranchManagerBoundary;
import usersManagment.CEOBoundary;
import usersManagment.CourierBoundary;
import usersManagment.CustomerServiceEmployeeBoundary;
import usersManagment.MarketingEmployeeBoundary;
import usersManagment.UserBoundary;

public class GuiObjectsFactory {
	private static GuiObjectsFactory guiObjectsFactoryInstance;

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
	 * Load fxml file, return its controller
	 * 
	 * @param filePath should be "/package/filename.fxml"
	 * @return the controller
	 * @throws IOException if failed to load the fxml file
	 */
	public IGuiController loadFxmlFile(String filePath) {
		try {
			FXMLLoader loader = new FXMLLoader();
			final URL resource = getClass().getResource(filePath);
			loader.setLocation(resource);
			System.out.println(resource);
			loader.load();
			return (IGuiController) loader.getController();
		} catch (IOException e) {
			return null;
		}
	}

}
