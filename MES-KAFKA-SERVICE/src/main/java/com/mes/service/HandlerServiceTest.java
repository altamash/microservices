package com.mes.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class HandlerServiceTest implements HandlerService {

    private static final Logger LOGGER = LoggerFactory.getLogger(HandlerServiceTest.class);

    @Override
    public void handleMessage(String topic, String message) {
        LOGGER.info("Topic: {},\nMessage: {}" ,topic, message);
    }
}
