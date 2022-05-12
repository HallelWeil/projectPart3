package promotion;

import java.io.Serializable;

import common.Status;

public class Promotion implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private int productID;
	private String promotionText;
	private Status status;

	public Promotion(int productID, String promotionText) {
		this.productID = productID;
		this.promotionText = promotionText;
		status = Status.Active;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public int getProductID() {
		return productID;
	}

	public String getPromotionText() {
		return promotionText;
	}

	public void setProductID(int productID) {
		this.productID = productID;
	}

	public void setPromotionText(String promotionText) {
		this.promotionText = promotionText;
	}

}
