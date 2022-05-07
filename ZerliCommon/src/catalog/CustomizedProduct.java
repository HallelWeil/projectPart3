package catalog;

import java.util.ArrayList;

public class CustomizedProduct extends Product {

	private ArrayList<Item> items;
	

	public CustomizedProduct(int productID,String name) {
		super(productID);
		super.setName(name);
		items = new ArrayList<Item>();
	}

	public void addItem(Item item) {
		items.add(item);
	}

	public void removeItem(Item item) {
		items.remove(item);
	}
	public ArrayList<Item> getItemsList(){
		return items;
	}
}
