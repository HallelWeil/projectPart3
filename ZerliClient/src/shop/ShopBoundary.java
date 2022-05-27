package shop;

import java.sql.Timestamp;
import java.util.ArrayList;

import cart.ProductInCart;
import catalog.CustomizedProduct;
import catalog.Product;
import order.DeliveryDetails;
import order.Order;

public class ShopBoundary {

	private CartController cartCon;
	private CatalogController catalogCon;
	private boolean homeDeliveryflag = false;
	private boolean personalCard = false;
	private ArrayList<Integer> cartItemsIDs;

	public ShopBoundary() {
		this.cartCon = new CartController();
		this.catalogCon = new CatalogController();
		cartItemsIDs = new ArrayList<Integer>();
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
	 */
	public void addNewCustomizedProduct(CustomizedProduct proudct) {
		cartCon.addNewProductToCart(proudct);
	}

	/**
	 * add a product to the cart
	 * 
	 * @param product
	 */
	public boolean addToCart(Product product, int amount) {
		cartCon.addToCart(product, amount);
		if (cartItemsIDs.contains(product.getProductID()))
			return false;
		else
			cartItemsIDs.add(product.getProductID());
		return true;

	}

	/**
	 * add a new item to a customized product
	 * 
	 * @param item
	 * @param productName
	 */

	public void addItemToProduct(Product item, int productID) {
		cartCon.addItemToProduct(item, productID);

	}

	/**
	 * delete an item from customized product
	 * 
	 * @param productName
	 * @param item
	 */

	public void deleteItemFromCart(int productID, Product item) {
		cartCon.deleteItemFromProduct(item, productID);
		if (cartItemsIDs.contains(productID))
			cartItemsIDs.remove(cartItemsIDs.indexOf(productID));
	}

	/**
	 * delete a product form the cart
	 * 
	 * @param product
	 * @return
	 */
	public boolean deleteProductFromCart(Product product) {
		cartCon.deleteFromCart(product);
		if (cartItemsIDs.contains(product.getProductID())) {
			cartItemsIDs.remove(cartItemsIDs.indexOf(product.getProductID()));
			return true;
		}
		return false;
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

	public boolean isHomeDeliveryflag() {
		return homeDeliveryflag;
	}

	public boolean isPersonalCardflag() {
		return personalCard;
	}

	public void selectPersonalCard(boolean b) {
		personalCard = b;	
	}

	public void submitDetailsForArivalDate(Timestamp arrivalDate) {
		cartCon.setArrivelOrPickupDateAndTime(arrivalDate);		
	}
	

}
