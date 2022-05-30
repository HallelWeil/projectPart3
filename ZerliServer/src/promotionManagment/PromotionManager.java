package promotionManagment;

import catalog.Product;
import common.Status;
import database.DBController;
import msg.Msg;
import msg.MsgType;
import promotion.Promotion;
import server.ServerMsgController;

public class PromotionManager {
	private Msg errorMsg = new Msg();
	private Msg completedMsg = new Msg();

	public PromotionManager() {
		errorMsg.type = MsgType.ERROR;
		errorMsg.data = "";
		completedMsg.type = MsgType.COMPLETED;
	}

	public Msg createNewPromotion(Promotion promotion) {
		DBController dbController = DBController.getInstance();
		if (!activePromotionInDB(promotion)) {
			return errorMsg;
		}
		if (dbController.savePromotion(promotion) != -1) {
			return completedMsg;
		}
		return errorMsg;
	}

	public Msg activatePromotion(int promotionNumber) {
		DBController dbController = DBController.getInstance();
		Promotion promotion = dbController.getPromotion(promotionNumber);
		if (promotion == null) {
			errorMsg.data = "Promotion not found";
			return errorMsg;
		}
		if (promotion.getStatus() == Status.Active) {// the promotion is already active
			errorMsg.data = "The promotion is already active";
			return errorMsg;
		}
		if (!activePromotionInDB(promotion)) {
			return errorMsg;
		}
		if (dbController.updatePromotion(promotionNumber, Status.Active)) {
			return completedMsg;
		}
		return errorMsg;

	}

	public Msg deactivatePromotion(int promotionNumber) {
		DBController dbController = DBController.getInstance();
		Promotion promotion = dbController.getPromotion(promotionNumber);
		if (promotion == null) {
			errorMsg.data = "Promotion not found";
			return errorMsg;
		}
		if (promotion.getStatus() == Status.Canceled) {// the promotion is already active
			errorMsg.data = "The promotion is already not active";
			return errorMsg;
		}
		Product product = DBController.getInstance().getProduct(promotion.getProductID());
		if (product == null) {// product not found
			errorMsg.data = "Product not found";
			return errorMsg;
		}
		product.setPrice(product.getOldPrice());
		product.setOldPrice(0);
		if (!dbController.updateProduct(product)) {
			errorMsg.data = "Failed to update product price";
			return errorMsg;
		}
		if (dbController.updatePromotion(promotionNumber, Status.Canceled)) {
			return completedMsg;
		}
		return errorMsg;
	}

	public Msg getAllPromotions() {
		DBController dbController = DBController.getInstance();
		return ServerMsgController.createRETURN_ALL_PROMOTIONSMsg(dbController.getAllPromotions());
	}

	private double calculateNewPrice(double price, double discount) {
		Double newPrice = price - price * discount;
		// create the price
		newPrice = newPrice.intValue() + 0.99;
		return newPrice;
	}

	private boolean activePromotionInDB(Promotion promotion) {
		Product product = DBController.getInstance().getProduct(promotion.getProductID());
		if (product == null) {// product not found
			errorMsg.data = "Product not found";
			return false;
		}

		if (product.getOldPrice() != 0) {// there is already active promotion for this product
			errorMsg.data = "There is already active promotion for this product";
			return false;
		}
		product.setOldPrice(product.getPrice());
		product.setPrice(calculateNewPrice(product.getPrice(), promotion.getDiscount()));
		if (!DBController.getInstance().updateProduct(product)) {
			errorMsg.data = "Failed to update product price";
			return false;
		}
		return true;
	}

}
