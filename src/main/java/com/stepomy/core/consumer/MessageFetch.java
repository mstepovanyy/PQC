package com.stepomy.core.consumer;

import com.stepomy.core.Configuration;
import com.stepomy.core.Queue;
import com.stepomy.core.QueueItem;

import java.util.TimerTask;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.stream.IntStream;

public class MessageFetch extends TimerTask {
    private LinkedBlockingQueue<QueueItem> queue;

    public MessageFetch(LinkedBlockingQueue<QueueItem> queue) {
        this.queue = queue;
    }

    @Override
    public void run() {

        IntStream.range(0, Configuration.CONSUMER_POOLING_MESSAGES).forEach(index-> {
            QueueItem item = Queue.getInstance().poll();
            while(item.isExpired()) {
                item.printExpired();
                item = Queue.getInstance().poll();
            }
            try {
                queue.put(item);
            } catch (InterruptedException e) { return; }
        });
        System.out.println("END Message fetch " + queue.size() + " , " + Queue.getInstance().size());
    }
}