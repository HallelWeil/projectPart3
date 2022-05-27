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
import order.OrderStatus;
import order.ProductInOrder;
import usersManagment.AuthorizedCustomerBoundary;
import usersManagment.BranchManagerBoundary;

public class OrdersHistoryController implements IGuiController {

	private GuiObjectsFactory guiObjectsFactory = GuiObjectsFactory.getInstance();
	private AuthorizedCustomerBoundary authorizedCustomerBoundary = guiObjectsFactory.authorizedCustomerBoundary;

	@FXML
	private AnchorPane orderHistoryBasePane;

	@FXML
	private TableView<Order> ordersTable;

	@FXML
	private Button selectOrderBtn;

	@FXML
	void selectOrder(ActionEvent event) {
		guiObjectsFactory.mainWindowController.changeWindowName("Products In Selected Order");
		// guiObjectsFactory.mainWindowController.showNewWindow(productInOrderPane);
	}

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

	ObservableList<Order> ordersObs = FXCollections.observableArrayList();
	Order selectedOrder;;

	public void initializeOrdersTable() {
		ordersObs.clear();
		ordersTable.getItems().clear();
		orderArrivalCol.setCellValueFactory(new PropertyValueFactory<>("arrivalDate"));
		orderDateCol.setCellValueFactory(new PropertyValueFactory<>("orderDate"));
		orderDeliveryCol.setCellValueFactory(new PropertyValueFactory<>("homeDelivery"));
		orderLetterCol.setCellValueFactory(new PropertyValueFactory<>("personalLetter"));
		orderNumCol.setCellValueFactory(new PropertyValueFactory<>("orderNumber"));
		orderPriceCol.setCellValueFactory(new PropertyValueFactory<>("price"));
		orderUserCol.setCellValueFactory(new PropertyValueFactory<>("username"));
	}

	@Override
	public void openWindow() {
		initializeOrdersTable();
		ordersObs.addAll(authorizedCustomerBoundary.getAllOrders());
		ordersTable.setItems(ordersObs);
		guiObjectsFactory.mainWindowController.changeWindowName("Customer - order history");
		guiObjectsFactory.mainWindowController.showNewWindow(orderHistoryBasePane);
	}

	@Override
	public Pane getBasePane() {
		return orderHistoryBasePane;
	}

	@Override
	public void resetController() {
		ordersObs.clear();
		ordersTable.getItems().clear();
		selectedOrder = null;
	}

}
