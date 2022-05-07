package shop;

import cart.Cart;
import catalog.Product;

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
}
