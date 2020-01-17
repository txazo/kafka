package org.txazo.kafka.test.producer;

import org.apache.kafka.clients.producer.Callback;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.txazo.kafka.test.ConfigConstants;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class KafkaProducerTest {

    private static final Logger LOGGER = LoggerFactory.getLogger(KafkaProducerTest.class);

    @Test
    public void test() throws Exception {
        int producerThreads = 1;
        int maxMessagesPerThread = 1;
        KafkaProducer kafkaProducer = new KafkaProducer();
        CompletableFuture[] tasks = new CompletableFuture[producerThreads];
        ExecutorService threadPool = Executors.newFixedThreadPool(producerThreads);
        for (int i = 0; i < tasks.length; i++) {
            final int j = i;
            tasks[i] = CompletableFuture.runAsync(() -> {
                int sequence = 1;
                while (sequence <= maxMessagesPerThread) {
                    String key = String.format("%02d-%08d", j + 1, sequence++);
                    String value = new String(new byte[1024 * 16 + 1]);
                    kafkaProducer.getProducer().send(new ProducerRecord<>(ConfigConstants.TOPIC, key, value),
                            new Callback() {

                                @Override
                                public void onCompletion(RecordMetadata metadata, Exception exception) {
                                    LOGGER.info("onCompletion topic={} partition={} offset={}", metadata.topic(), metadata.partition(), metadata.offset());
                                }

                            });
                    try {
                        Thread.sleep(10);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }, threadPool);
        }
        CompletableFuture.allOf(tasks).join();
        System.in.read();
    }

}
