package org.feuyeux.async.http;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.util.concurrent.TimeUnit;

/**
 * @author feuyeux
 */
@Slf4j
public class TestHttpClientCoon {
    private static final String url = "http://localhost:8080/customers/1";
    private static final HttpClientCoon httpClientCoon = new HttpClientCoon();

    @Test
    public void testAsyncHttpGet() throws Exception {
        DefaultHttpCallback callback = new DefaultHttpCallback();
        for (int i = 0; i < 10000; i++) {
            httpClientCoon.get(url, callback);
            String result = callback.get(5, TimeUnit.SECONDS);
            log.info(result);
        }
    }

    @Test
    public void testSyncHttpGet() throws Exception {
        String result = httpClientCoon.get(url);
        log.info(result);
    }
}
