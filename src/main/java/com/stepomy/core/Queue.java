package com.stepomy.core;

import java.util.Collection;
import java.util.concurrent.ConcurrentLinkedQueue;

public class Queue <T>{
    private ConcurrentLinkedQueue<QueueItem<T>> queue;
    private static Queue instance = null;

    private Queue() {
        queue = new ConcurrentLinkedQueue<QueueItem<T>>();
    }

    public static Queue getInstance() {
        if (instance == null) {
            synchronized (Queue.class) {
                if (instance == null) {
                    instance = new Queue();
                }
            }
        }
        return instance;
    }

    public boolean add(T item) {
        return queue.add(new QueueItem<T>(item));
    }


    public int size() {
        return queue.size();
    }

    public QueueItem<T> poll() {
        QueueItem<T> item = queue.poll();
        while(item == null) {
            item = queue.poll();
            try {
                Thread.sleep(1);
            } catch (InterruptedException e) { continue; }
        }
        return item;
    }
}
