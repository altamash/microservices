package com.mes.gateway;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
//@RequiredArgsConstructor
public class FallbackMethodController {

    final ObjectMapper mapper;

    public FallbackMethodController(ObjectMapper mapper) {
        this.mapper = mapper;
    }

    @GetMapping("/testServiceFallback")
//    @CircuitBreaker(name = "FallbackMethodController")
    public ObjectNode testServiceFallback() {
        ObjectNode message = mapper.createObjectNode();
        message.put("message", "Test service is closed for maintenance.");
        message.put("code", 503);
        return message;
    }

    @GetMapping("/kafkaServiceFallback")
    public ObjectNode kafkaServiceFallback() {
        ObjectNode message = mapper.createObjectNode();
        message.put("message", "Kafka service is closed for maintenance.");
        message.put("code", 503);
        return message;
    }
}
