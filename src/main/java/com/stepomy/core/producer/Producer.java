package com.stepomy.core.producer;

import com.stepomy.core.Configuration;

import java.util.Timer;


public class Producer {

    Timer scheduler;

    public void generateMessages() {
        System.out.println("Producer:generateMessages");
        startScheduler();
    }

    void startScheduler() {
        if (!isAlive()) {
            scheduler = new Timer();
            scheduler.schedule(new MessagePublisher(), 0, Configuration.PRODUCER_ITERATION_TIME);
        }
    }

    boolean isAlive() {
        return scheduler != null;
    }
}
