package org.feuyeux.given.proto.utils;

import org.feuyeux.given.proto.ResultType;
import org.feuyeux.given.proto.TalkRequest;
import org.feuyeux.given.proto.TalkResponse;
import org.feuyeux.given.proto.TalkResult;

import java.util.HashMap;

/**
 * Created by erichan feuyeux
 * on 16/8/24
 */
public class ProtoUtil {
    public static TalkRequest buildRequest() {
        return TalkRequest.newBuilder()
                .setMeta("user=eric")
                .setData("query=ai,from=0,size=1000,order=x,sort=y")
                .build();
    }

    public static TalkResponse buildResponse(TalkRequest request) {
        TalkResponse.Builder response = TalkResponse.newBuilder();
        response.setStatus(200);
        for (int i = 0; i < 10; i++) {
            response.addResults(getTalkResult(request));
        }
        return response.build();
    }

    private static TalkResult getTalkResult(TalkRequest request) {
        HashMap<String, String> kv = new HashMap<>();
        kv.put("request-data", request.getData());
        kv.put("request-meta", request.getMeta());
        kv.put("timestamp", String.valueOf(System.nanoTime()));
        return TalkResult.newBuilder()
                .setId(System.nanoTime())
                .setType(ResultType.SEARCH)
                .putAllKv(kv)
                .build();
    }
}
