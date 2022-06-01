package customerService;

import javafx.event.ActionEvent;

import javafx.fxml.FXML;
import javafx.scene.control.Button;

import javafx.scene.control.Label;

import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;

import main.GuiObjectsFactory;
import main.IGuiController;
import usersManagment.CustomerServiceEmployeeBoundary;
import usersManagment.UserBoundary;

public class NewComplaint implements IGuiController {

	private GuiObjectsFactory guiObjectsFactory = GuiObjectsFactory.getInstance();
	private CustomerServiceEmployeeBoundary complaintBoundary = guiObjectsFactory.employeeServiceBoundary;

	@FXML
	private AnchorPane basePane;

	@FXML
	private Label complaintLabel;

	@FXML
	private TextField complaintText;

	@FXML
	private Button createBtn;

	@FXML
	private Label createLabel;

	@FXML
	private TextField idText;

	@FXML
	void createComplaint(ActionEvent event) {
		String nameUser = idText.getText();
		String nameEmployee = UserBoundary.CurrentUser.getUsername();
		String complaint = complaintText.getText();
		try {
			complaintBoundary.createComplaints(nameEmployee, complaint, nameUser);
			createLabel.setText("Complaint was successfully created");

		} catch (Exception e) {
			createLabel.setText("Complaint was not successfully created \n" + e.getMessage());
		}

	}

	@Override
	public Pane getBasePane() {
		return basePane;
	}

	@Override
	public void resetController() {

		idText.clear();
		createLabel.setText("");
		complaintText.clear();
	}

	@Override
	public void openWindow() {

		// move to the next window
		guiObjectsFactory.mainWindowController.showNewWindow(basePane);
		// change to the name
		guiObjectsFactory.mainWindowController.changeWindowName("created New Complaint");
	}

}
