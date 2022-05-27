package shop;

import java.sql.Timestamp;
import java.util.ArrayList;

import cart.Cart;
import cart.ProductInCart;
import catalog.CustomizedProduct;
import catalog.Product;
<<<<<<< HEAD
import client.ClientController;
import client.MsgController;
import msg.MsgType;
=======
<<<<<<< Updated upstream
=======
import client.ClientController2;
import client.MsgController;
import msg.MsgType;
>>>>>>> Stashed changes
>>>>>>> origin/Ronen
import order.DeliveryDetails;
import order.Order;

public class CartController {

	private Cart myCart;
<<<<<<< HEAD
	private ArrayList<Product> CustomizedProductsInCart = new ArrayList<>();
	private ClientController clientController = ClientController.getInstance();

	public CartController() {
		myCart = new Cart();
=======
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
>>>>>>> origin/Ronen
	}

	/**
	 * crate new customized product
	 * 
	 * @param name
	 * @throws Exception
	 */
	public void addNewProductToCart(CustomizedProduct newProduct) {
		CustomizedProductsInCart.add(newProduct);
		myCart.addItem(newProduct, 1);
	}

	/**
	 * add item to a customized product
	 * 
	 * @param item
	 * @param Productname
	 */
	public void addItemToProduct(Product item, int productID) {

		for (int i = 0; i < CustomizedProductsInCart.size(); i++) {
			if (CustomizedProductsInCart.get(i) instanceof CustomizedProduct)
				if (CustomizedProductsInCart.get(i).getProductID() == productID) {
					((CustomizedProduct) CustomizedProductsInCart.get(i)).getItems().add(item);

				}
		}

	}

	/**
	 * delete item from a customized product
	 * 
	 * @param item
	 */
	public void deleteItemFromProduct(Product item, int productID) {
		for (int i = 0; i < CustomizedProductsInCart.size(); i++) {
			if (CustomizedProductsInCart.get(i) instanceof CustomizedProduct)
				if (CustomizedProductsInCart.get(i).getProductID() == productID) {
					((CustomizedProduct) CustomizedProductsInCart.get(i)).getItems().remove(item);

				}
		}

	}

	/**
	 * add item to cart
	 * 
	 * @param product
	 */
	public void addToCart(Product product, int amount) {

		myCart.addItem(product, amount);

	}

	/**
	 * remove product from cart
	 * 
	 * @param product
	 */
	public void deleteFromCart(Product product) {

		try {
			myCart.removeItem(product.getName());
			myCart.calculatePrice();
		} catch (Exception e) {

			e.printStackTrace();
		}
	}

	/**
	 * edit product amount in cart
	 * 
	 * @param product
	 * @param newAmount
	 */
	public void editAmount(Product product, int newAmount) {

		myCart.addItem(product, newAmount);
		myCart.calculatePrice();
	}

	/**
	 * calculate the new cart total price
	 * 
	 * @return new price for the cart
	 */
	public double calculatePrice() {
		return myCart.calculatePrice();
	}

	/**
	 * add replace greeting card
	 */
	public void addGreetingCard(String card) {

		myCart.setGreetingCard(card);
	}

	/**
	 * adding delivery information when choosing home delivery
	 * 
	 * @param details
	 */

	public void addDeliveryDetails(DeliveryDetails details) {
		myCart.setDeliveryDetails(details);

	}

	/**
	 * change the flag form false to true - home delivery was chosen
	 */
	public void chooseHomeDelivery() {
		myCart.setWithHomeDelivery(true);
	}

	/**
	 * choose a branch to ship or to pick the order
	 * 
	 * @param branchName
	 */
	public void chooseBranchForOrder(String branchName) {
		myCart.setBranchName(branchName);
	}

	/**
	 * place the order, send to server and return the order object
	 * 
	 * @return a new order
	 */
	public Order placeOrder() {
		MsgController msgController = clientController.sendMsg(MsgController.createPLACE_ORDER_REQUESTMsg(this.myCart));
		if (msgController.getType() == MsgType.RETURN_ORDER) {
			return msgController.getOrder();
		}
		return null;

	}

	/**
	 * pay for a new order ,send to server and return true when the payment is
	 * approved
	 * 
	 * @return false- when payment is not approved ,true - when payment is approved
	 */
	public boolean payForOrder() {
		MsgController msgController = clientController.sendMsg(MsgController.createPAY_FOR_ORDERMsg());
		if (msgController.getType() == MsgType.RETURN_PAYMENT_APPROVAL)
			return true;
		return false;
	}

	public void setArrivelOrPickupDateAndTime(Timestamp time) {
		myCart.setArrivalDate(time);
	}

}
