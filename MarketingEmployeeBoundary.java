package usersManagment;

import java.util.ArrayList;

import catalog.Product;
import promotion.Promotion;
import promotions.PromotionController;
import shop.CatalogController;

public class MarketingEmployeeBoundary {

	private PromotionController promotionController;
	private  CatalogController catalogController;

	public MarketingEmployeeBoundary() {
		promotionController = new PromotionController();
		catalogController = new CatalogController();
	}
	/**
	 * update the catalog send to server and return true when the product is updated
	 * @param product
	 * @return true- when the product is updated , false- when can't update a product
	 */

	public void updateCatalog(Product product) throws Exception{
		catalogController.updateCatalog( product);

	}

	public void requestcreateNewPromotion(int productID, double discount, String promotionText) throws Exception {

		promotionController.createNewPromotion(productID, discount, promotionText);

		// in case activate get error the activatePromotion method throw exception that
		// come here
		// and here throw to the method who called it (GUI method catch and display the
		// ERROR and why)

	}
	 public ArrayList<Product> chooseCategory(String category){
		return catalogController.chooseCategory(category);
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
