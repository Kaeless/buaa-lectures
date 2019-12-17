package org.feuyeux.async.http;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;
import okhttp3.ResponseBody;

import java.io.IOException;

/**
 * @author 六翁 lu.hl@alibaba-inc.com
 * @date 2019/10/23
 */

@SuppressWarnings("NullableProblems")
public interface HttpCallback extends Callback {
    @Override
    default void onFailure(Call call, IOException e) {

    }

    @Override
    default void onResponse(Call call, Response response) throws IOException {
        try {
            ResponseBody body = response.body();
            onResult(body == null ? null : body.string());
        } finally {
            response.close();
        }
    }

    default void onResult(String result) {
    }
}
