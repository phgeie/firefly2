package com.example.firefly;

import com.example.firefly.grpc.FireflyGrpc;
import com.example.firefly.grpc.PhaseRequest;
import com.example.firefly.grpc.PhaseResponse;
import com.example.firefly.service.SharedObjectService;
import io.grpc.stub.StreamObserver;

public class FireflyService extends FireflyGrpc.FireflyImplBase {
    private double phase;

    public FireflyService() {
        this.phase = 0.0;
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

    public void updatePhase(SharedObjectService sharedObjectService) {
        this.phase = sharedObjectService.getPhase();
    }
}
