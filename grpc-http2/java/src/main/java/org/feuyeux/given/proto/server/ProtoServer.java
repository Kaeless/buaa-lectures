package org.feuyeux.given.proto.server;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import io.grpc.Server;
import io.grpc.ServerBuilder;
import io.grpc.stub.StreamObserver;
import lombok.extern.slf4j.Slf4j;
import org.feuyeux.given.proto.LandingServiceGrpc;
import org.feuyeux.given.proto.TalkRequest;
import org.feuyeux.given.proto.TalkResponse;
import org.feuyeux.given.proto.utils.ProtoUtil;

@Slf4j
public class ProtoServer {
    private final Server server;

    public ProtoServer(final int port) throws IOException {
        this.server = ServerBuilder.forPort(port).addService(new LandingServiceImpl()).build();
        start();
    }

    public static void main(String[] args) throws InterruptedException, IOException {
        ProtoServer server = new ProtoServer(50061);
        server.blockUntilShutdown();
    }

    private void start() throws IOException {
        server.start();
        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            log.warn("shutting down Google RPC Server since JVM is shutting down");
            ProtoServer.this.stop();
            log.warn("Google RPC Server shut down");
        }));
        log.info("Google RPC Server is launched.");
    }

    public void blockUntilShutdown() throws InterruptedException {
        if (server != null) {
            server.awaitTermination();
        }
    }

    public void stop() {
        server.shutdown();
    }

    static class LandingServiceImpl extends LandingServiceGrpc.LandingServiceImplBase {
        @Override
        public void talk(TalkRequest request, StreamObserver<TalkResponse> responseObserver) {
            log.debug("REQUEST:{}", request.toString());
            final TalkResponse response = ProtoUtil.buildResponse(request);
            log.debug("RESPONSE:{}", response.toString());

            responseObserver.onNext(response);
            responseObserver.onCompleted();
        }

        @Override
        public void talkOneAnswerMore(TalkRequest request, StreamObserver<TalkResponse> responseObserver) {
            final List<TalkResponse> responses = ProtoUtil.buildResponses(request);
            responses.forEach(responseObserver::onNext);
            responseObserver.onCompleted();
        }

        @Override
        public StreamObserver<TalkRequest> talkMoreAnswerOne(StreamObserver<TalkResponse> responseObserver) {
            final List<TalkRequest> talkRequests = new ArrayList<>();
            return new StreamObserver<TalkRequest>() {
                @Override
                public void onNext(TalkRequest request) {
                    talkRequests.add(request);
                }

                @Override
                public void onError(Throwable t) {
                    log.error("talkBidirectional onError");
                }

                @Override
                public void onCompleted() {
                    responseObserver.onNext(ProtoUtil.buildResponse(talkRequests));
                    responseObserver.onCompleted();
                }
            };
        }

        @Override
        public StreamObserver<TalkRequest> talkBidirectional(StreamObserver<TalkResponse> responseObserver) {
            return new StreamObserver<TalkRequest>() {
                @Override
                public void onNext(TalkRequest request) {
                    responseObserver.onNext(ProtoUtil.buildResponse(request));
                }

                @Override
                public void onError(Throwable t) {
                    log.error("talkBidirectional onError");
                }

                @Override
                public void onCompleted() {
                    responseObserver.onCompleted();
                }
            };
        }
    }
}
