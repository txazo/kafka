package org.txazo.kafka.test.producer;

import org.apache.kafka.clients.producer.ProducerRecord;
import org.junit.Test;
import org.txazo.kafka.test.ConfigConstants;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class KafkaProducerTest {

    @Test
    public void test() {
        int producerThreads = 20;
        KafkaProducer kafkaProducer = new KafkaProducer();
        CompletableFuture[] tasks = new CompletableFuture[producerThreads];
        ExecutorService threadPool = Executors.newFixedThreadPool(producerThreads);
        for (int i = 0; i < tasks.length; i++) {
            final int j = i;
            tasks[i] = CompletableFuture.runAsync(() -> {
                int sequence = 1;
                while (true) {
                    String key = String.format("%02d-%08d", j + 1, sequence++);
                    kafkaProducer.getProducer().send(new ProducerRecord<>(ConfigConstants.TOPIC, key, key));
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }, threadPool);
        }
        CompletableFuture.allOf(tasks).join();
    }

}
