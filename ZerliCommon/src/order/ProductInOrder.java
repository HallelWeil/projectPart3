package order;

import java.io.Serializable;

public class ProductInOrder implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int orderNumber;
	private String name;
	private double price;
	private int amount;
	private double total;
	private String category;
	

		public double getTotal() {
		return total;
	} 
		
	public int getOrderNumber() {
		return orderNumber;
	}
	public void setOrderNumber(int orderNumber) {
		this.orderNumber = orderNumber;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
		this.total=amount*price;
	}
	public int getAmount() {
		return amount;
	}
	public void setAmount(int amount) {
		this.amount = amount;
		this.total=amount*price;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}

}
