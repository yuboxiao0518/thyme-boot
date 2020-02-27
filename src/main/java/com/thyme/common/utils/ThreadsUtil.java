package com.thyme.common.utils;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.*;
@Slf4j
public class ThreadsUtil {

    public static void createThreadPool() {
        int corePoolSize = 2;
        int maximumPoolSize = Runtime.getRuntime().availableProcessors();
        long keepAliveTime = 2L;
        TimeUnit unit = TimeUnit.SECONDS;
        BlockingQueue<Runnable> workQueue = new LinkedBlockingDeque<>(3);
        ThreadFactory factory = Executors.defaultThreadFactory();
        ThreadPoolExecutor.CallerRunsPolicy callerRunsPolicy = new ThreadPoolExecutor.CallerRunsPolicy();
        ThreadPoolExecutor poolExecutor = new ThreadPoolExecutor(corePoolSize, maximumPoolSize, keepAliveTime, unit, workQueue, factory, callerRunsPolicy);
        poolExecutor.execute(()->{
            System.out.println(Thread.currentThread().getName());
        });
    }

    public static void main(String[] args) {
        System.out.println(Thread.currentThread().getName());
        createThreadPool();
    }
}
