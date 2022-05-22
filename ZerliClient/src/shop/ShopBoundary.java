package shop;

import java.util.ArrayList;

import catalog.Item;
import catalog.Product;
import order.DeliveryDetails;
import order.Order;

public class ShopBoundary {

	private CartController cartCon;
	private CatalogController catalogCon;
	private boolean homeDeliveryflag = false;

	public ShopBoundary() {
		this.cartCon = new CartController();
		this.catalogCon = new CatalogController();
	}

	// category methods//
	/**
	 * choose a category to browse the catalog
	 * 
	 * @param category
	 * @return 
	 */
	public ArrayList<Product> chooseCategory(String category) {
		return catalogCon.chooseCategory(category);

	}

	// cart methods //
	/**
	 * add a new customized product to the cart
	 * 
	 * @param newProudctName
	 * @throws an exception -when the user tries to add a new product that has a
	 *            name that already exist in the cart
	 */
	public void requestCustomizedProduct(String newProudctName) {
		try {
			cartCon.createNewProduct(newProudctName);
		} catch (Exception e) {

			e.printStackTrace();
		}

	}

	/**
	 * add a product to the cart
	 * 
	 * @param product
	 */
	public void addToCart(Product product) {
		cartCon.addToCart(product);

	}

	/**
	 * add a new item to a customized product
	 * 
	 * @param item
	 * @param productName
	 */

	public void addItemToProduct(Item item, String productName) {
		cartCon.addItemToProduct(item, productName);

	}

	/**
	 * delete an item from customized product
	 * 
	 * @param productName
	 * @param item
	 */

	public void deleteItemFromCart(String productName, Item item) {
		cartCon.deleteItemFromProduct(item, productName);
	}

	/**
	 * delete a product form the cart
	 * 
	 * @param product
	 */
	public void deleteProductFromCart(Product product) {
		cartCon.deleteFromCart(product);
	}

	/**
	 * choose an amount of product in the cart
	 * 
	 * @param product
	 * @param amount
	 */
	public void chooseAmount(Product product, int amount) {
		cartCon.editAmount(product, amount);
	}

	/**
	 * public void openCart() {
	 * 
	 * show cart in full window?? }
	 */
	/**
	 * place a new order and return an order object
	 * 
	 * @return a order
	 */
	public Order placeOrder() {
		return cartCon.placeOrder();
	}

	/**
	 * add a personal letter to the order
	 * 
	 * @param letter
	 */
	public void addPersonalLetter(String letter) {
		cartCon.addGreetingCard(letter);
	}

	/**
	 * setting a local flag- when true home delivery was chosen
	 * 
	 * @param isHomedelivery, true- when home delivery is chosen ,false - when a
	 *                        store pickup is chosen
	 */
	public void selectDeliveryOption(boolean isHomedelivery) {

		homeDeliveryflag = isHomedelivery;

	}

	/**
	 * choose a branch name for this order
	 * 
	 * @param branchName
	 */
	public void chooseBranch(String branchName) {
		cartCon.chooseBranchForOrder(branchName);

	}

	/**
	 * send the required details for home delivery option
	 * 
	 * @param details
	 */
	public void sumbmitDetailsForHomeDelivery(DeliveryDetails details) {
		if (homeDeliveryflag)
			cartCon.addDeliveryDetails(details);

	}

	/**
	 * send a request to the pay for current order
	 * 
	 * @return true - when the payment is approved , false- when payment wasn't
	 *         completed successfully
	 */
	public boolean payForOrder() {
		return cartCon.payForOrder();
	}

}
