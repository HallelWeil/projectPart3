package main;

import client.ClientBoundary;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import userGuiManagment.MainWindowGuiManager;

public class ClientUI extends Application {

	public static final String DEFAULT_HOST = "localhost";
	public static final int DEFAULT_PORT = 5555;
	public static ClientBoundary clientBoundary;
	public static String host;
	public static int port;
	public static Stage globalstage;

	@Override
	public void start(Stage stage) {

		clientBoundary = new ClientBoundary();
		clientBoundary.connect(host, port);
		MainWindowGuiManager mainWindowManager = MainWindowGuiManager.getInstance();
		globalstage = stage;
		try {

			Scene scene = new Scene(mainWindowManager.mainWindowController.getMainWindowRoot());
			stage.setScene(scene);
			stage.show();
			mainWindowManager.mainWindowController.init();
			mainWindowManager.mainWindowController.openWindow();

		} catch (Exception e) {
			System.out.println("gui problem " + e.getMessage());
			e.printStackTrace();
		}
	}

	/**
	 * Stop the clients, called on exit the window, close the connection and exit
	 * the program
	 */
	@Override
	public void stop() {
		try {
			clientBoundary.quit();
		} catch (Exception e) {
			// do nothing
		}
		System.out.println("End");
		System.exit(1);
	}

	// should have arguments "host" "port"
	public static void main(String[] args) {
		// get the ip and port from the arguments
		host = DEFAULT_HOST;
		port = DEFAULT_PORT;
		try {
			host = args[0];
		} catch (ArrayIndexOutOfBoundsException e) {
		}
		try {
			port = Integer.parseInt(args[1]);
		} catch (ArrayIndexOutOfBoundsException e) {
		}
		clientBoundary = new ClientBoundary();

		launch();
	}
}
