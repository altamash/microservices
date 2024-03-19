package com.mes.configuration.queue;

import com.mes.service.HandlerService;
import org.apache.kafka.clients.admin.NewTopic;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.KafkaListener;

@Configuration
public class TopicConfig {

    private static final Logger LOGGER = LoggerFactory.getLogger(TopicConfig.class);

    @Value(value = "${topic.test.name}")
    private String testTopicName;

    private final HandlerService handlerService;

    public TopicConfig(@Qualifier("handlerServiceTest") HandlerService handlerService) {
        this.handlerService = handlerService;
    }

    @Bean
    public NewTopic testTopic() {
        return new NewTopic(testTopicName, 1, (short) 1);
    }

    @KafkaListener(topics = "${topic.test.name}",
                   groupId = "${topic.test.group-id}",
                   containerFactory = "kafkaListenerContainerFactory")
    public void listen(String message) {
        LOGGER.info(message);
        handlerService.handleMessage(testTopicName, message);
    }
}
