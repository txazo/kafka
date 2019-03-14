package org.txazo.kafka.test.consumer;

import org.txazo.kafka.test.ConfigConstants;

import java.util.Collections;
import java.util.Properties;

public class KafkaConsumer {

    private org.apache.kafka.clients.consumer.KafkaConsumer<String, String> consumer;

    public KafkaConsumer() {
        init();
    }

    public void init() {
        Properties props = new Properties();
        props.put("bootstrap.servers", ConfigConstants.BOOTSTRAP_SERVERS);
        props.put("group.id", "group-test");
        props.put("enable.auto.commit", true);
        props.put("auto.commit.interval.ms", 5000);
        props.put("max.partition.fetch.bytes", 1024 * 1024);
        props.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        props.put("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        consumer = new org.apache.kafka.clients.consumer.KafkaConsumer<>(props);
        consumer.subscribe(Collections.singletonList(ConfigConstants.TOPIC));
    }

    public org.apache.kafka.clients.consumer.KafkaConsumer<String, String> getConsumer() {
        return consumer;
    }

}
