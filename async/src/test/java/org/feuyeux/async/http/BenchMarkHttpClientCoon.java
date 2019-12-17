package org.feuyeux.async.http;

import lombok.extern.slf4j.Slf4j;
import okhttp3.Call;
import okhttp3.Response;
import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.infra.Blackhole;
import org.openjdk.jmh.results.format.ResultFormatType;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

/**
 * benchmark test
 * <p>
 * * http://deepoove.com/jmh-visual-chart/
 * * https://nilskp.github.io/jmh-charts/
 *
 * @author feuyeux
 */
@Slf4j
@BenchmarkMode({Mode.Throughput})
@OutputTimeUnit(TimeUnit.MILLISECONDS)
@Warmup(iterations = 3, time = 5000, timeUnit = TimeUnit.MILLISECONDS)
@Measurement(iterations = 10, time = 500, timeUnit = TimeUnit.MILLISECONDS)
@Threads(2)
@Fork(value = 1,
        jvmArgs = {"-server", "-Xms2G", "-Xmx2G", "-XX:MaxDirectMemorySize=1m", "-XX:+UnlockDiagnosticVMOptions",
                "-XX:+UseG1GC"})
@State(Scope.Benchmark)
public class BenchMarkHttpClientCoon {
    @Param({
            "http://localhost:8080/customers/1",
            "http://localhost:8080/customers/2"
    })
    String url;
    private HttpClientCoon httpClientCoon;

    //cd ~/cooding/feuyeux/hello-rsocket/java/spring-boot-responder && gradle bootRun
    //cd ~/cooding/feuyeux/hello-rsocket/java/spring-boot-requester && gradle bootRun

    public static void main(String[] args) throws RunnerException {
        Options options = new OptionsBuilder()
                .include(BenchMarkHttpClientCoon.class.getSimpleName())
                .result("result.json")
                .resultFormat(ResultFormatType.JSON)
                .build();
        new Runner(options).run();
    }

    @Setup
    public void prepare() {
        log.info("============prepare=================");
        httpClientCoon = new HttpClientCoon();
    }

    @TearDown
    public void destroy() throws IOException, InterruptedException {
        log.info("============destroy=================");
        TimeUnit.SECONDS.sleep(3);
        httpClientCoon.destroy();
        log.info("\n====================================");
    }

    @Benchmark
    @CompilerControl(CompilerControl.Mode.DONT_INLINE)
    public String testAsyncDefaultHttpGet() throws Exception {
        DefaultHttpCallback callback = new DefaultHttpCallback();
        httpClientCoon.get(url, callback);
        final String s = callback.get(5, TimeUnit.SECONDS);
        log.debug("callback result:{}", s);
        return s;
    }

    @Benchmark
    @CompilerControl(CompilerControl.Mode.DONT_INLINE)
    public void testAsyncHttpGet(Blackhole bh) throws Exception {
        httpClientCoon.get(url, new HttpCallback() {
            @Override
            public void onFailure(Call call, IOException e) {
                log.error("", e);
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                try {
                    final String body = response.body().string();
                    bh.consume(body);
                    log.debug("callback result:{}", body);
                } finally {
                    response.close();
                }
            }
        });
    }

    @Benchmark
    @CompilerControl(CompilerControl.Mode.DONT_INLINE)
    public String testSyncHttpGet() throws Exception {
        return httpClientCoon.get(url);
    }
}
