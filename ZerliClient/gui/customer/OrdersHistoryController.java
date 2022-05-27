package customer;

import java.sql.Timestamp;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import main.GuiObjectsFactory;
import main.IGuiController;
import order.Order;
import order.ProductInOrder;
import usersManagment.AuthorizedCustomerBoundary;
import usersManagment.BranchManagerBoundary;

public class OrdersHistoryController implements IGuiController {

	private GuiObjectsFactory guiObjectsFactory = GuiObjectsFactory.getInstance();
	private AuthorizedCustomerBoundary authorizedCustomerBoundary = guiObjectsFactory.authorizedCustomerBoundary;

	@FXML
	private AnchorPane orderApprovePane;

	@FXML
	private TableView<Order> managerOrderTable;

	@FXML
	private Button managerSelectedBot;

	@FXML
	private Button approveBot;

	@FXML
	private Button backToOrderSelectBot;

	@FXML
	private TextField refundField;

	@FXML
	private Label refundText;

	@FXML
	private AnchorPane productInOrderPane;

	@FXML
	private TableView<ProductInOrder> productsInOrderTable;

	@FXML
	private TableColumn<Order, Timestamp> orderArrivalCol;

	@FXML
	private TableColumn<Order, Timestamp> orderDateCol;

	@FXML
	private TableColumn<Order, Boolean> orderDeliveryCol;

	@FXML
	private TableColumn<Order, String> orderLetterCol;

	@FXML
	private TableColumn<Order, Integer> orderNumCol;

	@FXML
	private TableColumn<Order, Double> orderPriceCol;

	@FXML
	private TableColumn<Order, String> orderUserCol;

	@FXML
	private TableColumn<ProductInOrder, Integer> prodOrderNumCol;

	@FXML
	private TableColumn<ProductInOrder, Integer> productAmountCol;

	@FXML
	private TableColumn<ProductInOrder, String> productCategoryCol;

	@FXML
	private TableColumn<ProductInOrder, String> productNameCol;

	@FXML
	private TableColumn<ProductInOrder, Double> productPriceCol;

	@FXML
	private TableColumn<ProductInOrder, Double> productTotalCol;

	ObservableList<Order> ordersObs = FXCollections.observableArrayList();
	ObservableList<ProductInOrder> productsObs = FXCollections.observableArrayList();
	Order selectedOrder;;

	public void initializeOrdersTable() {
		ordersObs.clear();
		managerOrderTable.getItems().clear();
		orderArrivalCol.setCellValueFactory(new PropertyValueFactory<>("arrivalDate"));
		orderDateCol.setCellValueFactory(new PropertyValueFactory<>("orderDate"));
		orderDeliveryCol.setCellValueFactory(new PropertyValueFactory<>("homeDelivery"));
		orderLetterCol.setCellValueFactory(new PropertyValueFactory<>("personalLetter"));
		orderNumCol.setCellValueFactory(new PropertyValueFactory<>("orderNumber"));
		orderPriceCol.setCellValueFactory(new PropertyValueFactory<>("price"));
		orderUserCol.setCellValueFactory(new PropertyValueFactory<>("username"));
	}

	public void initializeProductsTable() {
		productsObs.clear();
		productsInOrderTable.getItems().clear();
		productCategoryCol.setCellValueFactory(new PropertyValueFactory<>("category"));
		productNameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
		prodOrderNumCol.setCellValueFactory(new PropertyValueFactory<>("orderNumber"));
		productPriceCol.setCellValueFactory(new PropertyValueFactory<>("price"));
		productAmountCol.setCellValueFactory(new PropertyValueFactory<>("amount"));
	}

	@Override
	public void openWindow() {
		initializeOrdersTable();
		ordersObs.addAll(authorizedCustomerBoundary.getAllOrders());
		managerOrderTable.setItems(ordersObs);
		guiObjectsFactory.mainWindowController.changeWindowName("Customer - order history");
	}

	@Override
	public Pane getBasePane() {
		return orderApprovePane;
	}

	@Override
	public void resetController() {
		ordersObs.clear();
		managerOrderTable.getItems().clear();
		productsInOrderTable.getItems().clear();
		productsObs.clear();
		selectedOrder = null;
		refundText.setVisible(false);
		refundField.setVisible(false);
	}

	@FXML
	void GoToSelectedOrderWindow(ActionEvent event) {
		guiObjectsFactory.mainWindowController.changeWindowName("Products In Selected Order");
		initializeProductsTable();
		selectedOrder = managerOrderTable.getSelectionModel().getSelectedItem();
		productsObs.addAll(authorizedCustomerBoundary.getAllProductsInOrder(selectedOrder.getOrderNumber()));
		if (selectedOrder.getOrderStatus().toString() == "WAITING_FOR_APPROAVL")
			approveBot.setText("Approve Order");
		if (selectedOrder.getOrderStatus().toString() == "WAITING_FOR_CANCELATION_APPROVAL") {
			approveBot.setText("Approve Cancellation");
			refundText.setVisible(true);
			refundField.setVisible(true);
		}
		guiObjectsFactory.mainWindowController.showNewWindow(productInOrderPane);
	}

	@FXML
	void ApproveOrder(ActionEvent event) {
		//if (selectedOrder.getOrderStatus().toString() == "WAITING_FOR_APPROAVL")
			//managerBoundry.requestApproveOrder(selectedOrder.getOrderNumber(), true);
		//if (selectedOrder.getOrderStatus().toString() == "WAITING_FOR_CANCELATION_APPROVAL")
			//managerBoundry.requestApproveCancelation(selectedOrder.getOrderNumber(), true,
				//	Double.valueOf(refundField.getText()));
	}

	@FXML
	void BackToOrderSelectWindow(ActionEvent event) {
		resetController();
		openWindow();
	}

}
