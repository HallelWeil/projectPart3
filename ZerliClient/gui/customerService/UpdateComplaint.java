package customerService;

import common.Status;
import complaint.Complaint;
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

public class UpdateComplaint implements IGuiController {

	private GuiObjectsFactory guiObjectsFactory = GuiObjectsFactory.getInstance();
	private CustomerServiceEmployeeBoundary complaintBoundary = guiObjectsFactory.employeeServiceBoundary;

	@FXML
	private Label answerLabel;

	@FXML
	private TextField answerText;

	@FXML
	private Button backBtn;

	@FXML
	private AnchorPane basePane;

	@FXML
	private Label compensationLabel;

	@FXML
	private TextField compensationText;

	@FXML
	private Label msgLabel;

	@FXML
	private Button updateBtn;

	Complaint selectedcomplaint;

	@FXML
	void updateComplaint(ActionEvent event) {
		String answer = answerText.getText();
		double compensation = Double.valueOf(compensationText.getText());
		Status status = Status.Completed;

		try {
			complaintBoundary.handlingComplaints(selectedcomplaint, answer, compensation, status);
			msgLabel.setText("Complaint successfully update");

		} catch (Exception e) {
			msgLabel.setText("Complaint unsuccessfully update");

		}
	}

	public void setSelectedComplaint(Complaint selectedComplaint) {
		selectedcomplaint = selectedComplaint;
	}

	@Override
	public Pane getBasePane() {
		return basePane;
	}

	@Override
	public void resetController() {

		answerText.clear();
		compensationText.clear();
	}

	@Override
	public void openWindow() {
		// move to the next window
		guiObjectsFactory.mainWindowController.showNewWindow(basePane);
		// change to the name
		guiObjectsFactory.mainWindowController
				.changeWindowName("update Complaint - number #" + selectedcomplaint.getComplaintsNumber());
	}

	@FXML
	void BackToShowAllComplaintsWindow(ActionEvent event) {
		resetController();
		guiObjectsFactory.showAllComplaints.openWindow();
	}

}
