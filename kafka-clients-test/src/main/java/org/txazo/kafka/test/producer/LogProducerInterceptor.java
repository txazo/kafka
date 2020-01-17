package org.txazo.kafka.test.producer;

import com.alibaba.fastjson.JSON;
import org.apache.kafka.clients.producer.ProducerInterceptor;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;

public class LogProducerInterceptor implements ProducerInterceptor {

    private static final Logger LOGGER = LoggerFactory.getLogger(LogProducerInterceptor.class);

    @Override
    public ProducerRecord onSend(ProducerRecord record) {
        LOGGER.info("onSend topic={} key={}", record.topic(), record.key());
        return record;
    }

    @Override
    public void onAcknowledgement(RecordMetadata metadata, Exception exception) {
        LOGGER.info("onAcknowledgement topic={} partition={} offset={}", metadata.topic(), metadata.partition(), metadata.offset());
    }

    @Override
    public void close() {
    }

    @Override
    public void configure(Map<String, ?> configs) {
    }

}
