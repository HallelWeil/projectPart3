package customerService;



import javafx.event.ActionEvent;

import javafx.fxml.FXML;
import javafx.scene.control.Button;

import javafx.scene.control.Label;

import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;

import main.GuiObjectsFactory;
import main.IGuiController;
import usersManagment.CustomerServiceEmployeeBoundary;

public class NewComplaint implements IGuiController {

		private GuiObjectsFactory guiObjectsFactory = GuiObjectsFactory.getInstance();
		private CustomerServiceEmployeeBoundary complaintBoundary = guiObjectsFactory.employeeServiceBoundary;


		@FXML
		private HBox basePane;
		
		@FXML
		private Label userNameLabel;

		@FXML
		private Label employUserNameLabel;

		@FXML
		private Label complaintLabel;
		
		@FXML
		private Label erorrLabel;
		
		@FXML
		private TextField userNameText;


		@FXML
		private TextField complaintText;
		
		@FXML
		private TextField employUserNameText;
		

		@FXML
		private Button createBtn;
		
		@FXML
		void createComplaint(ActionEvent event)
		{
			String nameUser = userNameText.getText();
			String nameEmployee = employUserNameText.getText();
			String complaint = complaintText.getText();
			try {
				complaintBoundary.createComplaints(nameEmployee, complaint, nameUser);
				goToStartWindow();
				
			} catch (Exception e) {
				e.printStackTrace();
			}	
		
		}
		
		private void setError(String errorMsg) {
			erorrLabel.setText(errorMsg);
		}

		@Override
		public Pane getBasePane() {
			return basePane;
		}

		@Override
		public void resetController() {
		
			userNameText.clear();
			employUserNameText.clear();
			complaintText.clear();
		}

		@Override
		public void openWindow() {
			
			// move to the next window
			guiObjectsFactory.mainWindowController.showNewWindow(basePane);
			// change to the name
			guiObjectsFactory.mainWindowController.changeWindowName("created New Complaint");
			//empty the error string
			setError("");
		}
		
		/**
		 * go to the user's start window
		 */
		private void goToStartWindow() {
			// add the global buttons
			guiObjectsFactory.btnMenuManager.setUserBtns();
			guiObjectsFactory.userHomeWindowController.openWindow();
		}

}
