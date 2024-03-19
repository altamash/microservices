package com.mes.service;

import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class QueuesServiceImpl implements QueuesService {

    private final KafkaTemplate<String, Object> kafkaTemplate;

    public QueuesServiceImpl(KafkaTemplate<String, Object> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    @Override
    public String sendMessage(String topic, String message) {
        kafkaTemplate.send(topic, message);
        return null;
    }
}
