package simulators;

import java.io.IOException;
import java.net.URL;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class ServerSimulatorsManager implements Runnable {

	private static ServerSimulatorsManager instance;

	private ServerSimulatorsManager() {
		SimulationsLog = FXCollections.observableArrayList();
	}

	public static ServerSimulatorsManager getInstance() {
		if (instance == null) {
			instance = new ServerSimulatorsManager();
		}
		return instance;
	}

	public ObservableList<String> SimulationsLog;
	private ServerSimulationsController controller;

	public void runSimulators() {
		AnchorPane root;
		try {
			FXMLLoader loader = new FXMLLoader();
			final URL resource = getClass().getResource("/simulators/ServerSimulations.fxml");
			loader.setLocation(resource);
			root = loader.load();
			Stage stage = new Stage();
			stage.setTitle("Simulations");
			controller = loader.getController();
			controller.initLog();
			stage.setScene(new Scene(root));
			stage.show();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void run() {
		// run the simulations
		runSimulators();
	}

}
