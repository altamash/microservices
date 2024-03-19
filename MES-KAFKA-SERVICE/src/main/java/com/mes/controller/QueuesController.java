package com.mes.controller;

import com.mes.service.QueuesService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class QueuesController {

    private final QueuesService queuesService;

    public QueuesController(QueuesService queuesService) {
        this.queuesService = queuesService;
    }

    @GetMapping("/send-message")
    public String sendMessage(@RequestParam String topic, @RequestParam String message) {
        return queuesService.sendMessage(topic, message);
    }
}
