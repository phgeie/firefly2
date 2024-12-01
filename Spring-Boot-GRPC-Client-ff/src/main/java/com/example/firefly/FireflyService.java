package com.example.firefly;

import com.example.firefly.grpc.FireflyGrpc;
import com.example.firefly.grpc.PhaseRequest;
import com.example.firefly.grpc.PhaseResponse;
import io.grpc.stub.StreamObserver;

import java.util.List;

public class FireflyService extends FireflyGrpc.FireflyImplBase {
    private double phase;
    private final double omega;
    private final double coupling;
    private final FireflyClient client;

    public FireflyService(double coupling, int threadsleeptime, FireflyClient client) {
        this.phase = Math.random() * 2 * Math.PI;
        this.omega = Math.random() * 2 * Math.PI * threadsleeptime / 1000.0;
        this.coupling = coupling;
        this.client = client;
    }

    @Override
    public void getPhase(PhaseRequest request, StreamObserver<PhaseResponse> responseObserver) {
        PhaseResponse response = PhaseResponse.newBuilder().setPhase(this.phase).build();
        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }

    public synchronized void updatePhase() {
        List<Double> neighborPhases = client.fetchNeighborPhases();
        double sum = 0.0;

        for (double neighborPhase : neighborPhases) {
            sum += Math.sin(neighborPhase - this.phase);
        }

        this.phase += omega + coupling * sum / 4.0;
        this.phase = this.phase % (2 * Math.PI);
        System.out.println("Synchronisierte Phase: " + phase);
    }
}
