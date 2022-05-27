package client;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;

import msg.Msg;
import msg.MsgType;
import ocsf.client.AbstractClient;
import report.*;
import survey.*;
import user.*;

public class ClientController2 extends AbstractClient {
	/**
	 * parse_SaveMSG used to parse and save msg data of the message we received from
	 * Server in method handleMessageFromServer
	 */
	public static boolean awaitResponse = false;
	private MsgController SaveParsedMSG;
	ClientBoundary clientBoundary;

	public ClientController2(String host, int port, ClientBoundary clientBoundary) throws IOException {
		super(host, port);
		openConnection();
		this.clientBoundary = clientBoundary;
	}

	/**
	 * send the given msg to the server, return the result in the msgCntroller
	 * 
	 * @param msg
	 * @return
	 */
	public MsgController sendMsg(Msg msg) {
		if (msg != null) {
			handleMessageFromClientUI(msg);
			return SaveParsedMSG;
		}
		return null;
	}

	/**
	 * this method handle a msg from the GUI and send the object to the server
	 *  by sendToServer method.
	 *   thread of client that used this method enter in sleep status
	 *    until someone change awaitResponse to false (handleMsgFromServer change awaitResponse) 
	 *  
	 * @param message contains the data and information we want to done by server 
	 */
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
			SaveParsedMSG = parseMSG;
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
