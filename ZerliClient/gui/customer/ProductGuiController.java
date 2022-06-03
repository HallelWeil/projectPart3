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
import javafx.scene.text.Text;
import javafx.scene.layout.Pane;
import main.GuiObjectsFactory;
import main.IGuiController;
import shop.ShopBoundary;
import userGuiManagment.AuthorizedCustomerGuiManager;

public class ProductGuiController implements IGuiController {

	private ShopBoundary shopBoundary = AuthorizedCustomerGuiManager.getInstance().getShopBoundary();
	private ShopWindowController shopWindowController = AuthorizedCustomerGuiManager.getInstance()
			.getShopWindowController();
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
	private Label priceLabel;

	@FXML
	private VBox pricePane;

	@FXML
	private VBox textPane;

	@FXML
	private AnchorPane btnsPane;

	@FXML
	private Text oldPriceText;

	private Product product;

	@FXML
	void addToProduct(ActionEvent event) {
		shopWindowController.addProductToCustomizedProduct(product);
	}

	@FXML
	void addToCart(ActionEvent event) {
		try {
			int num = Integer.valueOf(amount.getText());
			if (num < 0)
				num = 1;
			if (shopBoundary.addToCart(product, num))// add to cart
			{
				ItemInCartController controller = AuthorizedCustomerGuiManager.getInstance().getProductManager()
						.createNewCartItem(product, num);
				shopWindowController.addProductGuiObjectToCart(controller.getBasePane(), controller);
			} else {
				shopWindowController.updateAmount(product.getProductID());
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
		if (product.getOldPrice() == 0) {
			oldPriceText.setText("");
		} else {
			oldPriceText.setText("" + product.getOldPrice());
		}
		amount.setText("1");
		if (product.getCategory().equals("singleItems")) {
			btnsPane.getChildren().remove(addToCartBtn);
		}
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
