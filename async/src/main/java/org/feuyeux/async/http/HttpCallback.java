package org.feuyeux.async.http;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

/**
 * @author 六翁 lu.hl@alibaba-inc.com
 * @date 2019/10/23
 */

public interface HttpCallback extends Callback {

    /**
     * @param call
     * @param e
     */
    @Override
    default void onFailure(Call call, IOException e) {

    }

    /**
     * @param call
     * @param response
     * @throws IOException
     */
    @Override
    default void onResponse(Call call, Response response) throws IOException {
        try {
            onResult(response.body().string());
        } finally {
            response.close();
        }
    }

    /**
     * @param result
     */
    default void onResult(String result) {}
}
