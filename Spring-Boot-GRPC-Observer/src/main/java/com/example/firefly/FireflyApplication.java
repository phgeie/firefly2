package com.example.firefly;

import com.example.firefly.controller.MainController;
import com.example.firefly.service.SharedObjectService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class FireflyApplication {
	public static SharedObjectService sharedObjectService;
	public static void main(String[] args) throws Exception {

		int row = Integer.parseInt(args[0]);
		int column = Integer.parseInt(args[1]);
		int time = Integer.parseInt(args[2]);
		FireflyClient fireflyClient = new FireflyClient();

		for (int i=0; i< row* column; i++ ) {
			fireflyClient.addNeighbor("localhost", 50051 + i);
		}
		FireflyService service = new FireflyService(fireflyClient);
		FireflyServer server = new FireflyServer(8080, service);
		sharedObjectService = new SharedObjectService();
		sharedObjectService.setRow(row);
		sharedObjectService.setCol(column);
		sharedObjectService.setTime(time);
		SpringApplication.run(FireflyApplication.class, args);
		server.start(sharedObjectService);

	}
	@Bean
	CommandLineRunner run(ApplicationContext context) {
		return args -> {
			// MainController über den Spring-Kontext abrufen
			MainController mainController = context.getBean(MainController.class);

			// Methoden des Controllers verwenden (z. B. für Tests oder Initialisierung)
			mainController.setPhase(sharedObjectService);
		};
	}
}
