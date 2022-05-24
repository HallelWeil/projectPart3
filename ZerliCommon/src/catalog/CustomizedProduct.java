package catalog;

import java.util.ArrayList;

public class CustomizedProduct extends Product {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * list of the items in our product
	 */
	private ArrayList<Product> items;
	/**
	 * the price range low limit, the to limit is the Product's price
	 */
	private double priceRangeLowLimit;

	public CustomizedProduct(int productID, String name) {
		super(productID);
		super.setName(name);
		items = new ArrayList<Product>();
	}

	public void addItemToProduct(Product product) {
		if (!items.contains(product)) {
			items.add(product);
		}
	}

	public void choosePrice(double highPrice, double lowPrice) {
		this.setPrice(highPrice);
		this.priceRangeLowLimit = lowPrice;
	}

	public ArrayList<Product> getItems() {
		return items;
	}

	public double getPriceRangeLowLimit() {
		return priceRangeLowLimit;
	}

	public double getPriceRangeHighLimit() {
		return this.getPrice();
	}

	@Override
	public String getName() {
		return super.getName() + "(Customized)";
	}

}
