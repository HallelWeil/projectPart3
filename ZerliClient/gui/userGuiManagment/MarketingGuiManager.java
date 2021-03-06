package userGuiManagment;

import PromotionWindow.CreatePromotionWindowController;
import PromotionWindow.managePromotionWindowController;
import main.GuiObjectsFactory;
import usersManagment.MarketingEmployeeBoundary;

public class MarketingGuiManager implements IUserGuiManager {

	private static MarketingGuiManager marketingGuiManager;

	private GuiObjectsFactory guiObjectsFactory = GuiObjectsFactory.getInstance();
	// the controllers
	private CreatePromotionWindowController createPromotion;
	private managePromotionWindowController managePromotions;
	// Boundaries
	private MarketingEmployeeBoundary marketingEmployeeBoundary;

	private MarketingGuiManager() {

	}

	public static MarketingGuiManager getInstance() {
		if (marketingGuiManager == null)
			marketingGuiManager = new MarketingGuiManager();
		return marketingGuiManager;
	}

	public CreatePromotionWindowController getCreatePromotion() {
		if (createPromotion == null) {
			createPromotion = (CreatePromotionWindowController) guiObjectsFactory
					.loadFxmlFile("/PromotionWindow/createNewPromotion.fxml");
		}
		return createPromotion;
	}

	public managePromotionWindowController getManagePromotions() {
		if (managePromotions == null) {
			managePromotions = (managePromotionWindowController) guiObjectsFactory
					.loadFxmlFile("/PromotionWindow/managePromotionsWindow.fxml");
		}
		return managePromotions;
	}

	public MarketingEmployeeBoundary getMarketingEmployeeBoundary() {
		if (marketingEmployeeBoundary == null) {
			marketingEmployeeBoundary = new MarketingEmployeeBoundary();
		}
		return marketingEmployeeBoundary;
	}

	@Override
	public void logout() {
		createPromotion = null;
		managePromotions = null;
		marketingEmployeeBoundary = null;
	}
}
