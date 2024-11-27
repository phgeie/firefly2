package com.example.firefly;

import com.example.firefly.grpc.FireflyGrpc;
import com.example.firefly.grpc.PhaseRequest;
import com.example.firefly.grpc.PhaseResponse;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;

public class FireflyClient {
    private final ManagedChannel channel;
    private final FireflyGrpc.FireflyBlockingStub stub;

    public FireflyClient(String host, int port) {
        this.channel = ManagedChannelBuilder.forAddress(host, port).usePlaintext().build();
        this.stub = FireflyGrpc.newBlockingStub(channel);
    }

    public double getPhase() {
        PhaseRequest request = PhaseRequest.newBuilder().setFireflyId("any").build();
        PhaseResponse response = stub.getPhase(request);
        return response.getPhase();
    }

    public void shutdown() {
        channel.shutdown();
    }
}
