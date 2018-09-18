package org.feuyeux.given.proto.server;

import java.io.IOException;

import io.grpc.Server;
import io.grpc.ServerBuilder;
import io.grpc.stub.StreamObserver;
import lombok.extern.slf4j.Slf4j;
import org.feuyeux.given.proto.LandingServiceGrpc;
import org.feuyeux.given.proto.TalkRequest;
import org.feuyeux.given.proto.TalkResponse;
import org.feuyeux.given.proto.utils.ProtoUtil;

/**
 * Created by erichan feuyeux
 * on 16/8/22
 */
@Slf4j
public class ProtoServer {
    private Server server;

    public ProtoServer(final int port) {
        try {
            server = ServerBuilder.forPort(port)
                .addService(new LandingServiceImpl())
                .build()
                .start();
            Runtime.getRuntime().addShutdownHook(new Thread() {
                @Override
                public void run() {
                    log.warn("shutting down Google RPC Server since JVM is shutting down");
                    ProtoServer.this.stop();
                    log.warn("Google RPC Server shut down");
                }
            });
            log.info("Google RPC Server is launched.");
        } catch (IOException e) {
            log.error("Google RPC Server is failed to launch.", e);
        }
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
    }

    public static void main(String[] args) throws InterruptedException {
        ProtoServer server = new ProtoServer(50061);
        server.blockUntilShutdown();
    }
}
