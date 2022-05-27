package client;

import msg.Msg;

public class ClientController {
	
	public static MsgController SaveParsedMSG;
	
	/**
	 * send the given msg to the server, return the result in the msgCntroller
	 * 
	 * @param msg
	 * @return
	 */
	public MsgController sendMsg(Msg msg) {
		SaveParsedMSG=new MsgController(); //reset saveParsedMSG (msgType=NONE)
		if (msg != null) {
			ClientBoundary.client.handleMessageFromClientUI(msg);
		}
		return SaveParsedMSG; //return the data (in case msg=null saveParsedMSG will return saveParsedMSG.getType=NONE)
	}

}
