package client;

import java.io.IOException;
import msg.Msg;
import order.Order;
import msg.*;

/**
 * boundary of the client , make the zerliClientController,class contains 3
 * methods and received a requests from Client UI and send to
 * zerliClientController instance to handling msg and send to server
 * 
 * @author Ronen
 *
 */
public class ClientBoundary {

	// Instance variables **********************************************

	private Msg msg;
	//private ClientController client;
<<<<<<< HEAD
	public static ClientController client;
=======
	public static ClientConnector client;
>>>>>>> origin/Ronen

	// Constructors ****************************************************
	/**
	 * this constructor construct a ClientBoundary
	 */
	public ClientBoundary() {
		msg = new Msg();

	}

	/**
	 * connect to the server
	 * 
	 * @param host The host to connect to
	 * @param port The port to connect on.
	 * @return true if connected to server
	 */
	public boolean connect(String host, int port) {
		try {
			//client = new ClientController(host, port,this);
<<<<<<< HEAD
			client =ClientController.getInstance_WithArguments(host, port, this);
=======
			client = new ClientConnector(host, port,this);
>>>>>>> origin/Ronen
			System.out.println("connected to server");
			return true;
		} catch (IOException e) {
			System.out.println("Error cant connect to server");
			return false;
		}
	}
<<<<<<< HEAD
=======
/*
	// Instance methods ************************************************
	/**
	 * this method get a order from Client UI and prepare a requestOrder message by
	 * MsgController Class and send the msg request to handlingMsgFromClientUI
	 * method to send to server and waited to save the Details in
	 * zerliClientController.CreateMsg and return the order
	 * 
	 * @param OrderID the ID(Number) of order
	 * @return return the order that it number is orderID
	 
	public Order RequestOrder(int Order) {
		msg = MsgController.createGetOrderMsg(Order);
		client.handleMessageFromClientUI((Object) msg);
		if (zerliClientController.CreateMsg.getType().equals("Send order"))
			return zerliClientController.CreateMsg.getOrder();
		else {
			return null;
		}
	}
    
	/**
	 * this method get a old order and new order (updated order) and prepare a
	 * requestOrder message by MsgController Class and send the msg request to
	 * handlingMsgFromClientUI method to send to server and waited to save the
	 * Details in zerliClientController.CreateMsg and return the
	 * status(success/failed to update order).
	 * 
	 * @param old the old order
	 * @param New the new order
	 */
	/*
	public boolean saveOrder(Order old, Order New) {
		msg = MsgController.createSaveMsg(old, New);
		client.handleMessageFromClientUI(msg);
		if (zerliClientController.CreateMsg.getType().equals("completed")) {
			return true;
		} else {
			return false;
		}

	}
>>>>>>> origin/Ronen

	/**
	 * this method exit/stop the system when server disconnected
	 */

	public void serverDisconnect() {
		System.out.println("Server disconnected");
		System.exit(0);
	}

	/**
	 * this method our client used to stop/disconnect/terminate.send a msg to
	 * msgController and prepare ExitMsg and send it to server by
	 * handlemsgfromclientUI
	 */

	public void quit() {
	    msg = MsgController.createEXITMsg();
		client.handleMessageFromClientUI(msg);
		client.quit();
	}
	

}
