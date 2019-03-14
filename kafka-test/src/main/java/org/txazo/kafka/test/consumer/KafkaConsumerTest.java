package org.txazo.kafka.test.consumer;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.time.Duration;
import java.util.Collections;
import java.util.Properties;

public class KafkaConsumerTest {

    private KafkaConsumer<String, String> consumer;

    @Before
    public void init() {
        Properties props = new Properties();
        props.put("bootstrap.servers", "192.168.94.20:9091,192.168.94.20:9092,192.168.94.20:9093");
        props.put("group.id", "group-test");
        props.put("enable.auto.commit", true);
        props.put("auto.commit.interval.ms", 5000);
        props.put("max.partition.fetch.bytes", 1024 * 1024);
        props.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        props.put("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        consumer = new KafkaConsumer<>(props);
        consumer.subscribe(Collections.singletonList("topic-test"));
    }

    @Test
    public void test() {
        while (true) {
            ConsumerRecords<String, String> records = consumer.poll(Duration.ofMillis(100));
            for (ConsumerRecord<String, String> record : records) {
                System.err.println(record);
            }
        }
    }

    @After
    public void close() {
        consumer.close();
    }

}
