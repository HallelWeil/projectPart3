package PromotionWindow;


import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import main.GuiObjectsFactory;
import main.IGuiController;

public class CreatePromotionWindowController implements IGuiController {

	@FXML
	private AnchorPane basePane;
	
	@FXML
	private TextField ItemID;

	@FXML
	private TextField Discount;

	@FXML
	private TextField PromotionTxt;

	@FXML
	private Button createbutton;

	@FXML
	private Button cancelbutton;

	@FXML
	private Label errorLabel;
	
	private GuiObjectsFactory guiobjectfactory = GuiObjectsFactory.getInstance();


	@FXML
	void cancelButtonPressed(ActionEvent event) {
	}

	@FXML
	void createButtonPressed(ActionEvent event) {
       if(ItemID.getText().isEmpty()||Discount.getText().isEmpty()||PromotionTxt.getText().isEmpty())
       {
    	   errorLabel.setText("**please fill all the Fields");
       }
       try {
      // guiobjectfactory.promotion.activatePromotion(Integer.parseInt(ItemID.getText()), Double.parseDouble(Discount.getText()),PromotionTxt.getText());
       }
       catch(Exception e) {
    	   errorLabel.setText("ERROR: Invalid Data");
    	   return;
       }
	}

	@Override
	public Pane getBasePane() {
		return basePane;
	}

	@Override
	public void resetController() {
		// TODO Auto-generated method stub

	}

	@Override
	public void openWindow() {
		guiobjectfactory.mainWindowController.showNewWindow(basePane);
		guiobjectfactory.mainWindowController.changeWindowName("RequestCreatePromotion");

	}

	// how to stop/delete a active/created/old promotion ????????????!!!!!!!!!
	// maybe we should add a start date and end date ???? !!!!!

}