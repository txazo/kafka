package org.txazo.spring.boot.kafka.test.listener;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;
import org.txazo.spring.boot.kafka.test.ConfigConstants;

import java.util.List;

@Component
public class KafkaReceiver {

    @KafkaListener(topics = {ConfigConstants.TOPIC_1, ConfigConstants.TOPIC_2},
            containerFactory = "kafkaListenerContainerFactory")
    public void listen(List<ConsumerRecord<String, String>> records) throws Exception {
        String topic = records.get(0).topic();
        int partition = records.get(0).partition();
        boolean samePartition = true;
        for (ConsumerRecord<String, String> record : records) {
            if (!topic.equals(record.topic()) || partition != record.partition()) {
                samePartition = false;
            }
        }
        System.out.println(String.format("%s\t%d\t%b", topic, partition, samePartition));
        Thread.sleep(1000);
    }

}
