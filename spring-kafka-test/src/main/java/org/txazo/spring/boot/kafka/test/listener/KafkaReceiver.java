package org.txazo.spring.boot.kafka.test.listener;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class KafkaReceiver {

    @KafkaListener(topics = {"test"}, containerFactory = "kafkaListenerContainerFactory")
    public void listen(List<ConsumerRecord<String, String>> records) {
    }

}
