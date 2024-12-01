package com.example.firefly;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Arrays;
import java.util.List;

@SpringBootApplication
public class FireflyApplication extends Thread {

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
		FireflyClient fireflyClient = new FireflyClient();

		// oben
		int neighborRow = (myRow - 1 + row) % row;
		int neighborCol = myColumn;
		int neighborPort = 50051 + column * neighborRow + neighborCol;
		fireflyClient.addNeighbor("localhost" , neighborPort);
		System.out.println("oben: " + neighborPort);

		// unten
		neighborRow = (myRow + 1) % row;
		neighborCol = myColumn;
		neighborPort = 50051 + column * neighborRow + neighborCol;
		fireflyClient.addNeighbor("localhost" , neighborPort);
		System.out.println("unten: " + neighborPort);

		// links
		neighborRow = myRow;
		neighborCol = (myColumn - 1 + column) % column;
		neighborPort = 50051 + column * neighborRow + neighborCol;
		fireflyClient.addNeighbor("localhost" , neighborPort);
		System.out.println("links: " + neighborPort);

		// rechts
		neighborRow = myRow;
		neighborCol = (myColumn + 1) % column;
		neighborPort = 50051 + column * neighborRow + neighborCol;
		fireflyClient.addNeighbor("localhost" , neighborPort);
		System.out.println("rechts: " + neighborPort);

		double coupling = Double.parseDouble(args[3]);
		int threadsleeptime = Integer.parseInt(args[4]);




		FireflyService service = new FireflyService(coupling, threadsleeptime, fireflyClient);
		FireflyServer server = new FireflyServer(port, service);
		server.start();
		double phase = 0.0;
		while(phase != -1){
			try {
				phase = fireflyClient.getObserver();
				System.out.println(phase);
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				Thread.currentThread().interrupt();
				break;
			}
		}


		FireflyProcess process = new FireflyProcess(service, threadsleeptime);
		process.start();
	}
}
