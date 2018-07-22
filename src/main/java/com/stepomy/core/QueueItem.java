package com.stepomy.core;

public class QueueItem<T> {
    private long creationTime;
    private T item;
    QueueItem (T item) {
        this.item = item;
        this.creationTime = System.currentTimeMillis();
    }

    public long getCreationTime() {
        return this.creationTime;
    }
    public T getItem() {
        return item;
    }

    public boolean isExpired() {
        return System.currentTimeMillis() - getCreationTime() > Configuration.MAX_PROCESSING_TIME;
    }

    public void printExpired() {
        System.out.println("Message Expired: " + item);
    }
}
