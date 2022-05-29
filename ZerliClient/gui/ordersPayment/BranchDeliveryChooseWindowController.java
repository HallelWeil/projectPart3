package ordersPayment;


import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import main.GuiObjectsFactory;
import main.IGuiController;
import order.DeliveryDetails;

public class BranchDeliveryChooseWindowController implements IGuiController {
	

    @FXML
    private AnchorPane basePane;

    @FXML
    private CheckBox GreetingCardChoosen;

    @FXML
    private Button chooseButton;

    @FXML
    private RadioButton storePickupToggle;

    @FXML
    private ToggleGroup DeliveryOption;

    @FXML
    private RadioButton homeDeliverytoggle;

    @FXML
    private Button backButton;

    @FXML
    private ComboBox<?> BranchChosen;

    @FXML
    private Label ErrorText;

	private GuiObjectsFactory guiobjectfactory = GuiObjectsFactory.getInstance();

    
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
		guiobjectfactory.mainWindowController.changeWindowName("Branch&DeliveryChoose");
		
	}

    @FXML
    void backButtonPressed(ActionEvent event) {
         
    }

    @FXML
    void chooseButtonPressed(ActionEvent event) {
    	//---------------------------------------------choose personalCard-----
    	if(GreetingCardChoosen.isSelected())
    	{
    		guiobjectfactory.shopBoundary.selectPersonalCard(true);
    	}
    	else //default is false but in case user choose to add then make back and choose not to add 
    	{
    		guiobjectfactory.shopBoundary.selectPersonalCard(false);
    	}
    	
    	if(homeDeliverytoggle.isSelected())
    	{
    	   guiobjectfactory.shopBoundary.selectDeliveryOption(true);	
    	}
    	if(storePickupToggle.isSelected())
    	{
    		guiobjectfactory.shopBoundary.selectDeliveryOption(false);
    	}
    	//-----------------------------------------------------------------------
      
    	if(guiobjectfactory.shopBoundary.isPersonalCardflag())
    	{
    		guiobjectfactory.personalCardcontroller.openWindow();
    	}
    	else if(guiobjectfactory.shopBoundary.isHomeDeliveryflag())
    	{
    		guiobjectfactory.HomeDeliveryDetails.openWindow();
    	}
    	else
    	{
    	    guiobjectfactory.order=guiobjectfactory.shopBoundary.placeOrder(); //in case the user didn't put v in homeDelivery and Personal card then we do there placeOrder
    		guiobjectfactory.confirmOrder.openWindow();
    	}

    }
}
