package buttons;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import main.IGuiController;

public class BtnController implements IGuiController {

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

	@Override
	public Pane getBasePane() {
		return basePane;
	}

	@Override
	public void resetController() {
		// TODO Auto-generated method stub
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

}
