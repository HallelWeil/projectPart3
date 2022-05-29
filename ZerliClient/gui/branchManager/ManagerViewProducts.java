package branchManager;

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

public class ManagerViewProducts implements IGuiController {
	private GuiObjectsFactory guiObjectsFactory = GuiObjectsFactory.getInstance();
	private BranchManagerBoundary managerBoundry = guiObjectsFactory.branchManagerBoundary;
	private Order selectedOrder = null;
	private ObservableList<ProductInOrder> productsObs = FXCollections.observableArrayList();
	private IGuiController lastController = null;
	
    @FXML
    private Button approveBot;

    @FXML
    private Button backToOrderSelectBot;

    @FXML
    private TableColumn<ProductInOrder, Integer> prodOrderNumCol;

    @FXML
    private TableColumn<ProductInOrder, Integer> productAmountCol;

    @FXML
    private TableColumn<ProductInOrder, String> productCategoryCol;

    @FXML
    private AnchorPane productInOrderPane;

    @FXML
    private TableColumn<ProductInOrder, String> productNameCol;

    @FXML
    private TableColumn<ProductInOrder, Double> productPriceCol;

    @FXML
    private TableColumn<ProductInOrder, Double> productTotalCol;

    @FXML
    private TableView<ProductInOrder> productsInOrderTable;

    @FXML
    private TextField refundField;

    @FXML
    private Label refundText;

    @FXML
    void ApproveOrder(ActionEvent event) {
    	if(selectedOrder.getOrderStatus().toString() == "WAITING_FOR_APPROAVL")
    		managerBoundry.requestApproveOrder(selectedOrder.getOrderNumber(), true);
    	if(selectedOrder.getOrderStatus().toString() == "WAITING_FOR_CANCELATION_APPROVAL")
    		managerBoundry.requestApproveCancelation(selectedOrder.getOrderNumber(), true, Double.valueOf(refundField.getText()));
    }

    @FXML
    void BackToOrderSelectWindow(ActionEvent event) {
    	guiObjectsFactory.mainWindowController.showNewWindow(lastController.getBasePane());
    	resetController();
    	lastController.openWindow();
    }

	@Override
	public Pane getBasePane() {
		return productInOrderPane;
	}

	@Override
	public void resetController() {
		refundText.setVisible(false);
		refundField.setVisible(false);
		refundText.setDisable(true);
		refundField.setDisable(true);
		selectedOrder = null;
		productsObs.clear();
	}

	@Override
	public void openWindow() {
		guiObjectsFactory.mainWindowController.showNewWindow(productInOrderPane);
    	guiObjectsFactory.mainWindowController.changeWindowName("Manager - Products In Selected Order");
    	initializeProductsTable();
    	productsObs.addAll(managerBoundry.getAllProductsInOrder(selectedOrder.getOrderNumber()));
    	if(selectedOrder.getOrderStatus().toString() == "WAITING_FOR_APPROAVL")
    		approveBot.setText("Approve Order");
    	if(selectedOrder.getOrderStatus().toString() == "WAITING_FOR_CANCELATION_APPROVAL") {
    		approveBot.setText("Approve Cancellation");
    		refundText.setVisible(true);
    		refundField.setVisible(true);
    		refundText.setDisable(false);
    		refundField.setDisable(false);
    	}
	}
	
	public void setSelectedOrder(Order order) {
		this.selectedOrder = order;
	}
	
	public void setLastController(IGuiController controller) {
		this.lastController = controller;
	}
	
    public void initializeProductsTable() {
    	productsObs.clear();
    	productCategoryCol.setCellValueFactory(new PropertyValueFactory<>("category"));
    	productNameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
    	prodOrderNumCol.setCellValueFactory(new PropertyValueFactory<>("orderNumber"));
    	productPriceCol.setCellValueFactory(new PropertyValueFactory<>("price"));
    	productAmountCol.setCellValueFactory(new PropertyValueFactory<>("amount"));
    	productTotalCol.setCellValueFactory(new PropertyValueFactory<>("total"));
    }
    
    

}
