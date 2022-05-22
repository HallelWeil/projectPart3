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

public class ProductInCartManager {

	private static final String productFxmlPath = "/customer/ProductInCatalog.fxml";
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
		try {
		ProductGuiController newController;
		newController = (ProductGuiController) guiObjectsFactory.loadFxmlFile(productFxmlPath);
		System.out.println("file loaded");
		newController.setProduct(product);
		return newController.getBasePane();
		}catch(IOException e) {
			e.printStackTrace();
			return null;
		}
		
	}
}
