package shop;

import cart.Cart;
import catalog.Product;
import order.DeliveryDetails;
import order.Order;

public class CartController {

	private Cart myCart;
	
	public CartController()
	{
		myCart = new Cart();	
	}
	
	/**
	 * add item to cart
	 * @param product
	 */
	public void addToCart(Product product) {
		
	}
	/**
	 * remove product from cart
	 * @param product
	 */
	public void deleteFromCart(Product product) {
		
	}
	/**
	 * edit product amount
	 * @param product
	 * @param newAmount
	 */
	public void editAmount(Product product,int newAmount) {
		
	}
	/**
	 * calculate the new cart total price
	 * @return
	 */
	public double calculatePrice() {
		return 0;
	}
	
	/**
	 * add/replace greeting card
	 */
	public void addGreetingCard(String card) {
		
	}
	public void addDeliveryDetails(DeliveryDetails d) {
		
	}
	/**
	 * place the order, send to server and return the order object
	 */
	public Order placeOrder() {
		return null;
		
	}
}
