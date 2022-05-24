package customer;

import catalog.Product;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.layout.Pane;
import main.GuiObjectsFactory;
import main.IGuiController;

public class ProductGuiController implements IGuiController {

	@FXML
	private Label FlowerDescription;

	@FXML
	private HBox addPane;

	@FXML
	private Button addToCartBtn;

	@FXML
	private TextField amount;

	@FXML
	private HBox basePane;

	@FXML
	private Label flowerNAme;

	@FXML
	private AnchorPane imagePane;

	@FXML
	private Label oldPriceLabel;

	@FXML
	private Label priceLabel;

	@FXML
	private VBox pricePane;

	@FXML
	private VBox textPane;

	private Product product;

	@FXML
	void addToProduct(ActionEvent event) {
		GuiObjectsFactory.getInstance().shopWindowController.addProductToCustomizedProduct(product);
	}

	@FXML
	void addToCart(ActionEvent event) {
		try {
			int num = Integer.valueOf(amount.getText());
			if (num < 0)
				num = 1;
			if (GuiObjectsFactory.getInstance().shopBoundary.addToCart(product, num))// add to cart
			{
				ItemInCartController controller = GuiObjectsFactory.getInstance().productManager
						.createNewCartItem(product, num);
				GuiObjectsFactory.getInstance().shopWindowController.addProductGuiObjectToCart(controller.getBasePane(),
						controller);
			} else {
				GuiObjectsFactory.getInstance().shopWindowController.updateAmount(num, product.getProductID());
			}
		} catch (Exception e) {
			e.printStackTrace();
			amount.setText("1");
		}

	}

	public void setProduct(Product product) {
		// for future update
		// imagePane,oldPriceLabel
		this.product = product;
		flowerNAme.setText(product.getName() + "(" + product.getColors() + ")");
		FlowerDescription.setText(product.getDescription());
		priceLabel.setText(product.getPrice() + "");
		oldPriceLabel.setText("");
		amount.setText("1");
	}

	@Override
	public Pane getBasePane() {
		return basePane;
	}

	@Override
	public void resetController() {
		// TODO Auto-generated method stub

	}

	@Override
	public void openWindow() {
		// TODO Auto-generated method stub
	}

	public Product getProduct() {
		return product;
	}

}
