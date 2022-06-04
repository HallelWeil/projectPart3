package buttons;

import userGuiManagment.MainWindowGuiManager;
import usersManagment.UserBoundary;

/**
 * manage the top menu. place the correct buttons for each type of user
 *
 */
public class BtnMenuManager {

	private MainWindowGuiManager mainWindowManager = MainWindowGuiManager.getInstance();

	private BtnController btns;

	public BtnMenuManager() {
		btns = mainWindowManager.btnController;
	}

	public void setUserBtns() {
		mainWindowManager.mainWindowController.resetBtns();
		mainWindowManager.mainWindowController.setBtn(btns.getAccessibilityBtn(), 6);
		mainWindowManager.mainWindowController.setBtn(btns.getLogoutBtn(), 7);
		mainWindowManager.mainWindowController.setBtn(btns.getUserHomeBtn(), 1);
		// for each user type init different btns
		switch (UserBoundary.getCurrentUser().getUserType()) {
		case AuthorizedCustomer:
			setCustomerBtns();
			break;
		case BranchManager:
			setBranchManagerBtns();
			break;
		case BranchEmployee:
			setBranchEmployeeBtns();
			break;
		case CustomerServiceEmloyee:
			setCSEmployeeBtns();
			break;
		case MarketingEmployee:
			setMarketingEmployeeBtns();
			break;
		case Courier:
			setCurierBtns();
			break;
		case CEO:
			setCEObtns();
			break;
		default:
			break;
		}
	}

	public void setCEObtns() {
		mainWindowManager.mainWindowController.setBtn(btns.getReportsBtn(), 2);
	}

	public void setCustomerBtns() {
		mainWindowManager.mainWindowController.setBtn(btns.getOrderHistoryBtn(), 2);
		mainWindowManager.mainWindowController.setBtn(btns.getShopBtn(), 3);
	}

	public void setCurierBtns() {
		mainWindowManager.mainWindowController.setBtn(btns.getApproveDeliveryBtn(), 2);
	}

	public void setBranchEmployeeBtns() {
		mainWindowManager.mainWindowController.setBtn(btns.getEnterSurveyAnswersBtn(), 2);
	}

	public void setBranchManagerBtns() {
		mainWindowManager.mainWindowController.setBtn(btns.getReportsBtn(), 2);
		mainWindowManager.mainWindowController.setBtn(btns.getViewOrdersBtn(), 3);
		mainWindowManager.mainWindowController.setBtn(btns.getManageUsersBtn(), 4);
	}

	public void setMarketingEmployeeBtns() {
		mainWindowManager.mainWindowController.setBtn(btns.getCreatePromotionBtn(), 2);
		mainWindowManager.mainWindowController.setBtn(btns.getManagePromotionsBtn(), 3);
	}

	public void setCSEmployeeBtns() {
		mainWindowManager.mainWindowController.setBtn(btns.getCreateComplaintBtn(), 2);
		mainWindowManager.mainWindowController.setBtn(btns.getUpdateComplaintBtn(), 3);
		mainWindowManager.mainWindowController.setBtn(btns.getEnterSurveyresultBtn(), 4);
	}

	public void setLoginWindowBtns() {
		mainWindowManager.mainWindowController.resetBtns();
		mainWindowManager.mainWindowController.setBtn(btns.getHomeBtn(), 1);
		mainWindowManager.mainWindowController.setBtn(btns.getAccessibilityBtn(), 6);
		mainWindowManager.mainWindowController.setBtn(btns.getInfoBtn(), 7);
	}

	public void setMainWindowBtns() {
		mainWindowManager.mainWindowController.resetBtns();
		mainWindowManager.mainWindowController.setBtn(btns.getHomeBtn(), 1);
		mainWindowManager.mainWindowController.setBtn(btns.getAccessibilityBtn(), 6);
		mainWindowManager.mainWindowController.setBtn(btns.getLoginBtn(), 7);
	}

}
