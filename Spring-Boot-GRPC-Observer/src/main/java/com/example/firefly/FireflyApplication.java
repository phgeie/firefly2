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
		FireflyServer server = new FireflyServer("observer", 8080);
		sharedObjectService = new SharedObjectService();
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
			System.out.println();
			System.out.println();
			System.out.println();
			System.out.println();
			System.out.println("TEST");
			System.out.println();
			System.out.println();
			System.out.println();
		};
	}
}
