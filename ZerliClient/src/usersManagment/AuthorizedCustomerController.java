package usersManagment;

import java.util.ArrayList;

import client.ClientController;
import client.MsgController;
import msg.MsgType;
import order.Order;
import order.OrderStatus;

public class AuthorizedCustomerController {
/**
 * request to view all orders of customer, send to server and return a list of orders
 * @return ArrayList <order>
 */
	private ClientController clientController;
	public ArrayList<Order> getAllOrders() {
		
		MsgController msgController = clientController.sendMsg(MsgController.createGET_ALL_ORDERSMsg());
		if(msgController.getType()==MsgType.RETURN_ALL_ORDERS)
			return msgController.getOrders();
		return null;
	}
	
	/**
	 * create the catalog boundary
	 */
	public void requestBrowseTheCatalog() {
		
	}
	/**
	 * request to cancel an existing order ,send to server a request and return true when the cancellation is approved
	 * @param order
	 * @return true - the cancellation is approved, false- when we can't cancel
	 */
	public boolean requestOrderCancellation(Order order) {
	
		if(order.getOrderStatus()==OrderStatus.APPROVED)//
		order.setOrderStatus(OrderStatus.WAITING_FOR_CANCELATION_APPROVAL);
		MsgController msgController = clientController.sendMsg(MsgController.createUPDATE_ORDER_STATUSMsg(order));
		if(msgController.getType()==MsgType.COMPLETED)
			return true;
		return false;
	}

}
