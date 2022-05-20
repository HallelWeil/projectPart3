package mainWindow;

import java.net.URL;

import buttons.BtnController;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import main.ClientUI;
import main.GuiObjectsFactory;
import main.IGuiController;

public class MainWindowController implements IGuiController {

	private BtnController btnController;

	@FXML
	private GridPane TopMenu;

	@FXML
	private VBox bottomBox;

	@FXML
	private VBox mainWindowRoot;

	@FXML
	private VBox topMenuCell0;

	@FXML
	private VBox topMenuCell1;

	@FXML
	private VBox topMenuCell2;

	@FXML
	private VBox topMenuCell3;

	@FXML
	private VBox topMenuCell4;

	@FXML
	private VBox topMenuCell5;

	@FXML
	private VBox topMenuCell6;

	@FXML
	private VBox topMenuCell7;

	private VBox[] cells;

	public void showMainWindow() {
		emptyWindow();
		cells[1].getChildren().add(btnController.getHomeBtn());
		cells[7].getChildren().add(btnController.getAccessibilityBtn());
		cells[6].getChildren().add(btnController.getLoginBtn());
		changeWindowName("MainWindow");
	}

	public void showLoginWindow() {
		emptyWindow();
		resetBtns();
		cells[1].getChildren().add(btnController.getHomeBtn());
		cells[7].getChildren().add(btnController.getAccessibilityBtn());
		cells[6].getChildren().add(btnController.getInfoBtn());
		bottomBox.getChildren().add(GuiObjectsFactory.getInstance().loginGuiController.getBasePane());
		changeWindowName("Login");
	}

	public void init(BtnController btnController) {
		this.btnController = btnController;
		cells = new VBox[8];
		cells[0] = topMenuCell0;
		cells[1] = topMenuCell1;
		cells[2] = topMenuCell2;
		cells[3] = topMenuCell3;
		cells[4] = topMenuCell4;
		cells[5] = topMenuCell5;
		cells[6] = topMenuCell6;
		cells[7] = topMenuCell7;

	}

	public VBox getMainWindowRoot() {
		return mainWindowRoot;
	}

	@Override
	public Pane getBasePane() {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * Set new pane in the main menu
	 * 
	 * @param pane
	 */
	public void showNewWindow(Pane pane) {
		emptyWindow();
		bottomBox.getChildren().add(pane);
	}

	@Override
	public void resetController() {
		// TODO Auto-generated method stub

	}

	/**
	 * Change the main window name
	 * 
	 * @param s the new name
	 */
	public void changeWindowName(String s) {
		ClientUI.globalstage.setTitle(s);
	}

	/**
	 * Empty the main window, the top menu stay the same
	 */
	private void emptyWindow() {
		bottomBox.getChildren().removeAll(bottomBox.getChildren());
	}

	/**
	 * Remove all the buttons from the top menu
	 */
	public void resetBtns() {
		for (int i = 0; i < 8; i++) {
			cells[i].getChildren().removeAll(cells[i].getChildren());
		}
	}

	/**
	 * Add button to the given place, only if the place is empty
	 * 
	 * @param btn   the button to add
	 * @param place the place between 0(first from the left) to 7(last)
	 */
	public void setBtn(Button btn, int place) {
		if (place < 0 || place > 7)
			return;
		if (cells[place].getChildren().isEmpty())
			cells[place].getChildren().add(btn);
	}

}
