package com.example.firefly;

import com.example.firefly.grpc.FireflyGrpc;
import com.example.firefly.grpc.PhaseRequest;
import com.example.firefly.grpc.PhaseResponse;
import io.grpc.stub.StreamObserver;

public class FireflyService extends FireflyGrpc.FireflyImplBase {
    private double phase;
    private final String fireflyId;
    private final double omega; // Eigenfrequenz des Glühwürmchens
    private final double coupling;

    public FireflyService(String fireflyId, double coupling, int threadsleeptime) {
        this.fireflyId = fireflyId;
        this.phase = Math.random() * 2 * Math.PI;
        this.omega = Math.random() * 2 * Math.PI * threadsleeptime/1000.0;
        this.coupling = coupling;
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
        double sum = 0.0;

        for (FireflyClient neighbor : neighbors) {
            try {
                double neighborPhase = neighbor.getPhase();
                sum += Math.sin(neighborPhase - this.phase);
            } catch (Exception e) {
               // System.err.println("Fehler beim Abrufen der Phase von Nachbarn: " + e.getMessage());
            }
        }

        this.phase += omega + coupling * sum / 4.0;
        this.phase = (this.phase ) % (2 * Math.PI);
        System.out.println();
        System.out.println();
        System.out.println();
        System.out.println();
        System.out.println();
        System.out.println("Synchronisierte Phase von " + fireflyId + ": " + phase);
        System.out.println();
        System.out.println();
        System.out.println();
        System.out.println();
        System.out.println();
    }
}
