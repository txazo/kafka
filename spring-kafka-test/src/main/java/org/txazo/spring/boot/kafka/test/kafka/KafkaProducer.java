package org.txazo.spring.boot.kafka.test.kafka;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;
import org.txazo.spring.boot.kafka.test.ConfigConstants;

@Component
public class KafkaProducer implements InitializingBean {

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    @Override
    public void afterPropertiesSet() throws Exception {
        init();
    }

    private void init() {
        new Thread(() -> {
            int sequence = 1;
            while (true) {
                String key = String.format("%08d", sequence++);
                kafkaTemplate.send(ConfigConstants.TOPIC_1, key, key);
                kafkaTemplate.send(ConfigConstants.TOPIC_2, key, key);
                try {
                    Thread.sleep(50);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

}
