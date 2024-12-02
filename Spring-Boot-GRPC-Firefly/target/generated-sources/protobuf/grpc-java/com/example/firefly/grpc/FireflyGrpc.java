package com.example.firefly.grpc;

import static io.grpc.MethodDescriptor.generateFullMethodName;
import static io.grpc.stub.ClientCalls.asyncBidiStreamingCall;
import static io.grpc.stub.ClientCalls.asyncClientStreamingCall;
import static io.grpc.stub.ClientCalls.asyncServerStreamingCall;
import static io.grpc.stub.ClientCalls.asyncUnaryCall;
import static io.grpc.stub.ClientCalls.blockingServerStreamingCall;
import static io.grpc.stub.ClientCalls.blockingUnaryCall;
import static io.grpc.stub.ClientCalls.futureUnaryCall;
import static io.grpc.stub.ServerCalls.asyncBidiStreamingCall;
import static io.grpc.stub.ServerCalls.asyncClientStreamingCall;
import static io.grpc.stub.ServerCalls.asyncServerStreamingCall;
import static io.grpc.stub.ServerCalls.asyncUnaryCall;
import static io.grpc.stub.ServerCalls.asyncUnimplementedStreamingCall;
import static io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall;

/**
 */
@javax.annotation.Generated(
    value = "by gRPC proto compiler (version 1.22.1)",
    comments = "Source: firefly.proto")
public final class FireflyGrpc {

  private FireflyGrpc() {}

  public static final String SERVICE_NAME = "firefly.Firefly";

