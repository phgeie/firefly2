package com.example.firefly;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Arrays;

@SpringBootApplication
public class FireflyApplication {
	public static void main(String[] args) throws Exception {
		if (args.length < 3) {
			System.err.println("Verwendung: java FireflyApplication <fireflyId> <port> <neighbor1:port1> <neighbor2:port2> ...");
			return;
		}

		String fireflyId = args[0];
		int id = Integer.parseInt(fireflyId);
		int row = Integer.parseInt(args[1]);
		int column = Integer.parseInt(args[2]);
		if (row * column > 1000) {
			System.err.println("too much... stop it");
			return;
		}
		int myColumn = id % column;
		int myRow = (id - myColumn)/column;
		System.out.println("myColumn: " + myColumn + ", myRow: " + myRow + ", Port: " + id);

		int port = 50051 + id;
		String[] neighbors = new String[4];

		// oben
		int neighborRow = (myRow - 1 + row) % row;
		int neighborCol = myColumn;
		int neighborPort = 50051 + row * neighborRow + neighborCol;
		neighbors[0] = "localhost:" + neighborPort;

		// unten
		neighborRow = (myRow + 1) % row;
		neighborCol = myColumn;
		neighborPort = 50051 + row * neighborRow + neighborCol;
		neighbors[1] = "localhost:" + neighborPort;

		// links
		neighborRow = myRow;
		neighborCol = (myColumn - 1 + column) % column;
		neighborPort = 50051 + row * neighborRow + neighborCol;
		neighbors[2] = "localhost:" + neighborPort;

		// rechts
		neighborRow = myRow;
		neighborCol = (myColumn + 1) % column;
		neighborPort = 50051 + row * neighborRow + neighborCol;
		neighbors[3] = "localhost:" + neighborPort;
		System.out.println();
		System.out.println();
		System.out.println();
		System.out.println("TTTTTTTTTT: " + Arrays.toString(neighbors));
		System.out.println();
		System.out.println();
		System.out.println();

		double coupling = Double.parseDouble(args[3]);
		int threadsleeptime = Integer.parseInt(args[4]);

		FireflyServer server = new FireflyServer(fireflyId, port, neighbors, coupling, threadsleeptime);

		FireflyClient observer = new FireflyClient("localhost",8080);

		double phase = 0.0;
		while(phase != -1){
			try {
				phase = observer.getPhase();
				System.out.println();
				System.out.println();
				System.out.println();
				System.out.println("Phase: " + phase);
				System.out.println();
				System.out.println();
				System.out.println();
				Thread.sleep(1000); // 1 Sekunde warten
			} catch (Exception e) {

				e.printStackTrace();
			}
		}
		server.start();
	}
}
