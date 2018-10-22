package org.feuyeux.given.proto.server;

import java.io.IOException;

import lombok.extern.slf4j.Slf4j;
import org.feuyeux.given.proto.TalkRequest;
import org.feuyeux.given.proto.TalkResponse;
import org.feuyeux.given.proto.client.ProtoClient;
import org.feuyeux.given.proto.utils.ProtoUtil;
import org.junit.Assert;
import org.junit.Test;

/**
 * Created by erichan feuyeux
 * on 16/8/22
 */
@Slf4j
public class ProtoTest {
    //@Test
    public void testProto() throws InterruptedException, IOException {
        ProtoServer protoServer = new ProtoServer(17002);
        ProtoClient protoClient = new ProtoClient("localhost", 17002);
        TalkRequest talkRequest = ProtoUtil.buildRequest();
        log.info("REQUEST:{}", talkRequest);
        TalkResponse talkResponse = protoClient.talk(talkRequest);

        Assert.assertTrue(talkResponse.getStatus() == 200);
        log.info("RESPONSE:{}", talkResponse);

        protoClient.shutdown();
        protoServer.stop();
    }
}
