package shop;

import cart.Cart;
import catalog.Product;
<<<<<<< Updated upstream
=======
import client.ClientController2;
import client.MsgController;
import msg.MsgType;
>>>>>>> Stashed changes
import order.DeliveryDetails;
import order.Order;

public class CartController {

	private Cart myCart;
<<<<<<< Updated upstream
	
	public CartController()
	{
		myCart = new Cart();	
=======
	private ArrayList<Product> CustomizedProductsInCart = new ArrayList<>();
	private ClientController2 clientController;

	public CartController() {
		myCart = new Cart();
>>>>>>> Stashed changes
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
