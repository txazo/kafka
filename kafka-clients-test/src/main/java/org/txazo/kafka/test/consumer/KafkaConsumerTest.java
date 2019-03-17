package org.txazo.kafka.test.consumer;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.junit.Test;

import java.time.Duration;

public class KafkaConsumerTest {

    @Test
    public void test() {
        KafkaConsumer kafkaConsumer = new KafkaConsumer();
        while (true) {
            ConsumerRecords<String, String> records = kafkaConsumer.getConsumer().poll(Duration.ofMillis(100));
            for (ConsumerRecord<String, String> record : records) {
                System.out.println(record);
            }
        }
    }

}
