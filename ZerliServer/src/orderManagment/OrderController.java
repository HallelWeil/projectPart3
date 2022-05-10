package orderManagment;

import java.util.ArrayList;

import cart.Cart;
import cart.ProductInCart;
import catalog.CustomizedProduct;
import catalog.Item;
import order.Order;
import order.OrderStatus;
import order.ProductInOrder;

/**
 * create a order from a cart, manage orders status and approval
 * 
 * @author halel
 *
 */
public class OrderController {

	private Order activeOrder;

	public Order placeOrder(Cart cart, int orderNumber) {
		if (cart == null)
			return null;
		Order newOrder = new Order();
		newOrder.setOrderID(orderNumber);
		newOrder.setBranchName(cart.getBranchName());
		newOrder.setArrivalDate(cart.getArrivalDate());
		newOrder.setOrderData(null);// for future use
		newOrder.setHomeDelivery(cart.isWithHomeDelivery());
		newOrder.setDeliveryDetails(cart.getDeliveryDetails());
		newOrder.setOrderStatus(OrderStatus.WAITING_FOR_PAYMENT);
		// calculate the price and get the items
		ArrayList<ProductInOrder> items = new ArrayList<ProductInOrder>();
		newOrder.setPrice(calculateOrderPriceAndGetItems(cart.getProductsInCart(), items));
		newOrder.setItems(items);
		newOrder.setPersonalLetter(cart.getGreetingCard());
		activeOrder = newOrder;
		return newOrder;
	}

	public boolean payForOrder(String cardInfo) {
		return true;
		
	}
	
	public Order getActiveOrder() {
		return activeOrder;
	}



	/**
	 * calculate the products price and place the items in the items list
	 * 
	 * @param products
	 * @param items
	 * @return
	 */
	private double calculateOrderPriceAndGetItems(ArrayList<ProductInCart> products, ArrayList<ProductInOrder> items) {
		double price = 0, tempPrice;
		ProductInOrder tempProductInOrder;
		// for each product in the list
		for (ProductInCart product : products) {
			tempPrice = 0;
			// if its a customized product calculate the price base on the items cost
			if (product.getProduct() instanceof CustomizedProduct) {
				CustomizedProduct tempProduct = (CustomizedProduct) (product.getProduct());
				for (Item item : tempProduct.getItemsList()) {
					tempPrice += item.getPrice();
				}
			}
			// for regular product
			else {
				tempPrice = product.getProduct().getPrice();
			}
			tempProductInOrder = new ProductInOrder();
			tempProductInOrder.setAmount(product.getAmount());
			tempProductInOrder.getCategory();// for future use
			tempProductInOrder.setOrderNumber(0);// no number for now
			tempProductInOrder.setName(product.getProduct().getName());
			tempProductInOrder.setPrice(tempPrice);
			tempPrice = tempPrice * product.getAmount();
			items.add(tempProductInOrder);
			price += tempPrice;
		}
		return price;
	}
}
