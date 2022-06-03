package order;

import java.io.Serializable;

public class ProductInOrder implements Serializable {

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
	private String data = "";

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
		if (name.length() > 100)
			return name.subSequence(0, 100).toString();
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
		this.total = amount * price;
	}

	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
		this.total = amount * price;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String toString() {
		StringBuilder s = new StringBuilder();
		s.append("[ ");
		s.append(orderNumber + ", " + name + ", " + price + ", " + amount + ", ");
		s.append(total + ", " + category + "]");
		return s.toString();
	}

	public String getData() {
		if (data.length() > 200)
			return data.subSequence(0, 200).toString();
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}

	public void addData(String s) {
		data += s;
	}
}
