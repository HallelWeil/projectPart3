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

	// gui controllers
	private static GuiObjectsFactory guiObjectsFactoryInstance;
	public MainWindowController mainWindowController;
	public LoginGuiController loginGuiController;
	public BtnController btnController;
	public UserHomeWindowGuiController userHomeWindowController;
	public StartingWindowGuiController startingWindowController;

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
	public NewComplaint newComplaintController;
	public UpdateComplaint updateComplaint;
	public ShowAllComplaints showAllComplaints;
	public SurveyResults surveyResultsController;
	public ShowChoosenSurvey showChoosenSurvey;

	// omer controlers
	public ManagerApproveController managerApproveController;
	public ManagerWatchReportController managerWatchReportController;
	public ManagerUpdateUser managerUsersManagmetController;
	public CEOcontroller ceoController;

	// or controllers
	public SearchSurverControllerGUI searchSurvey;
	public SurveyControllerGUI showSurvey;
	public CourierControllerGUI courierConfirmDelivery;

	// gui manager
	public BtnMenuManager btnMenuManager;
	public ProductsManager productManager;

	// Boundaries
	public CourierBoundary courierBoundary;
	public BranchEmployeeBoundary branchEmployeeBoundary;
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
		branchEmployeeBoundary = new BranchEmployeeBoundary();
		courierBoundary = new CourierBoundary();
		ceoBoundry = new CEOBoundary();
	}

	public void loadAllFxmlFiles() throws IOException {
		btnController = (BtnController) loadFxmlFile("/buttons/Buttons.fxml");
		mainWindowController = (MainWindowController) loadFxmlFile("/mainWindow/MainWindow.fxml");

		startingWindowController = (StartingWindowGuiController) loadFxmlFile("/mainWindow/StartingWindow.fxml");

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

		searchSurvey = (SearchSurverControllerGUI) loadFxmlFile("/branchEmployee/BranchEmpolyeeSearchSurvey.fxml");
		showSurvey = (SurveyControllerGUI) loadFxmlFile("/branchEmployee/BranchEmployeeSurveyWindow.fxml");

		courierConfirmDelivery = (CourierControllerGUI) loadFxmlFile("/courier/CourierGUI.fxml");
		
		newComplaintController = (NewComplaint) loadFxmlFile("/customerService/NewComplaint.fxml");
		updateComplaint = (UpdateComplaint) loadFxmlFile("/customerService/UpdateComplaint.fxml");
		showAllComplaints = (ShowAllComplaints) loadFxmlFile("/customerService/ShowAllComplaints.fxml");
		
		surveyResultsController = (SurveyResults) loadFxmlFile("/surveyGui/surveyResults.fxml");
		showChoosenSurvey = (ShowChoosenSurvey) loadFxmlFile("/surveyGui/ChoosenSurvey.fxml");
		managerApproveController = (ManagerApproveController) loadFxmlFile("/branchManager/approveOrder.fxml");
		managerWatchReportController = (ManagerWatchReportController) loadFxmlFile(
				"/branchManager/managerWatchReport.fxml");
		ceoController = (CEOcontroller) loadFxmlFile("/ceo/ceoWatchReport.fxml");

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

	public void resetAll() {
		mainWindowController.resetController();
		loginGuiController.resetController();
		userHomeWindowController.resetController();

	}
}
