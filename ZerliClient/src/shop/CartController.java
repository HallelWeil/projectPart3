package shop;

import java.util.ArrayList;

import cart.Cart;
import catalog.CustomizedProduct;
import catalog.Item;
import catalog.Product;
import client.ClientController;
import client.MsgController;
import msg.MsgType;
import order.DeliveryDetails;
import order.Order;

public class CartController {

	private Cart myCart;
	private ArrayList<Product> CustomizedProductsInCart = new ArrayList<>();
	private ClientController clientController;

	public CartController() {
		myCart = new Cart();
	}

	/**
	 * crate new customized product
	 * 
	 * @param name
	 * @throws Exception
	 */
	public void createNewProduct(String name) throws Exception {
		CustomizedProduct newProduct = new CustomizedProduct(-1, name);
		if (CustomizedProductsInCart.contains(newProduct)) {
			throw new Exception("item  name allready exist");

		}
		CustomizedProductsInCart.add(newProduct);
		myCart.addItem(newProduct, 1);

	}

	/**
	 * add item to a customized product
	 * 
	 * @param item
	 */
	public void addItemToProduct(Item item, String Productname) {

		for (int i = 0; i < CustomizedProductsInCart.size(); i++) {
			if (CustomizedProductsInCart.get(i) instanceof CustomizedProduct)
				if (CustomizedProductsInCart.get(i).getName().equals(Productname)) {
					((CustomizedProduct) CustomizedProductsInCart.get(i)).getItemsList().add(item);

				}
		}

	}

	/**
	 * delete item from a customized product
	 * 
	 * @param item
	 */
	public void deleteItemFromProduct(Item item, String Productname) {
		for (int i = 0; i < CustomizedProductsInCart.size(); i++) {
			if (CustomizedProductsInCart.get(i) instanceof CustomizedProduct)
				if (CustomizedProductsInCart.get(i).getName().equals(Productname)) {
					((CustomizedProduct) CustomizedProductsInCart.get(i)).getItemsList().remove(item);
					
				}
		}

	}

	/**
	 * add item to cart
	 * 
	 * @param product
	 */
	public void addToCart(Product product) {

		myCart.addItem(product, 1);
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
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * edit product amount
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
	 * @return
	 */
	public double calculatePrice() {
		return myCart.calculatePrice();
	}

	/**
	 * add/replace greeting card
	 */
	public void addGreetingCard(String card) {

		myCart.setGreetingCard(card);
	}

	public void addDeliveryDetails(DeliveryDetails d) {
		myCart.setDeliveryDetails(d);

	}

	/**
	 * place the order, send to server and return the order object
	 */
	public Order placeOrder() {
		MsgController msgController = clientController.sendMsg(MsgController.createPLACE_ORDER_REQUESTMsg(this.myCart));
		if (msgController.getType() == MsgType.RETURN_ORDER) {
			return msgController.getOrder();
		}
		return null;

	}
}
