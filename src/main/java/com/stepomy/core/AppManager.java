package com.stepomy.core;

import com.stepomy.core.consumer.Consumer;
import com.stepomy.core.producer.Producer;

/**
 * TODO: need to handle cases with crash producer of consumer.
 */
public class AppManager {

    public void run () {
        Consumer consumer = new Consumer();
        Producer producer = new Producer();

        consumer.processMessage();
        producer.generateMessages();
    }
}
