package org.feuyeux.async.http;

import java.io.File;
import java.io.IOException;
import java.net.URLEncoder;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.TimeUnit;

import lombok.extern.slf4j.Slf4j;
import okhttp3.Cache;
import okhttp3.Call;
import okhttp3.ConnectionPool;
import okhttp3.FormBody;
import okhttp3.Headers;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.ResponseBody;

/**
 * @author feuyeux
 */
@Slf4j
public class HttpClientCoon {
    private static final MediaType JSON_CONTENT_TYPE = MediaType.parse("application/json; charset=utf-8");
    private static final MediaType STREAM_CONTENT_TYPE = MediaType.parse("application/octet-stream");
    private static final MediaType ZIP_CONTENT_TYPE = MediaType.parse("application/zip");
    private static final String HTTP_PREFIX = "http://";
    private static final String HTTPS_PREFIX = "https://";
    private static final int MAX_IDLE_CONNECTION = 5;
    private static final int KEEP_ALIVE_DURATION = 5;
    private static final long CONNECT_TIMEOUT = 5000;
    private static final long READ_TIMEOUT = 5000;
    private static final long WRITE_TIMEOUT = 5000;
    private OkHttpClient client;

    public HttpClientCoon() {
        init(CONNECT_TIMEOUT, READ_TIMEOUT, WRITE_TIMEOUT);
    }

    public HttpClientCoon(long connectTimeout, long readTimeout, long writeTimeout) {
        init(connectTimeout, readTimeout, writeTimeout);
    }

    /**
     * startup client
     *
     * @param connectTimeout
     * @param readTimeout
     * @param writeTimeout
     */
    private void init(long connectTimeout, long readTimeout, long writeTimeout) {
        log.info("HttpClientCoon start now.");
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        ConnectionPool connectionPool = new ConnectionPool(MAX_IDLE_CONNECTION, KEEP_ALIVE_DURATION, TimeUnit.MINUTES);

        client = builder
            .connectTimeout(connectTimeout, TimeUnit.MILLISECONDS)
            .readTimeout(readTimeout, TimeUnit.MILLISECONDS)
            .writeTimeout(writeTimeout, TimeUnit.MILLISECONDS)
            .connectionPool(connectionPool)
            .build();
    }

    /**
     * destroy client
     * https://square.github.io/okhttp/4.x/okhttp/okhttp3/-ok-http-client
     *
     * @throws IOException
     * @throws InterruptedException
     */
    public void destroy() throws IOException, InterruptedException {
        log.info("HttpClientCoon shutdown now.");

        final ExecutorService executorService = client.dispatcher().executorService();
        executorService.shutdown();
        final ConnectionPool connectionPool = client.connectionPool();
        connectionPool.evictAll();

        final Cache cache = client.cache();
        if (cache != null && !cache.isClosed()) {
            cache.close();
        }
        while (!executorService.isShutdown()) {
            log.info("waiting for threads shutdown");
            TimeUnit.MILLISECONDS.sleep(100);
        }
    }
    /* GET */

    public void get(String url, HttpCallback callback) {
        Request request = new Request.Builder().url(url).build();
        final Call call = client.newCall(request);
        call.enqueue(callback);
    }

    public String get(String url) throws Exception {
        Request request = new Request.Builder().url(url).build();
        try (Response response = client.newCall(request).execute()) {
            return body(response);
        }
    }

    private String body(Response response) throws Exception {
        return response.body().string();
    }

    public String get(String url, Map<String, String> params) throws Exception {
        StringBuilder sb = new StringBuilder();
        if (url.startsWith(HTTP_PREFIX) || url.startsWith(HTTPS_PREFIX)) {
            sb.append(url).append("?");
        } else {
            sb.append(HTTP_PREFIX).append(url).append("?");
        }
        if (params != null && !params.isEmpty()) {
            for (Map.Entry<String, String> entry : params.entrySet()) {
                String value = URLEncoder.encode(entry.getValue(), "utf-8");
                sb.append(entry.getKey()).append("=").append(value).append("&");
            }
        }
        String url0 = sb.deleteCharAt(sb.length() - 1).toString();
        return get(url0);
    }

    public String getWithHeader(String url, Map<String, String> headMap) throws Exception {
        Headers headers = Headers.of(headMap);
        Request request = new Request.Builder()
            .url(url)
            .headers(headers)
            .build();
        log.info("{}", request);
        try (Response response = client.newCall(request).execute()) {
            return response.body() != null ? response.body().string() : null;
        }
    }

    public String getWithHeader(String url, Map<String, String> headMap, Map<String, String> paramMap)
        throws Exception {
        StringBuilder sb = new StringBuilder();
        if (url.startsWith(HTTP_PREFIX) || url.startsWith(HTTPS_PREFIX)) {
            sb.append(url).append("?");
        } else {
            sb.append(HTTP_PREFIX).append(url).append("?");
        }
        if (paramMap != null && !paramMap.isEmpty()) {
            for (Map.Entry<String, String> entry : paramMap.entrySet()) {
                sb.append(entry.getKey()).append("=").append(entry.getValue()).append("&");
            }
        }
        String url0 = sb.deleteCharAt(sb.length() - 1).toString();

        return getWithHeader(url0, headMap);
    }

    public String post(String url) throws Exception {
        RequestBody body = RequestBody.create(null, new byte[0]);
        return post(url, null, body);
    }

