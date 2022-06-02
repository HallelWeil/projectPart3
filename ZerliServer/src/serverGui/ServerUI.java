package serverGui;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import scheduledTasks.ScheduledTasksManager;
import simulators.ServerSimulatorsManager;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;

public class ServerUI extends Application {
	private ServerGuiController controller;

	@Override
	public void start(Stage stage) {
		AnchorPane root;
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/serverGui/ServerGUI.fxml"));
			root = loader.load();
			controller = loader.getController();
			stage.setTitle("Server");
			Scene scene = new Scene(root);
			stage.setScene(scene);
			stage.show();
		} catch (Exception e) {
			System.out.println("gui problem");
			e.printStackTrace();
		}
		// run the simulators
		Thread t = new Thread(ServerSimulatorsManager.getInstance());
		t.run();
	}

	@Override
	public void stop() {
		try {
			controller.getBoundary().quit();
		} catch (Exception e) {
			// do nothing
		}
		System.out.println("Exit server, goodbye");
		System.exit(1);
	}

	public static void main(String[] args) {
		launch();
	}
}