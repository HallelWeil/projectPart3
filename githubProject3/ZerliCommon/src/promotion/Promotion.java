package promotion;

import java.io.Serializable;

public class Promotion implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private int productID;
	private double discount;
	private String promotionText;

	public Promotion(int productID, double discount, String promotionText) {
		super();
		this.productID = productID;
		this.discount = discount;
		this.promotionText = promotionText;
	}

	public int getProductID() {
		return productID;
	}

	public double getDiscount() {
		return discount;
	}

	public String getPromotionText() {
		return promotionText;
	}

	public void setProductID(int productID) {
		this.productID = productID;
	}

	public void setDiscount(double discount) {
		this.discount = discount;
	}

	public void setPromotionText(String promotionText) {
		this.promotionText = promotionText;
	}

}
