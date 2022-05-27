package ordersPayment;


import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import main.GuiObjectsFactory;
import main.IGuiController;

public class failedtopayWindowController implements IGuiController {

    @FXML
    private AnchorPane basepane;

    @FXML
    private ImageView image;

    @FXML
    private Label paymentfailed;

    @FXML
    private Label sentenceFailed;

    private GuiObjectsFactory guiobjectfactory=GuiObjectsFactory.getInstance();
    
	@Override
	public Pane getBasePane() {
		return basepane;
	}

	@Override
	public void resetController() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void openWindow() {
		guiobjectfactory.mainWindowController.showNewWindow(basepane);	
		guiobjectfactory.mainWindowController.changeWindowName("payment not approval");	
		
	}

}
