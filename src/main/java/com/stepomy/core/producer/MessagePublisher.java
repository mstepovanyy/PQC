package com.stepomy.core.producer;

import com.stepomy.core.Configuration;
import com.stepomy.core.Queue;

import java.time.LocalDateTime;
import java.util.TimerTask;
import java.util.stream.IntStream;

public class MessagePublisher extends TimerTask {
    long messageID = 0;

    @Override
    public void run() {
        IntStream.range(0, Configuration.PRODUCER_ITERATION_MESSAGES)
                .parallel()
                .forEach(index -> Queue.getInstance().add(getMessage())
                );

    }

    String getMessage() {
        messageID += 1;
        StringBuilder message = new StringBuilder();
        message.append("Thread ID: ");
        message.append(Thread.currentThread().getId());
        message.append(", Message ID: ");
        message.append(messageID);
        message.append(", generated in: ");
        message.append(LocalDateTime.now());
        return message.toString();
    }
}
