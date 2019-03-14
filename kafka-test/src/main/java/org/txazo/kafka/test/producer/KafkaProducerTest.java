package org.txazo.kafka.test.producer;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.Properties;

public class KafkaProducerTest {

    private Producer<String, String> producer;

    @Before
    public void init() {
        Properties props = new Properties();
        props.put("bootstrap.servers", "192.168.94.20:9091");
        props.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        props.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        producer = new KafkaProducer<>(props);
    }

    @After
    public void close() {
        producer.flush();
        producer.close();
    }

    @Test
    public void test() {
        producer.send(new ProducerRecord<>("topic-test", "1000", "1"));
    }

}
