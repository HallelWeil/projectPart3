package server;

import java.util.Scanner;

//temp class for testing
public class mainServer {

	public static void main(String[] args) {
		ServerBoundary sb = new ServerBoundary();
		sb.connect(5555, "0", "0", "0");
	}
}