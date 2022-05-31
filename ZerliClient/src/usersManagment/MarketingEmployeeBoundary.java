package usersManagment;

import java.util.ArrayList;

import promotion.Promotion;
import promotions.PromotionController;

public class MarketingEmployeeBoundary {

	private PromotionController promotionController;

	public MarketingEmployeeBoundary() {
		promotionController = new PromotionController();
	}

	public void updateCatalog() {

	}

	public void requestcreateNewPromotion(int productID, double discount, String promotionText) throws Exception {

		promotionController.createNewPromotion(productID, discount, promotionText);

		// in case activate get error the activatePromotion method throw exception that
		// come here
		// and here throw to the method who called it (GUI method catch and display the
		// ERROR and why)

	}

	public void activatePromotion(int promotionNumber) throws Exception {
		promotionController.activatePromotion(promotionNumber);
	}

	public void deActivatePromotion(int promotionNumber) throws Exception {
		promotionController.endPromotion(promotionNumber);
	}

	public ArrayList<Promotion> getAllPromotions() {
		return promotionController.getAllPromotions();
	}
}
