package server;

import java.util.HashMap;

import database.DBController;
import msg.Msg;
import ocsf.server.*;
import order.Order;

/**
 * Server controller, using OCSF manage the server and clients threads, send and
 * receive messages, update the active clients table on connect\disconnect,
 * update status log on change
 * 
 * @author hallel
 * @version 02
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
	private ServerMsgController msgController;

	/**
	 * the server ip address
	 */
	private String hostName;

	/**
	 * 
	 */
	private HashMap<ConnectionToClient, ClientTask> clientsTasks;

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
		msgController = new ServerMsgController();
		hostName = "Server";
		clientsTasks = new HashMap<ConnectionToClient, ClientTask>();
	}

	/**
	 * handle a message from the client. if received a msg object check the type and
	 * get the needed result from the database for the return msg
	 * 
	 * @param msg    -> the received msg
	 * @param client -> the client we got the msg from
	 */
	public void handleMessageFromClient(Object msg, ConnectionToClient client) {
		Msg returnMsg = new Msg();
		if(clientsTasks.containsKey(client)) {
			ClientTask task = clientsTasks.get(client);
			returnMsg = task.handleTask(msg);
		}
		else {
			//todo handle the error
		}
		try {
			client.sendToClient(returnMsg);
		} catch (Exception e) {
			System.out.println("ERROR - Could not send to client");
			serverBoundary.setStatus("ERROR - Could not send to client " + returnMsg.type + client);
		}
	}

	/**
	 * Called each time a new client connection is accepted. update the updateTable
	 * flag and the active clients list
	 * 
	 * @param client the connection connected to the client.
	 */
	@Override
	public void clientConnected(ConnectionToClient client) {
		String tempHost = hostName;
		if (client.toString().equals("127.0.0.1 (127.0.0.1)"))
			tempHost = "local host";
		serverBoundary.updateClientsTable(client.toString(), "Active", tempHost, client.getName());
		serverBoundary.setStatus("Client connected to server from " + client);
		// add the clients to the client tasks map
		ClientTask newClientTask = new ClientTask(client);
		if (!clientsTasks.containsKey(client)) {
			clientsTasks.put(client, newClientTask);
		} else {
			clientsTasks.remove(client);
			clientsTasks.put(client, newClientTask);
		}
	}

	/**
	 * Called each time a client disconnects. update the updateTable flag and the
	 * active clients list
	 *
	 * @param client the connection with the client.
	 */
	@Override
	public void clientDisconnected(ConnectionToClient client) {
		serverBoundary.updateClientsTable(client.toString(), "NotActive", "---", client.getName());
		serverBoundary.setStatus("Client disconnected from server from " + client);
		clientsTasks.remove(client);
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

}
