package ordersPayment;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import main.GuiObjectsFactory;
import main.IGuiController;
import shop.ShopBoundary;
import userGuiManagment.AuthorizedCustomerGuiManager;
import userGuiManagment.MainWindowGuiManager;

public class SuccedPayWindowController implements IGuiController {

	private MainWindowGuiManager mainWindowManager = MainWindowGuiManager.getInstance();
	private AuthorizedCustomerGuiManager authorizedCustomerGuiManager = AuthorizedCustomerGuiManager.getInstance();

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
		mainWindowManager.mainWindowController.showNewWindow(basepane);
		mainWindowManager.mainWindowController.changeWindowName("Payment Approval");
		//we done withthe order lets empty the cart
		authorizedCustomerGuiManager.getShopBoundary().emptyCart();
		authorizedCustomerGuiManager.getShopWindowController().emptyCart();
	}

	public void initmywindow() {
		if (authorizedCustomerGuiManager.getConfirmOrder().isEmailSend) {
			labelSendEmail.setText(labelSendEmail.getText() + "\nA receipt send to email :"
					+ mainWindowManager.userBaundary.CurrentUser.getEmail()); // change email to what in user object in
																				// userBoudary
		}
	}

}
