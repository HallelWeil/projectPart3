package buttons;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import main.GuiObjectsFactory;
import main.IGuiController;
import usersManagment.UserBoundary;

public class BtnController implements IGuiController {

	private GuiObjectsFactory guiObjectsFactory = GuiObjectsFactory.getInstance();

	@FXML
	private AnchorPane basePane;
	@FXML
	private Button accessibilityBtn;

	@FXML
	private Button approveDeliveryBtn;

	@FXML
	private Button createComplaintBtn;

	@FXML
	private Button createPromotionBtn;

	@FXML
	private ImageView deleteBtn;

	@FXML
	private Button enterSurveyAnswersBtn;

	@FXML
	private Button enterSurveyresultBtn;

	@FXML
	private Button homeBtn;

	@FXML
	private Button infoBtn;

	@FXML
	private Button loginBtn;

	@FXML
	private Button logoutBtn;

	@FXML
	private Button manageUsersBtn;

	@FXML
	private Button orderHistoryBtn;

	@FXML
	private Button reportsBtn;

	@FXML
	private Button shopBtn;

	@FXML
	private Button updateComplaintBtn;

	@FXML
	private Button viewOrdersBtn;

	@FXML
	private Button viewSurveyBtn;

	@FXML
	private Button userHomeBtn;

	@FXML
	private ImageView logo;

	@FXML
	private Button managePromotionsBtn;

	@FXML
	void managePromotions(ActionEvent event) {
		guiObjectsFactory.managePromotions.openWindow();
	}

	@FXML
	void goToUserHomepage(ActionEvent event) {
		guiObjectsFactory.userHomeWindowController.openWindow();
	}

	@FXML
	void accessibility(ActionEvent event) {
		guiObjectsFactory.accessibilityPageController.openWindow();
	}

	@FXML
	void approvedelivery(ActionEvent event) {

	}

	@FXML
	void createComplaint(ActionEvent event) {

	}

	@FXML
	void createPromotion(ActionEvent event) {
		guiObjectsFactory.createPromotion.openWindow();
	}

	@FXML
	void getInfo(ActionEvent event) {

	}

	@FXML
	void goToHomepage(ActionEvent event) {
		guiObjectsFactory.mainWindowController.openWindow();
	}

	@FXML
	void login(ActionEvent event) {
		guiObjectsFactory.loginGuiController.openWindow();
	}

	@FXML
	void logout(ActionEvent event) {
		guiObjectsFactory.userBaundary.requestLogOut();
		guiObjectsFactory.resetAll();
		guiObjectsFactory.mainWindowController.openWindow();
	}

	@FXML
	void manageOrders(ActionEvent event) {
		guiObjectsFactory.managerApproveController.openWindow();
	}

	@FXML
	void manageUsers(ActionEvent event) {
		guiObjectsFactory.managerUsersManagmetController.openWindow();
	}

	@FXML
	void openOrdersHistory(ActionEvent event) {
		guiObjectsFactory.ordersHistoryController.openWindow();
	}

	@FXML
	void openReports(ActionEvent event) {
		guiObjectsFactory.managerWatchReportController.openWindow();
	}

	@FXML
	void openShop(ActionEvent event) {
		guiObjectsFactory.shopWindowController.openWindow();
	}

	@FXML
	void surveyAnswers(ActionEvent event) {

	}

	@FXML
	void surveyResult(ActionEvent event) {
		guiObjectsFactory.surveyResultsController.openWindow();
	}

	@FXML
	void updateComplaint(ActionEvent event) {

	}

	@FXML
	void viewSurvey(ActionEvent event) {

	}

	@Override
	public Pane getBasePane() {
		return basePane;
	}

	@Override
	public void resetController() {
		// accessibilityBtn.setDisable(true);
	}

	public Button getAccessibilityBtn() {
		return accessibilityBtn;
	}

	public Button getApproveDeliveryBtn() {
		return approveDeliveryBtn;
	}

	public Button getCreateComplaintBtn() {
		return createComplaintBtn;
	}

	public Button getCreatePromotionBtn() {
		return createPromotionBtn;
	}

	public ImageView getDeleteBtn() {
		return deleteBtn;
	}

	public Button getEnterSurveyAnswersBtn() {
		return enterSurveyAnswersBtn;
	}

	public Button getEnterSurveyresultBtn() {
		return enterSurveyresultBtn;
	}

	public Button getHomeBtn() {
		return homeBtn;
	}

	public Button getInfoBtn() {
		return infoBtn;
	}

	public Button getLoginBtn() {
		return loginBtn;
	}

	public Button getLogoutBtn() {
		return logoutBtn;
	}

	public Button getManageUsersBtn() {
		return manageUsersBtn;
	}

	public Button getOrderHistoryBtn() {
		return orderHistoryBtn;
	}

	public Button getReportsBtn() {
		return reportsBtn;
	}

	public Button getShopBtn() {
		return shopBtn;
	}

	public Button getUpdateComplaintBtn() {
		return updateComplaintBtn;
	}

	public Button getViewOrdersBtn() {
		return viewOrdersBtn;
	}

	public Button getViewSurveyBtn() {
		return viewSurveyBtn;
	}

	public Button getUserHomeBtn() {
		return userHomeBtn;
	}

	public Button getManagePromotionsBtn() {
		return managePromotionsBtn;
	}

	public ImageView getLogo() {
		return logo;
	}

	@Override
	public void openWindow() {
		// move to the next window
		guiObjectsFactory.mainWindowController.showNewWindow(basePane);
	}

	@FXML
	void logoBtnPress(MouseEvent event) {
		if (UserBoundary.getCurrentUser() == null) {
			guiObjectsFactory.mainWindowController.openWindow();
		} else {
			guiObjectsFactory.userHomeWindowController.openWindow();
		}
	}

}
