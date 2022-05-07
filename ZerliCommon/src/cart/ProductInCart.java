package cart;

import java.io.Serializable;
import java.util.Objects;

import catalog.Product;

public class ProductInCart implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Product product;
	private int amount;

	public ProductInCart(Product product, int amount) {
		super();
		this.product = product;
		this.amount = amount;
	}

	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}

	public Product getProduct() {
		return product;
	}

	public void addAmount(int amount) {
		this.amount += amount;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ProductInCart other = (ProductInCart) obj;
		return product.equals(other.product);
	}

}
