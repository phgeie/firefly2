package com.example.firefly;

import com.example.firefly.grpc.FireflyGrpc;
import com.example.firefly.grpc.PhaseRequest;
import com.example.firefly.grpc.PhaseResponse;
import io.grpc.stub.StreamObserver;

public class FireflyService extends FireflyGrpc.FireflyImplBase {
    private double phase;
    private final String fireflyId;
    private final double omega; // Eigenfrequenz des Glühwürmchens
    private static final double K = 0.99;

    public FireflyService(String fireflyId) {
        this.fireflyId = fireflyId;
        this.phase = Math.random() * 2 * Math.PI;
        this.omega = Math.random() * 2 * Math.PI * 50.0/1000.0;
    }


    @Override
    public void getPhase(PhaseRequest request, StreamObserver<PhaseResponse> responseObserver) {
        PhaseResponse response = PhaseResponse.newBuilder().setPhase(this.phase).build();
        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }

    public double getPhase() {
        return this.phase;
    }

    public void updatePhase(FireflyClient[] neighbors) {
        double ownPhase = this.phase;
        double sum = ownPhase;

        for (FireflyClient neighbor : neighbors) {
            try {
                double neighborPhase = neighbor.getPhase();
                sum += Math.sin(neighborPhase - this.phase);
            } catch (Exception e) {
               // System.err.println("Fehler beim Abrufen der Phase von Nachbarn: " + e.getMessage());
            }
        }

        this.phase += omega + K * sum / neighbors.length;
        this.phase = (this.phase ) % (2 * Math.PI);
        System.out.println("Synchronisierte Phase von " + fireflyId + ": " + phase);
    }
}
