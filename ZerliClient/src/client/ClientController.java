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


public class ClientController extends AbstractClient  {
/**
 * parse_SaveMSG used to parse and save msg data of the message we received from Server in method handleMessageFromServer
 */
	public static boolean awaitResponse = false;
	//private MsgController parse_SaveMSG; 
	ClientBoundary clientBoundary;

	public ClientController(String host, int port,ClientBoundary clientBoundary) throws IOException {
		super(host, port);
		openConnection();
		parse_SaveMSG=new MsgController();
		this.clientBoundary=clientBoundary;
	}

	/**
	 * send the given msg to the server, return the result in the msgCntroller
	 * @param msg
	 * @return
	 */
	public MsgController sendMsg(Msg msg) {
		if(msg!=null)
		{
			handleMessageFromClientUI(msg);
			return parse_SaveMSG;
		}
		return null;
	}
	
	public void handleMessageFromClientUI(Object message) {
		try {
			openConnection();// in order to send more than one message
			awaitResponse = true;
			sendToServer(message);
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

	@Override
	protected void handleMessageFromServer(Object msg) {
		Msg receivedMsg;
		if (msg instanceof Msg) 
		{
			receivedMsg=new Msg();
			receivedMsg=(Msg)msg;
			parse_SaveMSG.msgParser(receivedMsg);
			awaitResponse=false; //after we parse and save data its time to wakeup the thread of client
			if (parse_SaveMSG.getType().equals(MsgType.EXIT)) { //in case server want to disconnect then terminate send exit msg for all clients 
				//in the future add reconnect options
				clientBoundary.serverDisconnect();
		}
		
		
	}
	}
	
	public void quit() {
		try {
			closeConnection();
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
	}
	
	public void display(String message) {
		System.out.println("> " + message);
	}
	
}
