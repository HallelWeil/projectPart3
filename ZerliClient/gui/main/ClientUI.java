package main;

import java.net.URL;

import buttons.BtnController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import mainWindow.MainWindowController;
import com.gluonhq.charm.glisten.control.Icon;

public class ClientUI extends Application {

	public static Stage globalstage;

	@Override
	public void start(Stage stage) {
		GuiObjectsFactory guiObjectsFactory = GuiObjectsFactory.getInstance();
		guiObjectsFactory.clientBoundary.connect("localhost", 5555);
		guiObjectsFactory.initBoundaries();
		globalstage = stage;
		try {

			guiObjectsFactory.loadAllFxmlFiles();
			Scene scene = new Scene(guiObjectsFactory.mainWindowController.getMainWindowRoot());
			stage.setScene(scene);
			stage.show();
			guiObjectsFactory.mainWindowController.init();
			guiObjectsFactory.mainWindowController.openWindow();
			 

		} catch (Exception e) {
			System.out.println("gui problem " + e.getMessage());
			e.printStackTrace();
		}

	}

	@Override
	public void stop() {

	}

	public static void main(String[] args) {
		launch();
	}
}
