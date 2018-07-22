package com.stepomy.core.consumer;

import com.stepomy.core.QueueItem;

import java.util.concurrent.LinkedBlockingQueue;

public class MessageProcess implements Runnable {
    private LinkedBlockingQueue<QueueItem> queue;

    public MessageProcess (LinkedBlockingQueue<QueueItem> queue) {
        this.queue = queue;
    }

    @Override
    public void run() {
        while (true) {
            try {
                QueueItem message = queue.take();
                process(message);
            } catch(InterruptedException e) {
                break;
            }
        }
        System.out.println("END Message process");
    }

    private void process(QueueItem message) {
        try {
            if (!message.isExpired()) {
                System.out.println(message.getItem());
            } else {
                message.printExpired();
            }
        } catch (Exception e) {}
    }
}