module ZerliServer {
	requires ZerliCommon;
	requires java.sql;
	requires ocsf;
	requires javafx.base;
	requires javafx.controls;
	requires javafx.fxml;
	requires mysql.connector.java;
	opens serverGUI to javafx.graphics, javafx.fxml, javafx.base;
}