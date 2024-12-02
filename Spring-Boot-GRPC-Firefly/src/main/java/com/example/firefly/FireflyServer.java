package com.example.firefly;

import io.grpc.Server;
import io.grpc.ServerBuilder;

import java.io.IOException;

public class FireflyServer {
    private final int port;
    private final FireflyService fireflyService;
    private Server server;

    public FireflyServer(int port, FireflyService fireflyService) {
        this.port = port;
        this.fireflyService = fireflyService;
    }

    public void start() throws IOException {
        server = ServerBuilder.forPort(port).addService(fireflyService).build().start();
        System.out.println("Server gestartet auf Port " + port);

        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            if (server != null) {
                server.shutdown();
            }
        }));
    }
}
