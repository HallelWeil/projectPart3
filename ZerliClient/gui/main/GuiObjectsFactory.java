package main;

import java.io.IOException;
import java.net.URL;

import PromotionWindow.*;
import accessibility.AccessibilityPageController;
import branchManager.ManagerApproveController;
import branchManager.ManagerUpdateUser;
import branchManager.ManagerWatchReportController;
import buttons.*;
import client.ClientBoundary;
import customer.*;
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
import usersManagment.BranchManagerBoundary;
import usersManagment.CEOBoundary;
import usersManagment.CustomerServiceEmployeeBoundary;
import usersManagment.MarketingEmployeeBoundary;
import usersManagment.UserBoundary;

public class GuiObjectsFactory {

	// gui controllers
	private static GuiObjectsFactory guiObjectsFactoryInstance;
	public MainWindowController mainWindowController;
	public LoginGuiController loginGuiController;
	public BtnController btnController;
	public UserHomeWindowGuiController userHomeWindowController;
	public ShopWindowController shopWindowController;
	public OrdersHistoryController ordersHistoryController;
	public OrderDetailsController orderDetailsController;

	public personalcardWindowController personalCardcontroller;
	public HomeDeliveryWindowController HomeDeliveryDetails;
	public ConfirmOrderWindowController confirmOrder;
	public SuccedPayWindowController succedfailedpay;
	public failedtopayWindowController failedpay;
	public CreatePromotionWindowController createPromotion;
	public managePromotionWindowController managePromotions;
	public BranchDeliveryChooseWindowController branch_Delivery;
	public AccessibilityPageController accessibilityPageController;

	// ofir controllers
	public customerService.NewComplaint newComplaintController;
	public customerService.UpdateComplaint updateController;
	public SurveyResults surveyResultsController;
	public ShowChoosenSurvey showChoosenSurvey;

	// omer controlers
	public ManagerApproveController managerApproveController;
	public ManagerWatchReportController managerWatchReportController;
	public ManagerUpdateUser managerUsersManagmetController;

	// gui manager
	public BtnMenuManager btnMenuManager;
	public ProductsManager productManager;

	// Boundaries
	public UserBoundary userBaundary;
	public ShopBoundary shopBoundary;
	public CustomerServiceEmployeeBoundary employeeServiceBoundary;
	public ClientBoundary clientBoundary = new ClientBoundary();
	public BranchManagerBoundary branchManagerBoundary;
	public CEOBoundary ceoBoundry;
	public AuthorizedCustomerBoundary authorizedCustomerBoundary;
	public MarketingEmployeeBoundary marketingEmployeeBoundary;
	public Order order;

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
		branchManagerBoundary = new BranchManagerBoundary();
		authorizedCustomerBoundary = new AuthorizedCustomerBoundary();
		marketingEmployeeBoundary = new MarketingEmployeeBoundary();
		employeeServiceBoundary = new CustomerServiceEmployeeBoundary();
	}

	public void loadAllFxmlFiles() throws IOException {
		btnController = (BtnController) loadFxmlFile("/buttons/Buttons.fxml");
		mainWindowController = (MainWindowController) loadFxmlFile("/mainWindow/MainWindow.fxml");
		loginGuiController = (LoginGuiController) loadFxmlFile("/mainWindow/LoginWindow.fxml");
		userHomeWindowController = (UserHomeWindowGuiController) loadFxmlFile("/usersHomeWindows/UserHomeWindow.fxml");
		shopWindowController = (ShopWindowController) loadFxmlFile("/customer/ShopWindow.fxml");
		ordersHistoryController = (OrdersHistoryController) loadFxmlFile("/customer/OrdersHistory.fxml");
		orderDetailsController = (OrderDetailsController) loadFxmlFile("/customer/OrderDetailsWindow.fxml");
		accessibilityPageController = (AccessibilityPageController) loadFxmlFile(
				"/accessibility/AccessibilityWindow.fxml");

		personalCardcontroller = (personalcardWindowController) loadFxmlFile("/ordersPayment/PersonalCardWindow.fxml");
		HomeDeliveryDetails = (HomeDeliveryWindowController) loadFxmlFile("/ordersPayment/HomeDeliveryWindow.fxml");
		confirmOrder = (ConfirmOrderWindowController) loadFxmlFile("/ordersPayment/ConfirmOrderWindow.fxml");
		succedfailedpay = (SuccedPayWindowController) loadFxmlFile("/ordersPayment/SuccedPayWindow.fxml");
		failedpay = (failedtopayWindowController) loadFxmlFile("/ordersPayment/FailedToPayWindow.fxml");
		branch_Delivery = (BranchDeliveryChooseWindowController) loadFxmlFile(
				"/ordersPayment/Branch&DeliveryChoose.fxml");

		createPromotion = (CreatePromotionWindowController) loadFxmlFile("/PromotionWindow/createNewPromotion.fxml");
		managePromotions = (managePromotionWindowController) loadFxmlFile(
				"/PromotionWindow/managePromotionsWindow.fxml");

		managerUsersManagmetController = (ManagerUpdateUser) loadFxmlFile("/branchManager/userInfoUpdate.fxml");

		// newComplaintController = (NewComplaint)
		// loadFxmlFile("/customerService/NewComplaint.fxml");
		// updateController = (UpdateComplaint)
		// loadFxmlFile("/customerService/UpdateComplaint.fxml");
		surveyResultsController = (SurveyResults) loadFxmlFile("/surveyGui/surveyResults.fxml");
		showChoosenSurvey = (ShowChoosenSurvey) loadFxmlFile("/surveyGui/ChoosenSurvey.fxml");
		managerApproveController = (ManagerApproveController) loadFxmlFile("/branchManager/approveOrder.fxml");
		managerWatchReportController = (ManagerWatchReportController) loadFxmlFile(
				"/branchManager/managerWatchReport.fxml");

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
