package client;

import java.io.IOException;

import msg.Msg;
import msg.MsgType;
import ocsf.client.AbstractClient;

public class ClientConnector extends AbstractClient {
	
	public static boolean awaitResponse = false;
	ClientBoundary clientBoundary;
	
	public ClientConnector(String host, int port, ClientBoundary clientBoundary) throws IOException {
		super(host, port);
		openConnection();
		this.clientBoundary = clientBoundary;
	}
	
	public void handleMessageFromClientUI(Object message) {
		try {
			openConnection();// in order to send more than one message
			awaitResponse = true;
			sendToServer(message); //this is the only way to communicate with the server 
			while (awaitResponse) {
				try {
					Thread.sleep(100);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		} catch (IOException e) {  
			e.printStackTrace();
			display("Could not send message to server: Terminating client." + e);
			quit();
		}
	}
	
	/**
	 * this method overrided from abstractClient class and this method
	 *  handled a msg sent from server to our client. after we received we parse the msg by 
	 * msgParser Method (implemented in MsgController class) and saved the parsed data in
	 * msgController of the client then changed awaitResponse to let client send thread wakeup 
	 * 
	 *   @param msg contains the data we asked from server
	 */
		@Override
		protected void handleMessageFromServer(Object msg) {
			Msg receivedMsg;
			MsgController parseMSG;
			if (msg instanceof Msg) {
				receivedMsg = new Msg();
				receivedMsg = (Msg) msg;
				parseMSG = new MsgController();
				if (!(parseMSG.mgsParser(receivedMsg))) // in case returned failed mean no type founded we returned msg
														// included ERROR
				{
					receivedMsg.type = MsgType.ERROR; // change receivedmsg unknown type to ERROR type
					parseMSG.mgsParser(receivedMsg);
				}
				ClientController.SaveParsedMSG = parseMSG;
				awaitResponse = false; // after we parse and save data its time to wakeup the thread of client
				if (parseMSG.getType().equals(MsgType.EXIT)) { // in case server want to disconnect then terminate send exit
																// msg for all clients
					// in the future add reconnect options
					clientBoundary.serverDisconnect();
				}

			}
		}
	
	/**
	 * this method close the connection with server.throw IOException if closeConnection() with server didn't succeed 
	 */
		public void quit() {
			try {
				closeConnection();
			} catch (IOException e) {
				System.out.println(e.getMessage());
			}
		}
	 /**
	  * this method display a msg String to the console 
	  * @param message contains the string we want to display in console 
	  */
		public void display(String message) {
			System.out.println("> " + message);
		}

	}


