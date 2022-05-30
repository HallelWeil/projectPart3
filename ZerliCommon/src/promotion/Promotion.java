package promotion;

import java.io.Serializable;
import java.sql.Timestamp;

import common.Status;

public class Promotion implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private int productID;
	private String promotionText;
	private Status status;
	private double discount;
	private int promotionNumber;
	private Timestamp creationDate;

	public Promotion() {		
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

	public double getDiscount() {
		return discount;
	}
	public Timestamp getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(Timestamp creationDate) {
		this.creationDate = creationDate;
	}

	public void setDiscount(double discount) {
		this.discount = discount;
	}

	public int getPromotionNumber() {
		return promotionNumber;
	}

	public void setPromotionNumber(int promotionNumber) {
		this.promotionNumber = promotionNumber;
	}

}
