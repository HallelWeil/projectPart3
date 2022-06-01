package customer;

import catalog.Product;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.InputMethodEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import main.GuiObjectsFactory;
import main.IGuiController;
import shop.ShopBoundary;
import userGuiManagment.AuthorizedCustomerGuiManager;
import userGuiManagment.MainWindowGuiManager;

public class ItemInCartController implements IGuiController {
	private MainWindowGuiManager mainWindowManager = MainWindowGuiManager.getInstance();
	private ShopBoundary shopBoundary = AuthorizedCustomerGuiManager.getInstance().getShopBoundary();
	private ShopWindowController shopWindowController = AuthorizedCustomerGuiManager.getInstance()
			.getShopWindowController();

	private Product product;

	private int currentAmount;

	@FXML
	private Label productNameLabel;

	@FXML
	private TextField amountTextField;

	@FXML
	private HBox baseLayer;

	@FXML
	private ImageView deleteBtn;

	@FXML
	private Button removeBtn;

	@FXML
	private Label priceLabel;

	@FXML
	void changeAmount(InputMethodEvent event) {
		try {
			int amount = Integer.valueOf(amountTextField.getText());
			if (amount < 0)
				amount = 0;
			currentAmount = amount;
			shopBoundary.chooseAmount(product, currentAmount);
		} catch (Exception e) {
			amountTextField.setText(currentAmount + "");
		}
	}

	@FXML
	void removeItem(ActionEvent event) {
		if (shopBoundary.deleteProductFromCart(product)) {
			shopWindowController.removeProductGuiObjectToCart(this.baseLayer);
		}
	}

	@Override
	public Pane getBasePane() {
		return baseLayer;
	}

	@Override
	public void resetController() {
		// TODO Auto-generated method stub

	}

	@Override
	public void openWindow() {

	}

	public void setProduct(Product product, int amount) {
		this.product = product;
		productNameLabel.setText(product.getName());
		amountTextField.setText(amount + "");
		priceLabel.setText(product.getPrice() + "");
	}

	public Product getProduct() {
		return product;
	}

	public void updateAmount(int amount) {
		if (amount < 0)
			amount = 0;
		currentAmount = currentAmount + amount;
		amountTextField.setText(currentAmount + "");
	}

}
