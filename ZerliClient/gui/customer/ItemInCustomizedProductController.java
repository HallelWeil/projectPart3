package customer;

import catalog.CustomizedProduct;
import catalog.Product;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import main.GuiObjectsFactory;
import main.IGuiController;

public class ItemInCustomizedProductController implements IGuiController {

	@FXML
	private HBox baseLayer;

	@FXML
	private ImageView deleteBtn;

	@FXML
	private Label productNameLabel;

	@FXML
	private Button removeBtn;

	private Product product;

	@FXML
	void removeItem(ActionEvent event) {
		GuiObjectsFactory.getInstance().shopWindowController.removeProductFromCustomizedProduct(product, baseLayer);
	}

	public void setProduct(Product product) {
		productNameLabel.setText(product.getName());
		this.product = product;
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
		// TODO Auto-generated method stub

	}

	public Product getProduct() {
		return product;
	}

}
