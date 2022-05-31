package branchManager;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import main.GuiObjectsFactory;
import main.IGuiController;
import user.User;
import user.UserStatus;
import user.UserType;
import usersManagment.BranchManagerBoundary;


public class ManagerUpdateUser implements IGuiController {
	private GuiObjectsFactory guiObjectsFactory = GuiObjectsFactory.getInstance();
	private BranchManagerBoundary managerBoundry = guiObjectsFactory.branchManagerBoundary;
	private User user = null;
	

    @FXML
    private AnchorPane managerUpdateUserPane;

    @FXML
    private TextField usernameSearch;

    @FXML
    private Button updateInfoBot;

    @FXML
    private TextField userBranchInfo;

    @FXML
    private TextField userEmailInfo;

    @FXML
    private TextField userFirstNameInfo;

    @FXML
    private Label userFirstNameText;

    @FXML
    private TextField userID;

    @FXML
    private TextField userLastNameInfo;

    @FXML
    private Label userLastNameText;

    @FXML
    private TextField userPhoneInfo;

    @FXML
    private ComboBox<UserStatus> userStatusCombo;

    @FXML
    private ComboBox<UserType> userTypeCombo;

    @FXML
    private TextField userUsername;
    
    @FXML
    private TextField userIsConnected;

    @FXML
    private Button watchUserBot;

    @FXML
    private Label notFoundText;
    
	@Override
	public Pane getBasePane() {
		return managerUpdateUserPane;
	}
	
	@Override
	public void resetController() {
    	user = null;
    	userUsername.setText("");
    	userFirstNameInfo.setText("");
    	userLastNameInfo.setText("");
    	userEmailInfo.setText("");
    	userID.setText("");
    	userPhoneInfo.setText("");
    	userBranchInfo.setText("");
    	userIsConnected.setText("");
    	userTypeCombo.getSelectionModel().clearSelection();
    	userStatusCombo.getSelectionModel().clearSelection();
    	notFoundText.setVisible(false);
	}
	
	@Override
	public void openWindow() {
    	guiObjectsFactory.mainWindowController.changeWindowName("Manager - update user");
    	guiObjectsFactory.mainWindowController.showNewWindow(managerUpdateUserPane);
    	userStatusCombo.getItems().setAll(UserStatus.values());
    	userTypeCombo.getItems().setAll(UserType.values());
	}

    @FXML
    void UpdateUserInformation(ActionEvent event) {
    	managerBoundry.requestUpdateUserData(user.getUsername(), user.getUserType(), user.getStatus());
    }

    @FXML
    void WatchUserInfo(ActionEvent event) {
    	user = managerBoundry.requestUser(usernameSearch.getText());
    	if(user != null)
    	{
	    	userUsername.setText(user.getUsername());
	    	userFirstNameInfo.setText(user.getFirstName());
	    	userLastNameInfo.setText(user.getLastName());
	    	userEmailInfo.setText(user.getEmail());
	    	userID.setText(user.getPersonID());
	    	userPhoneInfo.setText(user.getPhoneNumber());
	    	userBranchInfo.setText(user.getBranchName());
	    	userIsConnected.setText(Boolean.toString(user.isConnected()));
	    	userTypeCombo.getSelectionModel().select(user.getUserType());
	    	userStatusCombo.getSelectionModel().select(user.getStatus());
    	} else {
    		notFoundText.setVisible(true);
    	}
    }

}