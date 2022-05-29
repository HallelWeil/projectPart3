package ordersPayment;

import javafx.event.ActionEvent;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import main.GuiObjectsFactory;
import main.IGuiController;
import order.DeliveryDetails;
import shop.ShopBoundary;


public class personalcardWindowController implements IGuiController {

	

	private GuiObjectsFactory guiobjectfactory=GuiObjectsFactory.getInstance();
	
	  @FXML
	    private AnchorPane basepane;

	    @FXML
	    private Button backButton;

	    @FXML
	    private TextArea textareagreeting;

	    @FXML
	    private Button nextbutton;
	    
	    @FXML
	    private Label errorlabel;
        
	    public String cardtxt;
	    
	    @FXML
	    void backbuttonpreesed(ActionEvent event) {
           guiobjectfactory.branch_Delivery.openWindow();       
	    }

	    @FXML
	    void nextbuttonpressed(ActionEvent event) {
	    	 cardtxt=textareagreeting.getText();
	    	if(cardtxt.isEmpty()) //check if user didn't fill the text area 
	    	{
	    	seterror("your personal card is empty please fill in the blank area!!!"); 
	    	return;
	    	}
	    	 guiobjectfactory.shopBoundary.addPersonalLetter(cardtxt); //save letter in cart of shop
            if(guiobjectfactory.shopBoundary.isHomeDeliveryflag()) //if true mean customer want home delivery
            {
            	guiobjectfactory.HomeDeliveryDetails.openWindow();
            }
            else //if customer didn't put V in Checkbox for home Delivery
            {
        	    guiobjectfactory.order=guiobjectfactory.shopBoundary.placeOrder();
            	guiobjectfactory.confirmOrder.openWindow();
            }
	    }
	@Override
	public Pane getBasePane() {
		return basepane;
	}

	@Override
	public void resetController() {
		// TODO Auto-generated method stub
		
	}
	
	/**
	 * 
	 */
	private void seterror(String ErrorMsg)
	{
		errorlabel.setText(ErrorMsg);
	}
	
	
	
	@Override
	public void openWindow() {
		
		guiobjectfactory.mainWindowController.showNewWindow(basepane);
		guiobjectfactory.mainWindowController.changeWindowName("personalCard");
		
	}

}