    public String post(String url, Map<String, String> params) throws Exception {
        FormBody.Builder builder = new FormBody.Builder();
        params.forEach(builder::add);
        RequestBody body = builder.build();
        return post(url, null, body);
    }

    public String post(String url, String payload) throws Exception {
        return postWithHeader(url, null, payload);
    }

    public byte[] post(String url, byte[] bytes) throws Exception {
        RequestBody requestBody = RequestBody.create(STREAM_CONTENT_TYPE, bytes);
        Request request = new Request.Builder().url(url).post(requestBody).build();

        Response response = client.newCall(request).execute();
        if (response.isSuccessful()) {
            return response.body() != null ? response.body().bytes() : new byte[0];
        } else {
            return null;
        }
    }

    public String postZipFile(String url, Map<String, String> headerMap, String filepath) throws Exception {
        File zipFile = new File(filepath);
        RequestBody body = RequestBody.create(ZIP_CONTENT_TYPE, zipFile);
        RequestBody requestBody = new MultipartBody.Builder()
            .setType(MultipartBody.FORM)
            .addFormDataPart("zipFile", zipFile.getName(), body).build();
        return post(url, headerMap, requestBody);
    }

    public String postWithHeader(String url, Map<String, String> headerMap, String payload) throws Exception {
        RequestBody body = RequestBody.create(JSON_CONTENT_TYPE, payload == null ? "" : payload);
        return post(url, headerMap, body);
    }

    public String postWithHeader(String url, Map<String, String> headerMap, Map<String, String> paramMap)
        throws Exception {
        FormBody.Builder builder = new FormBody.Builder();
        paramMap.forEach(builder::add);
        RequestBody body = builder.build();
        return post(url, headerMap, body);
    }

    public String postWithQuery(String url, Map<String, String> params) throws Exception {
        StringBuilder sb = new StringBuilder();
        if (url.startsWith(HTTP_PREFIX) || url.startsWith(HTTPS_PREFIX)) {
            sb.append(url).append("?");
        } else {
            sb.append(HTTP_PREFIX).append(url).append("?");
        }
        if (params != null && !params.isEmpty()) {
            for (Map.Entry<String, String> entry : params.entrySet()) {
                sb.append(entry.getKey()).append("=").append(entry.getValue()).append("&");
            }
        }
        String url0 = sb.deleteCharAt(sb.length() - 1).toString();
        return post(url0);
    }

    private String post(String url, Map<String, String> headerMap, RequestBody requestBody) throws Exception {
        Request.Builder builder = new Request.Builder()
            .url(url)
            .post(requestBody);

        if (headerMap != null) {
            headerMap.forEach(builder::header);
        }

        Request request = builder.build();
        try (Response response = client.newCall(request).execute()) {
            if (response.isSuccessful()) {
                ResponseBody body = response.body();
                return body == null ? "" : body.string();
            } else {
                ResponseBody body = response.body();
                return body == null ? response.message() : body.string();
            }
        }
    }

    public String put(String url) throws Exception {
        RequestBody requestBody = RequestBody.create(null, new byte[0]);

        Request request = new Request.Builder()
            .url(url)
            .put(requestBody)
            .build();

        try (Response response = client.newCall(request).execute()) {
            ResponseBody responseBody = response.body();
            return responseBody == null ? "" : responseBody.string();
        }
    }

    public String put(String url, String payload) throws Exception {
        RequestBody body = RequestBody.create(JSON_CONTENT_TYPE, payload);
        Request request = new Request.Builder()
            .url(url)
            .put(body)
            .build();

        try (Response response = client.newCall(request).execute()) {
            ResponseBody responseBody = response.body();
            return responseBody == null ? "" : responseBody.string();
        }
    }

    public String put(String url, Map<String, String> params) throws Exception {
        FormBody.Builder builder = new FormBody.Builder();
        params.forEach(builder::add);
        RequestBody requestBody = builder.build();

        Request request = new Request.Builder()
            .url(url)
            .put(requestBody)
            .build();

        try (Response response = client.newCall(request).execute()) {
            ResponseBody responseBody = response.body();
            return responseBody == null ? "" : responseBody.string();
        }
    }

    public String delete(String url) throws Exception {
        Request request = new Request.Builder()
            .url(url)
            .delete()
            .build();

        try (Response response = client.newCall(request).execute()) {
            ResponseBody responseBody = response.body();
            return responseBody == null ? "" : responseBody.string();
        }
    }

    public String delete(String url, String payload) throws Exception {
        RequestBody body = RequestBody.create(JSON_CONTENT_TYPE, payload);
        Request request = new Request.Builder()
            .url(url)
            .delete(body)
            .build();

        try (Response response = client.newCall(request).execute()) {
            ResponseBody responseBody = response.body();
            return responseBody == null ? "" : responseBody.string();
        }
    }

    public String delete(String url, Map<String, String> params) throws Exception {
        FormBody.Builder builder = new FormBody.Builder();
        params.forEach(builder::add);
        RequestBody requestBody = builder.build();

        Request request = new Request.Builder()
            .url(url)
            .delete(requestBody)
            .build();

        try (Response response = client.newCall(request).execute()) {
            ResponseBody responseBody = response.body();
            return responseBody == null ? "" : responseBody.string();
        }
    }

}
