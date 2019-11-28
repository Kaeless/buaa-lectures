package org.feuyeux.async.http;

import java.util.concurrent.CompletableFuture;

import lombok.extern.slf4j.Slf4j;

/**
 * Don't use this style, because from async to sync is slower than sync
 *
 * @author feuyeux
 */
@Slf4j
public class DefaultHttpCallback extends CompletableFuture<String> implements HttpCallback {

    @Override
    public void onResult(String result) {
        super.complete(result);
    }
}
