package branchManager;

import java.io.IOException;
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
import usersManagment.BranchManagerBoundary;

public class ManagerApproveController implements IGuiController {
	private GuiObjectsFactory guiObjectsFactory = GuiObjectsFactory.getInstance();
	private BranchManagerBoundary managerBoundry = guiObjectsFactory.branchManagerBoundary;

	@FXML
	private Label errorLabel;

	@FXML
	private Label guidanceLabel;

	@FXML
	private AnchorPane orderApprovePane;

	@FXML
	private TableView<Order> managerOrderTable;

	@FXML
	private Button managerSelectedBot;

	@FXML
	private Button backToOrderSelectBot;

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

	//ObservableList<Order> ordersObs = FXCollections.observableArrayList();
	Order selectedOrder;

	public void initializeOrdersTable() {
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
		guiObjectsFactory.mainWindowController.showNewWindow(orderApprovePane);
		initializeOrdersTable();
		managerOrderTable.getItems().setAll(managerBoundry.getAllOrdersToApprove());
		guiObjectsFactory.mainWindowController.changeWindowName("Manager - Approve Order");
	}

	@Override
	public Pane getBasePane() {
		return orderApprovePane;
	}

	@Override
	public void resetController() {
		managerOrderTable.getItems().clear();
		selectedOrder = null;
		errorLabel.setVisible(false);
		guidanceLabel.setVisible(false);
	}

	@FXML
	void GoToSelectedOrderWindow(ActionEvent event) throws IOException {
		selectedOrder = managerOrderTable.getSelectionModel().getSelectedItem();
		String statusOfSelected = selectedOrder.getOrderStatus().toString();
		if (statusOfSelected == "WAITING_FOR_APPROAVL" || statusOfSelected == "WAITING_FOR_CANCELATION_APPROVAL") {
			ManagerViewProducts viewProductsController = (ManagerViewProducts) guiObjectsFactory
					.loadFxmlFile("/branchManager/productsInOrderView.fxml");
			viewProductsController.setLastController(this);
			viewProductsController.setSelectedOrder(selectedOrder);
			guiObjectsFactory.mainWindowController.showNewWindow(viewProductsController.getBasePane());
			errorLabel.setVisible(false);
			guidanceLabel.setVisible(false);
		} else {
			errorLabel.setVisible(true);
			guidanceLabel.setVisible(true);
		}
	}

	@FXML
	void BackToOrderSelectWindow(ActionEvent event) {
		resetController();
		openWindow();
	}

}
