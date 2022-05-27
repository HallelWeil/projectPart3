package catalog;

import java.io.Serializable;

public class Item implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private int itemID;
	private String itemName;
	private String description;
	
	public Item(int itemID) {
		super();
		this.itemID = itemID;
	}
}
