package msg;

import java.io.Serializable;

public class Msg implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 4L;
	public MsgType type;
	public Serializable data;	
	
	
	public String toString() {
		return type.toString();	
	}	
}