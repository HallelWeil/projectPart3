 package order;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.ArrayList;

import common.Status;

/**
 * Order entity, implements Serializable and can be used in messages
 * 
 * @author hallel
 *
 */
public class Order implements Serializable {

	/**
	 * the order version, must be the same in both client and server
	 */
	private static final long serialVersionUID = 6L;
	/**
	 * max size for the greeting card field
	 */
	public final int GREETING_CARD_MAX_SIZE = 200;
	/**
	 * The unique order id number
	 */
	private int orderNumber;
	private Timestamp orderDate;
<<<<<<< HEAD
	private Timestamp arrivalDate;
	private String branchName;
	private boolean homeDelivery;
	private double price;
	private String personalLetter;
	private OrderStatus orderStatus;
	private String data;
	private ArrayList<ProductInOrder> items;
	private DeliveryDetails deliveryDetails;
	private String username;

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}
=======
	private String dOrder;
    private OrderStatus status= OrderStatus.WaitingForAprroval;
>>>>>>> origin/Ronen

	/**
	 * Set the order data string, up to 45 characters
	 * 
	 * @param OrderData
	 */
	public void setOrderData(String OrderData) {
		data = OrderData;
	}

	/**
	 * Get the order data string
	 * 
	 * @return the order data string
	 */
	public String getOrderData() {
		return data;
	}

	/**
	 * Get the order id number
	 * 
	 * @return the order id number
	 */
	public int getOrderNumber() {
		return orderNumber;
	}

	/**
	 * Set the order id, must be positive number
	 * 
	 * @param orderID
	 */
	public void setOrderNumber(int orderID) {
		if (orderID >= 0)
			this.orderNumber = orderID;
	}

	/**
	 * Get the order price
	 * 
	 * @return the order price
	 */
	public double getPrice() {
		return price;
	}

	/**
	 * Set the order price, must be positive number
	 * 
	 * @param price
	 */
	public void setPrice(double price) {
		if (price > 0)
			this.price = price;
	}

	/**
	 * Get the Greeting Card
	 * 
	 * @return the Greeting Card
	 */
	public String getPersonalLetter() {
		return personalLetter;
	}

	/**
	 * Set the greeting card string ,must be <= from GREETING_CARD_MAX_SIZE
	 * 
	 * @param greetingCard
	 */
	public void setPersonalLetter(String personalLetter) {
		if (personalLetter != null)
			if (personalLetter.length() <= GREETING_CARD_MAX_SIZE)
				this.personalLetter = personalLetter;
	}

	/**
	 * Get the order creation date
	 * 
	 * @return the order creation date
	 */
	public Timestamp getOrderDate() {
		return orderDate;
	}

	/**
	 * Set the order creation date
	 * 
	 * @param orderDate
	 */
	public void setOrderDate(Timestamp orderDate) {
		if (orderDate != null)
			this.orderDate = orderDate;
	}

	public Timestamp getArrivalDate() {
		return arrivalDate;
	}

	public void setArrivalDate(Timestamp arrivalDate) {
		this.arrivalDate = arrivalDate;
	}

	public String getBranchName() {
		return branchName;
	}

	public void setBranchName(String branchName) {
		this.branchName = branchName;
	}

	public boolean isHomeDelivery() {
		return homeDelivery;
	}

	public void setHomeDelivery(boolean homeDelivery) {
		this.homeDelivery = homeDelivery;
	}

	public OrderStatus getOrderStatus() {
		return orderStatus;
	}

	public void setOrderStatus(OrderStatus orderStatus) {
		this.orderStatus = orderStatus;
	}

	public ArrayList<ProductInOrder> getItems() {
		return items;
	}

	public void setItems(ArrayList<ProductInOrder> items) {
		this.items = items;
	}

	public DeliveryDetails getDeliveryDetails() {
		return deliveryDetails;
	}

	public void setDeliveryDetails(DeliveryDetails deliveryDetails) {
		this.deliveryDetails = deliveryDetails;
	}

	/**
	 * Get the order as string ain the format:
	 * [id,price,color,shop,arrivalDate,orderDate,orderData,greetingCard]
	 */
	public String toString() {
		StringBuilder s = new StringBuilder();
		s.append("[ ");
		s.append(orderNumber + ", " + price + ", " + branchName + ", ");
		s.append(data + ", " + orderDate + "," + personalLetter + "]");
		return s.toString();
	}

	public OrderStatus getStatus() {
		return status;
	}

	public void setStatus(OrderStatus status) {
		if(status!=null)
		{
		this.status = status;
		}
	}
}
