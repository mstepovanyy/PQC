package com.stepomy.core;

import com.stepomy.core.consumer.Consumer;
import com.stepomy.core.producer.Producer;

public class AppManager {

    public void run () {
        Consumer consumer = new Consumer();
        Producer producer = new Producer();

        consumer.processMessage();
        producer.generateMessages();
    }
}
