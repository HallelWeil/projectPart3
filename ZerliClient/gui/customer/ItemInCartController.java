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

public class ItemInCartController implements IGuiController {
	private GuiObjectsFactory guiObjectsFactory = GuiObjectsFactory.getInstance();
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
	void changeAmount(InputMethodEvent event) {
		try {
			int amount = Integer.valueOf(amountTextField.getText());
			if (amount < 0)
				amount = 0;
			currentAmount = amount;
			guiObjectsFactory.shopBoundary.chooseAmount(product, currentAmount);
		} catch (Exception e) {
			amountTextField.setText(currentAmount + "");
		}
	}

	@FXML
	void removeItem(ActionEvent event) {
		if (guiObjectsFactory.shopBoundary.deleteProductFromCart(product)) {
			guiObjectsFactory.shopWindowController.removeProductGuiObjectToCart(this.baseLayer);
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
	}

}