  // Static method descriptors that strictly reflect the proto.
  private static volatile io.grpc.MethodDescriptor<com.example.firefly.grpc.PhaseRequest,
      com.example.firefly.grpc.PhaseResponse> getGetPhaseMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "GetPhase",
      requestType = com.example.firefly.grpc.PhaseRequest.class,
      responseType = com.example.firefly.grpc.PhaseResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.example.firefly.grpc.PhaseRequest,
      com.example.firefly.grpc.PhaseResponse> getGetPhaseMethod() {
    io.grpc.MethodDescriptor<com.example.firefly.grpc.PhaseRequest, com.example.firefly.grpc.PhaseResponse> getGetPhaseMethod;
    if ((getGetPhaseMethod = FireflyGrpc.getGetPhaseMethod) == null) {
      synchronized (FireflyGrpc.class) {
        if ((getGetPhaseMethod = FireflyGrpc.getGetPhaseMethod) == null) {
          FireflyGrpc.getGetPhaseMethod = getGetPhaseMethod = 
              io.grpc.MethodDescriptor.<com.example.firefly.grpc.PhaseRequest, com.example.firefly.grpc.PhaseResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(
                  "firefly.Firefly", "GetPhase"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.example.firefly.grpc.PhaseRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.example.firefly.grpc.PhaseResponse.getDefaultInstance()))
                  .setSchemaDescriptor(new FireflyMethodDescriptorSupplier("GetPhase"))
                  .build();
          }
        }
     }
     return getGetPhaseMethod;
  }

  /**
   * Creates a new async stub that supports all call types for the service
   */
  public static FireflyStub newStub(io.grpc.Channel channel) {
    return new FireflyStub(channel);
  }

  /**
   * Creates a new blocking-style stub that supports unary and streaming output calls on the service
   */
  public static FireflyBlockingStub newBlockingStub(
      io.grpc.Channel channel) {
    return new FireflyBlockingStub(channel);
  }

  /**
   * Creates a new ListenableFuture-style stub that supports unary calls on the service
   */
  public static FireflyFutureStub newFutureStub(
      io.grpc.Channel channel) {
    return new FireflyFutureStub(channel);
  }

  /**
   */
  public static abstract class FireflyImplBase implements io.grpc.BindableService {

    /**
     */
    public void getPhase(com.example.firefly.grpc.PhaseRequest request,
        io.grpc.stub.StreamObserver<com.example.firefly.grpc.PhaseResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getGetPhaseMethod(), responseObserver);
    }

    @java.lang.Override public final io.grpc.ServerServiceDefinition bindService() {
      return io.grpc.ServerServiceDefinition.builder(getServiceDescriptor())
          .addMethod(
            getGetPhaseMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                com.example.firefly.grpc.PhaseRequest,
                com.example.firefly.grpc.PhaseResponse>(
                  this, METHODID_GET_PHASE)))
          .build();
    }
  }

  /**
   */
  public static final class FireflyStub extends io.grpc.stub.AbstractStub<FireflyStub> {
    private FireflyStub(io.grpc.Channel channel) {
      super(channel);
    }

    private FireflyStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected FireflyStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new FireflyStub(channel, callOptions);
    }

    /**
     */
    public void getPhase(com.example.firefly.grpc.PhaseRequest request,
        io.grpc.stub.StreamObserver<com.example.firefly.grpc.PhaseResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getGetPhaseMethod(), getCallOptions()), request, responseObserver);
    }
  }

  /**
   */
  public static final class FireflyBlockingStub extends io.grpc.stub.AbstractStub<FireflyBlockingStub> {
    private FireflyBlockingStub(io.grpc.Channel channel) {
      super(channel);
    }

    private FireflyBlockingStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected FireflyBlockingStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new FireflyBlockingStub(channel, callOptions);
    }

    /**
     */
    public com.example.firefly.grpc.PhaseResponse getPhase(com.example.firefly.grpc.PhaseRequest request) {
      return blockingUnaryCall(
          getChannel(), getGetPhaseMethod(), getCallOptions(), request);
    }
  }

  /**
   */
  public static final class FireflyFutureStub extends io.grpc.stub.AbstractStub<FireflyFutureStub> {
    private FireflyFutureStub(io.grpc.Channel channel) {
      super(channel);
    }

    private FireflyFutureStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected FireflyFutureStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new FireflyFutureStub(channel, callOptions);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<com.example.firefly.grpc.PhaseResponse> getPhase(
        com.example.firefly.grpc.PhaseRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getGetPhaseMethod(), getCallOptions()), request);
    }
  }

  private static final int METHODID_GET_PHASE = 0;

  private static final class MethodHandlers<Req, Resp> implements
      io.grpc.stub.ServerCalls.UnaryMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ServerStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ClientStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.BidiStreamingMethod<Req, Resp> {
    private final FireflyImplBase serviceImpl;
    private final int methodId;

    MethodHandlers(FireflyImplBase serviceImpl, int methodId) {
      this.serviceImpl = serviceImpl;
      this.methodId = methodId;
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public void invoke(Req request, io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        case METHODID_GET_PHASE:
          serviceImpl.getPhase((com.example.firefly.grpc.PhaseRequest) request,
              (io.grpc.stub.StreamObserver<com.example.firefly.grpc.PhaseResponse>) responseObserver);
          break;
        default:
          throw new AssertionError();
      }
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public io.grpc.stub.StreamObserver<Req> invoke(
        io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        default:
          throw new AssertionError();
      }
    }
  }

  private static abstract class FireflyBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoFileDescriptorSupplier, io.grpc.protobuf.ProtoServiceDescriptorSupplier {
    FireflyBaseDescriptorSupplier() {}

    @java.lang.Override
    public com.google.protobuf.Descriptors.FileDescriptor getFileDescriptor() {
      return com.example.firefly.grpc.FireflyOuterClass.getDescriptor();
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.ServiceDescriptor getServiceDescriptor() {
      return getFileDescriptor().findServiceByName("Firefly");
    }
  }

  private static final class FireflyFileDescriptorSupplier
      extends FireflyBaseDescriptorSupplier {
    FireflyFileDescriptorSupplier() {}
  }

  private static final class FireflyMethodDescriptorSupplier
      extends FireflyBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoMethodDescriptorSupplier {
    private final String methodName;

    FireflyMethodDescriptorSupplier(String methodName) {
      this.methodName = methodName;
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.MethodDescriptor getMethodDescriptor() {
      return getServiceDescriptor().findMethodByName(methodName);
    }
  }

  private static volatile io.grpc.ServiceDescriptor serviceDescriptor;

  public static io.grpc.ServiceDescriptor getServiceDescriptor() {
    io.grpc.ServiceDescriptor result = serviceDescriptor;
    if (result == null) {
      synchronized (FireflyGrpc.class) {
        result = serviceDescriptor;
        if (result == null) {
          serviceDescriptor = result = io.grpc.ServiceDescriptor.newBuilder(SERVICE_NAME)
              .setSchemaDescriptor(new FireflyFileDescriptorSupplier())
              .addMethod(getGetPhaseMethod())
              .build();
        }
      }
    }
    return result;
  }
}
