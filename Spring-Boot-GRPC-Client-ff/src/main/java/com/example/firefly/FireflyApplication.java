package com.example.firefly;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class FireflyApplication {
	public static void main(String[] args) throws Exception {
		if (args.length < 3) {
			System.err.println("Verwendung: java FireflyApplication <fireflyId> <port> <neighbor1:port1> <neighbor2:port2> ...");
			return;
		}

		String fireflyId = args[0];
		int port = Integer.parseInt(args[1]);
		String[] neighbors = new String[args.length - 2];
		System.arraycopy(args, 2, neighbors, 0, args.length - 2);

		FireflyServer server = new FireflyServer(fireflyId, port, neighbors);


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
				Thread.sleep(1000); // 1 Sekunde wart
			} catch (Exception e) {

				e.printStackTrace();
			}
		}
		server.start();
	}
}
