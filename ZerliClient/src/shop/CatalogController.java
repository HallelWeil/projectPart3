package shop;

import java.util.ArrayList;
import java.util.Locale.Category;

import catalog.CustomizedProduct;
import catalog.Item;

public class CatalogController {


	private ArrayList<CustomizedProduct> customizedProducts = new ArrayList<CustomizedProduct>();
	
	/**
	 * get the next catalog page from the server
	 */
	public void changePage(Category category, int newPage) {
		//will have clientController method
	}
	/**
	 * change the category, get the category page(default should be 1)
	 * @param category
	 * @param newPage
	 */
	public void chooseCategory(Category category, int newPage) {
		//will have clientController method
	}
	
	
	/**
	 * crate new customized product
	 * @param name
	 */
	public void createNewProduct(String name) {
		
	}
	/**
	 * add item to a customized product
	 * @param item
	 */
	public void addItemToProduct(Item item,String Productname) {
		
	}
	
	/**
	 * delete item from a customized product
	 * @param item
	 */
	public void deleteItemFromProduct(Item item,String Productname) {
		
	}
	public void updateCatalog() {
		
	}
}
