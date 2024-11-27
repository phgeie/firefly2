package com.example.firefly;

import io.grpc.Server;
import io.grpc.ServerBuilder;

public class FireflyServer {
    private final String fireflyId;
    private final int port;
    private final FireflyService fireflyService;
    private final FireflyClient[] neighbors;

    public FireflyServer(String fireflyId, int port, String[] neighborAddresses) {
        this.fireflyId = fireflyId;
        this.port = port;
        this.fireflyService = new FireflyService(fireflyId);

        this.neighbors = new FireflyClient[neighborAddresses.length];
        for (int i = 0; i < neighborAddresses.length; i++) {
            String[] address = neighborAddresses[i].split(":");
            this.neighbors[i] = new FireflyClient(address[0], Integer.parseInt(address[1]));
        }
    }

    public void start() throws Exception {
        Server server = ServerBuilder.forPort(port).addService(fireflyService).build().start();
        System.out.println(fireflyId + " Server gestartet auf Port " + port);

        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            for (FireflyClient client : neighbors) {
                client.shutdown();
            }
            server.shutdown();
        }));

        while (true) {
            try {
                Thread.sleep(100); // 1 Sekunde warten
                fireflyService.updatePhase(neighbors);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                break;
            }
        }
    }
}
