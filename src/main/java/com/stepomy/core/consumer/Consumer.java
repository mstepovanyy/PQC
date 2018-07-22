package com.stepomy.core.consumer;


import com.stepomy.core.Configuration;
import com.stepomy.core.QueueItem;

import java.util.Timer;
import java.util.concurrent.*;
import java.util.stream.IntStream;

public class Consumer {
    private LinkedBlockingQueue<QueueItem> queue = new LinkedBlockingQueue<>(Configuration.CONSUMER_WORKERS);
    private Timer messageScheduler;
    private ExecutorService executorService;

    public void processMessage() {
        System.out.println("Consumer:processMessage");
        startMessageScheduler();
        startMessageProcess();

    }

    void startMessageScheduler() {
        messageScheduler = new Timer();
        messageScheduler.schedule(new MessageFetch(queue), 0, Configuration.CONSUMER_POOLING_TIME);
    }

    void startMessageProcess() {
        executorService = Executors.newFixedThreadPool(Configuration.CONSUMER_WORKERS);
        IntStream
                .range(0, Configuration.CONSUMER_WORKERS)
                .forEach( it -> executorService.submit(new MessageProcess(queue)));
    }

}
