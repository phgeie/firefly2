package com.example.firefly;

import com.example.firefly.grpc.FireflyGrpc;
import com.example.firefly.grpc.PhaseRequest;
import com.example.firefly.grpc.PhaseResponse;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;

import java.util.ArrayList;
import java.util.List;

public class FireflyClient {
    private final List<String> neighbors;

    public FireflyClient() {
        this.neighbors = new ArrayList<>();
    }

    public void addNeighbor(String host, int port) {
        neighbors.add(host + ":" + port);
    }

    public List<Double> fetchNeighborPhases() {
        List<Double> phases = new ArrayList<>();
        System.out.println(neighbors);
        for (String neighbor : neighbors) {
                String[] parts = neighbor.split(":");
            System.out.println(parts[0] + ":" + parts[1]);
                String host = parts[0];
                int port = Integer.parseInt(parts[1]);

                ManagedChannel neighborChannel = ManagedChannelBuilder.forAddress(host, port).usePlaintext().build();
                FireflyGrpc.FireflyBlockingStub neighborStub = FireflyGrpc.newBlockingStub(neighborChannel);

                PhaseRequest request = PhaseRequest.newBuilder().setFireflyId("any").build();
                PhaseResponse response = neighborStub.getPhase(request);
                phases.add(response.getPhase());

                neighborChannel.shutdown();

        }
        return phases;
    }

    public double getObserver(){
        ManagedChannel neighborChannel = ManagedChannelBuilder.forAddress("localhost", 8080).usePlaintext().build();
        FireflyGrpc.FireflyBlockingStub neighborStub = FireflyGrpc.newBlockingStub(neighborChannel);
        PhaseRequest request = PhaseRequest.newBuilder().setFireflyId("any").build();
        PhaseResponse response = neighborStub.getPhase(request);
        return response.getPhase();
    }


}
