package com.example.firefly;

import com.example.firefly.service.SharedObjectService;
import io.grpc.Server;
import io.grpc.ServerBuilder;

public class FireflyServer {
    private final int port;
    private final FireflyService fireflyService;

    public FireflyServer(int port, FireflyService fireflyService) {
        this.port = port;
        this.fireflyService = fireflyService;
    }

    public void start(SharedObjectService sharedObjectService) throws Exception {
        Server server = ServerBuilder.forPort(port).addService(fireflyService).build().start();

        Runtime.getRuntime().addShutdownHook(new Thread(server::shutdown));

        while (true) {
            try {
                Thread.sleep(100);// 1 Sekunde warten
                fireflyService.updatePhase(sharedObjectService);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                break;
            }
        }
    }
}
