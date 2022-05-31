package customerService;

import java.io.IOException;

import java.util.ArrayList;

import common.Status;
import complaint.Complaint;
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
import usersManagment.CustomerServiceEmployeeBoundary;

public class UpdateComplaint implements IGuiController {

		private GuiObjectsFactory guiObjectsFactory = GuiObjectsFactory.getInstance();
		private CustomerServiceEmployeeBoundary complaintBoundary = guiObjectsFactory.employeeServiceBoundary;


		@FXML
		private HBox updateComplaintPane;
		
		@FXML
		private Label answerLabel;
		
		@FXML
		private Label complaintNumberLabel;

		@FXML
		private Label erorrLabel;

		@FXML
		private Label compensationLabel;
		
		@FXML
		private TextField answerText;

		@FXML
		private TextField compensationText;
		
		@FXML
		private TextField complaintNumberText;

		@FXML
		private Button updateBtn;
		
		@FXML
		private Button nextBtn;
		
		@FXML
		private Button backBtn;

		ShowAllComplaints showAllComplaints = new ShowAllComplaints();

		@FXML
		void updateComplaint(ActionEvent event)
		{
			String answer = answerText.getText();
			double compensation = Double.valueOf(compensationText.getText());
			int number = Integer.valueOf(complaintNumberText.getText());
			Status status = Status.Completed;
			ArrayList <Complaint> complaints = complaintBoundary.getMyComplaints();
			Complaint complaintToUpdate = null;;
			for(int i = 0; i < complaints.size(); i++)
			{
				if(complaints.get(i).getComplaintsNumber() == number)
				{
					complaintToUpdate = complaints.get(i);
				}
			}
			if(complaintToUpdate == null)
			{
				setError("There is no such complaint number");
			}
			else
			{
				try {
					complaintBoundary.handlingComplaints(complaintToUpdate, answer, compensation, status);
					
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}

		private void setError(String errorMsg) {
			erorrLabel.setText(errorMsg);
		}

		@Override
		public Pane getBasePane() {
			return updateComplaintPane;
		}

		@Override
		public void resetController() {
		
			complaintNumberText.clear();
			answerText.clear();
			compensationText.clear();
		}

		@Override
		public void openWindow() {
			// move to the next window
			guiObjectsFactory.mainWindowController.showNewWindow(getBasePane());
			// change to the name
			guiObjectsFactory.mainWindowController.changeWindowName("update Complaint");
		}

		/**
		 * go to the user's start window
		 */
		private void goToStartWindow() {
			// add the global buttons
			guiObjectsFactory.btnMenuManager.setUserBtns();
			guiObjectsFactory.userHomeWindowController.openWindow();
		}
		
		@FXML
	    void BackToShowAllComplaintsWindow(ActionEvent event) {
	    	resetController();
	    	guiObjectsFactory.mainWindowController.showNewWindow(showAllComplaints.getBasePane());
	    }

}
