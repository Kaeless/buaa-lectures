package org.feuyeux.async.http;

import java.util.concurrent.Callable;
import java.util.concurrent.Future;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import com.google.common.util.concurrent.ThreadFactoryBuilder;

/**
 * @author feuyeux
 */
public class ThreadPoolCoon<T> {

    private static final int CORE_POOL_SIZE = 50;
    private static final int QUEUE_SIZE = 50;
    private static final int MAX_POOL_SIZE = 500;
    private static final long KEEP_ALIVE_TIME = 5L;
    private static final String NAME_FORMAT = "engine-pool-%d";
    private static ThreadFactory threadFactory = new ThreadFactoryBuilder().setNameFormat(NAME_FORMAT).build();

    /**
     * 初始工人数量为CORE_POOL_SIZE，不能设置太小，否则创建线程消耗的时间会严重影响latency
     *
     * 工人一旦闲下来，就从任务队列里拿一个任务去执行
     * 任务队列满时(堆积的任务数=QUEUE_SIZE)，如果还没有闲置的工人，系统会雇佣新的工人(创建新的线程)
     * 要设定任务队列的QUEUE_SIZE，否则是Integer.MAX_VALUE，一方面会导致OOM，一方面会没有机会创建新的线程去处理任务，导致堆积时间过长
     * 要设定MAX_POOL_SIZE，否则是Integer.MAX_VALUE，会导致系统load增高，无法响应
     *
     * 工人闲置时间超过等待时间后，如果闲人数量>CORE_POOL_SIZE，这个差值数量的工人会被解雇
     */
    private static ThreadPoolExecutor executor = new ThreadPoolExecutor(
        /*最小工人数量*/
        CORE_POOL_SIZE,
        /*最大工人数量*/
        MAX_POOL_SIZE,
        /*等待时间 值*/
        KEEP_ALIVE_TIME,
        /*等待时间 单位*/
        TimeUnit.MINUTES,
        /*任务队列*/
        new LinkedBlockingQueue<>(QUEUE_SIZE),
        threadFactory,
        new ThreadPoolExecutor.AbortPolicy());

    public static <T> Future<?> submit(Runnable task) {
        return executor.submit(task);
    }

    public static <T> Future<T> submit(Callable<T> task) {
        return executor.submit(task);
    }
}
