package org.feuyeux.given.proto.client;

import java.util.concurrent.TimeUnit;

import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.feuyeux.given.proto.LandingServiceGrpc;
import org.feuyeux.given.proto.TalkRequest;
import org.feuyeux.given.proto.TalkResponse;
import org.feuyeux.given.proto.TalkResult;
import org.feuyeux.given.proto.utils.ProtoUtil;

/**
 * Created by erichan feuyeux
 * on 16/8/24
 */
public class ProtoClient {
    private static final Logger log = LogManager.getLogger(ProtoClient.class);
    private ManagedChannel channel;
    private LandingServiceGrpc.LandingServiceBlockingStub landingStub;

    public ProtoClient(String host, int port) {
        channel = ManagedChannelBuilder.forAddress(host, port).usePlaintext(true).build();
        landingStub = LandingServiceGrpc.newBlockingStub(channel);
    }

    public TalkResponse talk(TalkRequest talkRequest) {
        return landingStub.talk(talkRequest);
    }

    public java.util.Iterator<org.feuyeux.given.proto.TalkChunk> talkChunk(com.google.protobuf.Empty request) {
        return landingStub.talkChunk(request);
    }

    public void close() {
        try {
            channel.shutdown().awaitTermination(5, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            log.error("", e);
        }
    }

    public static void main(String[] args) {
        ProtoClient protoClient = new ProtoClient("localhost", 50061);
        TalkResponse talkResponse = protoClient.talk(ProtoUtil.buildRequest());
        final TalkResult result = talkResponse.getResults(0);
        log.info("status={}", talkResponse.getStatus());
        log.info("FIRST LINE: id={}, type={}, kv={}",
            result.getId(),
            result.getType(),
            result.getKvMap());
        log.info("RESPONSE:{}", talkResponse);
        protoClient.close();
    }
}
