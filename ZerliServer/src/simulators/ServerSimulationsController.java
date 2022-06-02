package simulators;

import java.time.LocalDateTime;

import javafx.collections.ListChangeListener;
import javafx.collections.ListChangeListener.Change;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;

public class ServerSimulationsController {

	private ServerSimulatorsManager serverSimulatorsManager = ServerSimulatorsManager.getInstance();
	@FXML
	private Button importDataBtn;

	@FXML
	private TextArea simulationsLog;

	@FXML
	void importData(ActionEvent event) {

	}

	public void initLog() {
		serverSimulatorsManager.SimulationsLog.addListener(new ListChangeListener<String>() {
			@SuppressWarnings("rawtypes")
			@Override
			public void onChanged(Change c) {
				updateConsole();

			}
		});
	}

	public void updateConsole() {
		synchronized (serverSimulatorsManager.SimulationsLog) {
			for (int i = 0; i < serverSimulatorsManager.SimulationsLog.size(); i++) {
				String s = serverSimulatorsManager.SimulationsLog.get(i);
				simulationsLog.appendText(LocalDateTime.now().toString() + "\n");
				simulationsLog.appendText(s + "\n");
				serverSimulatorsManager.SimulationsLog.remove(i);
			}
		}
	}
}
