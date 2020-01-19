package org.txazo.kafka.test.producer;

import org.apache.kafka.clients.producer.Producer;
import org.txazo.kafka.test.ConfigConstants;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class KafkaProducer {

    private Producer<String, String> producer;

    public KafkaProducer() {
        init();
    }

    public void init() {
        Properties props = new Properties();
        props.put("bootstrap.servers", ConfigConstants.BOOTSTRAP_SERVERS);
        props.put("buffer.memory", 32 * 1000L * 1000L);
        props.put("retries", 3);
        props.put("acks", "all");
        props.put("compression.type", "gzip");
        props.put("batch.size", 16384);
        props.put("linger.ms", 5000);
        props.put("send.buffer.bytes", 128 * 1024);
        props.put("max.request.size", 1024 * 1024);
        props.put("max.in.flight.requests.per.connection", 1);
        props.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        props.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");

        props.put("metadata.max.age.ms", 10 * 1000);

        props.put("partitioner.class", "org.apache.kafka.clients.producer.internals.DefaultPartitioner");

        List<String> interceptors = new ArrayList<>();
        interceptors.add(LogProducerInterceptor.class.getName());
        props.put("interceptor.classes", interceptors);

        producer = new org.apache.kafka.clients.producer.KafkaProducer<>(props);
    }

    public Producer<String, String> getProducer() {
        return producer;
    }

}
