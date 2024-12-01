package com.example.firefly;

import com.example.firefly.grpc.FireflyGrpc;
import com.example.firefly.grpc.PhaseRequest;
import com.example.firefly.grpc.PhaseResponse;
import com.example.firefly.service.SharedObjectService;
import io.grpc.stub.StreamObserver;

import java.util.List;

public class FireflyService extends FireflyGrpc.FireflyImplBase {
    private double phase;
    private final FireflyClient client;

    public FireflyService(FireflyClient client) {
        this.phase = 0.0;
        this.client = client;
    }


    @Override
    public void getPhase(PhaseRequest request, StreamObserver<PhaseResponse> responseObserver) {
        PhaseResponse response = PhaseResponse.newBuilder().setPhase(this.phase).build();
        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }

    public synchronized void setPhases(SharedObjectService sharedObjectService) {
        List<Double> neighborPhases = client.fetchNeighborPhases();
        sharedObjectService.setPhases(neighborPhases);

    }

    public void updatePhase(SharedObjectService sharedObjectService) {

        this.phase = sharedObjectService.getPhase();
        if (this.phase == -1) {
            setPhases(sharedObjectService);
        }
    }
}
