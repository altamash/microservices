package com.mes.gateway.configuration;

import com.mes.gateway.filter.JwtRequestFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;

@RefreshScope
@Configuration
public class RouteConfiguration {

    @Value("${swagger.host}")
    private String swaggerHost;
    @Value("${service.name.test}")
    private String testServiceName;
    @Value("${service.name.kafka}")
    private String kafkaServiceName;

    @Autowired
    @Lazy
    private JwtRequestFilter jwtRequestFilter;

    @Bean
    public RouteLocator apiRoutes(RouteLocatorBuilder builder) {
        return builder.routes()
                .route(testServiceName, p -> p
                        .path("/sample/**")
                        .filters(f -> f.filter(jwtRequestFilter)
                                .circuitBreaker(config -> config
                                        .setName(testServiceName)
                                        .setFallbackUri("forward:/testServiceFallback"))
                                .rewritePath("/sample/(?<path>.*)", "/${path}"))
                        .uri("lb://" + testServiceName))
                .route(kafkaServiceName, p -> p
                        .path("/kafka/**")
                        .filters(f -> f.filter(jwtRequestFilter)
                                .circuitBreaker(config -> config
                                        .setName(kafkaServiceName)
                                        .setFallbackUri("forward:/kafkaServiceFallback"))
                                .rewritePath("/kafka/(?<path>.*)", "/${path}"))
                        .uri("lb://" + kafkaServiceName))
                .route("openapi", r -> r
                        .path("/v3/api-docs/**")
                        .filters(f -> f
                                .rewritePath("/v3/api-docs/(?<path>.*)", "/${path}/v3/api-docs"))
                        .uri(swaggerHost))
                .build();
    }
}
