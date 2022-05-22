package customer;

import java.io.IOException;
import java.util.ArrayList;

import catalog.Product;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import main.GuiObjectsFactory;
import main.IGuiController;
import shop.ShopBoundary;

public class ShopWindowController implements IGuiController {

	private GuiObjectsFactory guiObjectsFactory = GuiObjectsFactory.getInstance();
	private ShopBoundary shopBoundary = guiObjectsFactory.shopBoundary;

	private boolean isOpen = false;

	@FXML
	private Button addCPtoCart;

	@FXML
	private VBox anniversaryFlowersBase;

	@FXML
	private HBox basePane;

	@FXML
	private VBox birthdayFlowerBase;

	@FXML
	private VBox cartItemsList;

	@FXML
	private Label cartLabel;

	@FXML
	private VBox cartPane;

	@FXML
	private VBox cartPane1;

	@FXML
	private TabPane catalogTabPane;

	@FXML
	private VBox congratulationFlowersBase;

	@FXML
	private TextField costumizedItemName;

	@FXML
	private VBox costumizedProductItemList;

	@FXML
	private ChoiceBox<?> costumizedProductsChooseList;

	@FXML
	private VBox newBabyFlowersBase;

	@FXML
	private Button newCostumizedProduct;

	@FXML
	private Button placeOrderBtn;

	@FXML
	private Button saveChangeBtn;

	@FXML
	private VBox singleItemsBase;

	@FXML
	private VBox weddingFlowersbase;

	// shop tabs
	@FXML
	private Tab congratulationFlowersTab;

	@FXML
	private Tab singleItemsTab;

	@FXML
	private Tab weddingFlowersTab;

	@FXML
	private Tab birthdayFlowersTab;

	@FXML
	private Tab babyFlowersTab;

	@FXML
	private Tab AnniversaryFlowersTab;
	// cart tabs
	@FXML
	private Tab cartTab;

	@FXML
	private Tab CostumizedProductTab;

	@FXML
	void PalceOrder(ActionEvent event) {

	}

	@FXML
	void addCPToCart(ActionEvent event) {

	}

	@FXML
	void saveChnages(ActionEvent event) {

	}

	@FXML
	void selectCartTab(Event event) {

	}

	@FXML
	void selectCategoryTab(Event event) {
		if (!isOpen)
			return;
		ArrayList<Product> products;
		switch (((Tab) event.getSource()).getId()) {
		case "congratulationFlowersTab":
			products = shopBoundary.chooseCategory("congratulationFlowers");
			guiObjectsFactory.productManager.fillProductList(congratulationFlowersBase, products);
			break;
		case "singleItemsTab":
			// products = shopBoundary.chooseCategory("congratulationFlowers");
			// guiObjectsFactory.productInCartManager.fillProductList(congratulationFlowersBase,
			// products);
			break;
		case "weddingFlowersTab":
			products = shopBoundary.chooseCategory("weddingFlowers");
			guiObjectsFactory.productManager.fillProductList(weddingFlowersbase, products);
			break;
		case "birthdayFlowersTab":
			products = shopBoundary.chooseCategory("birthdayFlowers");
			guiObjectsFactory.productManager.fillProductList(birthdayFlowerBase, products);
			break;
		case "babyFlowersTab":
			products = shopBoundary.chooseCategory("babyFlowers");
			guiObjectsFactory.productManager.fillProductList(newBabyFlowersBase, products);
			break;
		case "AnniversaryFlowersTab":
			products = shopBoundary.chooseCategory("AnniversaryFlowers");
			guiObjectsFactory.productManager.fillProductList(anniversaryFlowersBase, products);
			break;

		default:

			break;

		}
	}

	void selectCongratulationFlowers(Event event) throws IOException {
		String name = ((Tab) event.getSource()).getId();
		System.out.println("got here " + name);
		// temp for testing
		ArrayList<Product> products = new ArrayList<Product>();
		for (int i = 0; i < 10; i++) {
			Product p = new Product(i);
			p.setColors("Blue");
			p.setDescription("A very nice blue flower" + i);
			p.setPrice(22.5 + i);
			p.setName("Flower #" + i);
			products.add(p);
		}
	}

	@Override
	public Pane getBasePane() {
		return null;
	}

	@Override
	public void resetController() {
		// TODO Auto-generated method stub

	}

	@Override
	public void openWindow() {
		isOpen = true;
		// move to the next window
		guiObjectsFactory.mainWindowController.showNewWindow(basePane);
		// change to the name
		guiObjectsFactory.mainWindowController.changeWindowName("Shop");
	}

	public VBox getCartPane() {
		return cartPane;

	}

	public void addProductGuiObjectToCart(Pane p) {
		cartItemsList.getChildren().add(p);
	}

	public void removeProductGuiObjectToCart(Pane p) {
		if (cartItemsList.getChildren().contains(p))
			cartItemsList.getChildren().remove(p);
	}

}
