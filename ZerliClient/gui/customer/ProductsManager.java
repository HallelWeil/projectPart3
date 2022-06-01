package customer;

import java.io.IOException;
import java.util.ArrayList;

import catalog.Product;
import javafx.scene.control.ListView;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import main.GuiObjectsFactory;

public class ProductsManager {

	private static final String productFxmlPath = "/customer/ProductInCatalog.fxml";
	private static final String cartItemPath = "/customer/ItemInCart.fxml";
	private static final String CSItemPath = "/customer/ItemInCustomozedProduct.fxml";
	private GuiObjectsFactory guiObjectsFactory = GuiObjectsFactory.getInstance();

	/**
	 * Fille the list view with gui object of the Products
	 * 
	 * @param list
	 * @param productLsi
	 * @throws IOException
	 */
	public void fillProductList(VBox basePane, ArrayList<Product> productsList) {
		// empty the basePane
		basePane.getChildren().removeAll(basePane.getChildren());
		// create and add all the products
		Pane temp;
		for (Product product : productsList) {
			temp = createNewProduct(product);
			basePane.getChildren().add(temp);
		}
	}

	private Pane createNewProduct(Product product) {
		ProductGuiController newController;
		newController = (ProductGuiController) guiObjectsFactory.loadFxmlFile(productFxmlPath);
		newController.setProduct(product);
		return newController.getBasePane();
	}

	public ItemInCartController createNewCartItem(Product product, int amount) {
		ItemInCartController newController;
		newController = (ItemInCartController) guiObjectsFactory.loadFxmlFile(cartItemPath);
		newController.setProduct(product, amount);
		return newController;
	}

	public ItemInCustomizedProductController createNewCSItem(Product product) {
		ItemInCustomizedProductController newController;
		newController = (ItemInCustomizedProductController) guiObjectsFactory.loadFxmlFile(CSItemPath);
		newController.setProduct(product);
		return newController;
	}

}
