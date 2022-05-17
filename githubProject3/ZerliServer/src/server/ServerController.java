package server;
import java.io.*;
import java.util.ArrayList;

import msg.Msg;
import msg.MsgType;
//import msg.MsgController;
//import msg.Type;
import ocsf.server.*;
import order.Order;
import report.Report;
import report.ReportType;
import user.User;
import user.UserType;

/**
 * Server controller, using OCSF manage the server and clients threads, send and
 * receive messages, update the active clients table on connect\disconnect,
 * update status log on change
 * 
 * @author hallel
 * @version 01
 */
public class ServerController extends AbstractServer {
	// Class variables *************************************************
	/**
	 * The database controller
	 */
	private DBController dbController;
	/**
	 * The server boundary , to update the gui
	 */
	private ServerBoundary serverBoundary;
	/**
	 * The massage controller, for parser/msg creation
	 */
	private MsgController msgController;
	/**
	 * Active clients arrayList used to save the active clients ip addresses
	 */
	private ArrayList<String> activeClients;

	// Class Constructor *************************************************
	/**
	 * Constructs an instance of the server controller.
	 * 
	 * @param port         ->the server port
	 * @param dbController -> the database controller
	 * @param sb           -> the server boundary
	 */
	public ServerController(int port, DBController dbController, ServerBoundary sb) {
		super(port);
		this.dbController = dbController;
		this.serverBoundary = sb;
		msgController = new MsgController();
		activeClients = new ArrayList<String>();
	}

	/**
	 * handle a message from the client. if recieved a msg object check the type and
	 * get the needed result from the database for the return msg
	 * 
	 * @param msg    -> the received msg
	 * @param client -> the client we got the msg from
	 */
	public void handleMessageFromClient(Object msg, ConnectionToClient client) {
		User user=null;
		Msg returnMsg = new Msg();
		msgController.mgsParser((Msg) msg);
		
		if(msgController.getType().equals(MsgType.LOGIN_REQUEST))
		{
            if(msgController.getUserName().equals("Ronen")&&msgController.getPassword().equals("Zeyan"))
            {
            	 user=checkifuserexist("ronen", "Zeyan");
            }
			if(user!=null)
			returnMsg=MsgController.createAPPROVE_LOGINMsg(user);
			else
				returnMsg=MsgController.createERRORMsg();
			sendToAllClients(returnMsg);
		}
		if(msgController.getType().equals(MsgType.GET_REPORT))
		{
		Report report=new Report(10, 2023, ReportType.QUARTERLY_REVENUE_REPORT,"Yarka israel hazafon");
		returnMsg=MsgController.creatRETURN_REPORTMsg(report);
		sendToAllClients(returnMsg);
		}
	    if(msgController.getType().equals(MsgType.UPDATE_ORDER_STATUS))
	    {
	    	if(msgController.getOrder().getOrderID()==123)
	    	returnMsg=MsgController.createCOMPLETEDMsg();
	    	else
	    	returnMsg=MsgController.createERRORMsg();	
	    	sendToAllClients(returnMsg);
	    }
		/*
		System.out.println("Message received: " + msg + " from " + client);
		Msg returnMsg = new Msg();
		// default return msg is error->if no other option was relevant
		returnMsg.type = Type.ERROR;
		if (msg instanceof Msg) {
			// parse the msg
			msgController.resetParser();
			msgController.msgParser((Msg) msg);
			// check the msg type
			switch (msgController.getType()) {
			// for order request: get the orderId from the msg and use it to get result from
			// database, if no such order was found will send null as order
			case RequestOrder:
				Order order = dbController.getOrdrFromDB(msgController.getOrderNum());
				returnMsg = msgController.createSendMsg(order,Type.OrderSended);
				break;
			// for save order: use the old order id to delete it from the database, save the
			// new order to the database
			case UpdateOrder:
				if (dbController.deleteOrder(msgController.getOldOrderNumber())) {
					if (dbController.saveOrderToDB(msgController.getOrder())) {
						returnMsg.type = Type.OrderUpdated;// return completed on success
					}
				}
				break;
			default:
				break;
			}
			
		}
		// send the response to the client
		try {
			client.sendToClient(returnMsg);
		} catch (Exception e) {
			System.out.println("ERROR - Could not send to client");
			serverBoundary.setStatus("ERROR - Could not send to client " + returnMsg.type + client);
		}
		*/
	}

	/**
	 * 
	 * @return the active clients ip list
	 */
	public ArrayList<String> getActiveClients() {
		return activeClients;
	}

	/**
	 * Called each time a new client connection is accepted. update the updateTable
	 * flag and the active clients list
	 * 
	 * @param client the connection connected to the client.
	 */
	@Override
	public void clientConnected(ConnectionToClient client) {
		activeClients.add(client.toString());
		serverBoundary.updateTable = true;
		serverBoundary.setStatus("Client connected to server from " + client);
		System.out.println("client connected to server");
	}

	/**
	 * Called each time a client disconnects. update the updateTable flag and the
	 * active clients list
	 *
	 * @param client the connection with the client.
	 */
	@Override
	public void clientDisconnected(ConnectionToClient client) {
		activeClients.remove(client.toString());
		serverBoundary.updateTable = true;
		serverBoundary.setStatus("Client disconnected from server from " + client);
	}

	/**
	 * Called when the server starts listening for connections. set the relevant
	 * status for the status log
	 */
	@Override
	protected void serverStarted() {
		System.out.println("Server listening for connections on port " + getPort());
		serverBoundary.setStatus("Server listening for connections on port " + getPort());
	}

	/**
	 * Called when the server stops listening for connections. set the relevant
	 * status for the status log
	 */
	@Override
	protected void serverStopped() {
		System.out.println("Server has stopped listening for connections.");
		serverBoundary.setStatus("Server has stopped listening for connections.");
	}
	
	public User checkifuserexist(String name,String last)
	{
		User user=new User();
		user.setFirstName("ronen");
		user.setLastName("zeyan");
		user.setConnected(true);
		user.setBranchName("yarka north israel hazafon");
		user.setUserType(UserType.CEO);
		return user;
	}

}
