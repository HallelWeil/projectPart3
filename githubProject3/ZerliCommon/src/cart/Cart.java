package cart;

import java.io.Serializable;
import java.util.ArrayList;

import catalog.Product;
import order.DeliveryDetails;

public class Cart implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private ArrayList<ProductInCart> productsInCart;
	private double price;
	private boolean withGreetingCard;
	private boolean withHomeDelivery;
	private String greetingCard;
	private DeliveryDetails deliveryDetails;

	public Cart() {
		productsInCart = new ArrayList<ProductInCart>();
		withGreetingCard = false;
		withHomeDelivery = false;
		greetingCard = null;
		deliveryDetails = null;
		price = 0;
	}

	/**
	 * 
	 * add item to the cart, if the item already in the cart add the amount to the
	 * existing item amount
	 * 
	 * @param product the product we want to add
	 * @param amount  the product amount
	 */
	public void addItem(Product product, int amount) {
		ProductInCart newProduct = new ProductInCart(product, amount);
		if (productsInCart.contains(newProduct))
			productsInCart.get(productsInCart.indexOf(newProduct)).addAmount(amount);
		else
			productsInCart.add(newProduct);
	}

	public double getPrice() {
		return price;
	}

	/**
	 * calculate and return the total price for the cart
	 * 
	 */
	public double calculatePrice() {
		double newPrice = 0;
		for (ProductInCart item : productsInCart) {
			newPrice += item.getAmount() * item.getProduct().getPrice();
		}
		this.price = newPrice;
		return newPrice;
	}

	public String getGreetingCard() {
		return greetingCard;
	}

	public void setGreetingCard(String greetingCard) {
		this.greetingCard = greetingCard;
	}

	public DeliveryDetails getDeliveryDetails() {
		return deliveryDetails;
	}

	public void setDeliveryDetails(DeliveryDetails deliveryDetails) {
		this.deliveryDetails = deliveryDetails;
	}

}
