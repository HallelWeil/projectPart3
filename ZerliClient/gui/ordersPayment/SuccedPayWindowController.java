package ordersPayment;


import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import main.GuiObjectsFactory;
import main.IGuiController;

public class SuccedPayWindowController implements IGuiController {

	private GuiObjectsFactory guiobjectfactory=GuiObjectsFactory.getInstance();
	
    @FXML
    private AnchorPane basepane;

    @FXML
    private Label succedfailedPayTxt;

    @FXML
    private Label labelSendEmail;

    @FXML
    private ImageView image;

	
	@Override
	public Pane getBasePane() {
		return basepane;
	}

	@Override
	public void resetController() {
		labelSendEmail.setText(labelSendEmail.getText());
		
	}

	@Override
	public void openWindow() {
		initmywindow();
		guiobjectfactory.mainWindowController.showNewWindow(basepane);	
		guiobjectfactory.mainWindowController.changeWindowName("Payment Approval");	
		
	}
	
	public void initmywindow() {
		if(guiobjectfactory.confirmOrder.isEmailSend)
		{
			labelSendEmail.setText(labelSendEmail.getText()+"\nA receipt send to email :"+guiobjectfactory.userBaundary.CurrentUser.getEmail()); //change email to what in user object in userBoudary
		}
	}

}
