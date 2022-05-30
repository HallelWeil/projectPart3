package buttons;

import main.GuiObjectsFactory;
import mainWindow.MainWindowController;
import usersManagment.UserBoundary;

public class BtnMenuManager {

	private GuiObjectsFactory guiObjectsFactory = GuiObjectsFactory.getInstance();
	private BtnController btns;

	public BtnMenuManager() {
		btns = guiObjectsFactory.btnController;
	}

	public void setUserBtns() {
		guiObjectsFactory.mainWindowController.resetBtns();
		guiObjectsFactory.mainWindowController.setBtn(btns.getAccessibilityBtn(), 6);
		guiObjectsFactory.mainWindowController.setBtn(btns.getLogoutBtn(), 7);
		guiObjectsFactory.mainWindowController.setBtn(btns.getUserHomeBtn(), 1);
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
		guiObjectsFactory.mainWindowController.setBtn(btns.getReportsBtn(), 2);
	}

	public void setCustomerBtns() {
		guiObjectsFactory.mainWindowController.setBtn(btns.getOrderHistoryBtn(), 2);
		guiObjectsFactory.mainWindowController.setBtn(btns.getShopBtn(), 3);
	}

	public void setCurierBtns() {
		guiObjectsFactory.mainWindowController.setBtn(btns.getApproveDeliveryBtn(), 2);
	}

	public void setBranchEmployeeBtns() {
		guiObjectsFactory.mainWindowController.setBtn(btns.getViewSurveyBtn(), 2);
		guiObjectsFactory.mainWindowController.setBtn(btns.getEnterSurveyAnswersBtn(), 3);
	}

	public void setBranchManagerBtns() {
		guiObjectsFactory.mainWindowController.setBtn(btns.getReportsBtn(), 2);
		guiObjectsFactory.mainWindowController.setBtn(btns.getViewOrdersBtn(), 3);
		guiObjectsFactory.mainWindowController.setBtn(btns.getManageUsersBtn(), 4);
	}

	public void setMarketingEmployeeBtns() {
		guiObjectsFactory.mainWindowController.setBtn(btns.getCreatePromotionBtn(), 2);
		guiObjectsFactory.mainWindowController.setBtn(btns.getManagePromotionsBtn(), 3);
	}

	public void setCSEmployeeBtns() {
		guiObjectsFactory.mainWindowController.setBtn(btns.getCreateComplaintBtn(), 2);
		guiObjectsFactory.mainWindowController.setBtn(btns.getUpdateComplaintBtn(), 3);
		guiObjectsFactory.mainWindowController.setBtn(btns.getEnterSurveyresultBtn(), 4);
	}

	public void setLoginWindowBtns() {
		guiObjectsFactory.mainWindowController.resetBtns();
		guiObjectsFactory.mainWindowController.setBtn(btns.getHomeBtn(), 1);
		guiObjectsFactory.mainWindowController.setBtn(btns.getAccessibilityBtn(), 6);
		guiObjectsFactory.mainWindowController.setBtn(btns.getInfoBtn(), 7);
	}

	public void setMainWindowBtns() {
		guiObjectsFactory.mainWindowController.resetBtns();
		guiObjectsFactory.mainWindowController.setBtn(btns.getHomeBtn(), 1);
		guiObjectsFactory.mainWindowController.setBtn(btns.getAccessibilityBtn(), 6);
		guiObjectsFactory.mainWindowController.setBtn(btns.getLoginBtn(), 7);
	}

}
