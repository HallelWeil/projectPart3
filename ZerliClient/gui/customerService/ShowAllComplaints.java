package customerService;

import java.sql.Timestamp;

import common.Status;
import complaint.Complaint;
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
import usersManagment.CustomerServiceEmployeeBoundary;

public class ShowAllComplaints implements IGuiController {
	private GuiObjectsFactory guiObjectsFactory = GuiObjectsFactory.getInstance();
	private CustomerServiceEmployeeBoundary complaintBoundary = guiObjectsFactory.employeeServiceBoundary;
	
    @FXML
    private AnchorPane showAllComplaintPane;
	
    @FXML
    private TableView<Complaint> ComplaintTable;
    
    //@FXML
   // private Button updateBot;

    @FXML
    private TableColumn<Complaint, Integer> complaintsNumberCol;
    
    @FXML
    private TableColumn<Complaint, Integer> responsibleEmployeeUserNameCol;

    @FXML
    private TableColumn<Complaint, String> complaintCol;

    @FXML
    private TableColumn<Complaint, Status> statusCol;

    @FXML
    private TableColumn<Complaint, String> customerUserNameCol;

    @FXML
    private TableColumn<Complaint, Timestamp> creationTimeCol;
    

    ObservableList<Complaint> complaintObs = FXCollections.observableArrayList();
    UpdateComplaint updateComplaint = new UpdateComplaint();
    Complaint selectedComplaint;
;
    
    public void initializeComplaintsTable() {
    	complaintObs.clear();
    	ComplaintTable.getItems().clear();
    	complaintsNumberCol.setCellValueFactory(new PropertyValueFactory<>("number"));
    	responsibleEmployeeUserNameCol.setCellValueFactory(new PropertyValueFactory<>("responsible Employee"));
    	complaintCol.setCellValueFactory(new PropertyValueFactory<>("complaint"));
    	statusCol.setCellValueFactory(new PropertyValueFactory<>("status"));
    	customerUserNameCol.setCellValueFactory(new PropertyValueFactory<>("customer name"));
    	creationTimeCol.setCellValueFactory(new PropertyValueFactory<>("complaint create date"));
    }
    
    @Override
    public void openWindow() {
    	initializeComplaintsTable();
    	complaintObs.addAll(complaintBoundary.getMyComplaints());
    	ComplaintTable.setItems(complaintObs);
    	guiObjectsFactory.mainWindowController.showNewWindow(showAllComplaintPane);
    }

	@Override
	public Pane getBasePane() {
		return showAllComplaintPane;
	}

	@Override
	public void resetController() {
		complaintObs.clear();
		ComplaintTable.getItems().clear();
	}


    @FXML
    void GoToUpdateComplaintWindow(ActionEvent event) {
    	guiObjectsFactory.mainWindowController.changeWindowName("update complaint");
    	updateComplaint.openWindow();
    }
}