package com.micropos.gateway;

import com.micropos.delivery.dto.DeliveryDto;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.integration.channel.DirectChannel;
import org.springframework.integration.dsl.IntegrationFlow;
import org.springframework.integration.dsl.IntegrationFlows;
import org.springframework.integration.webflux.dsl.WebFlux;


@Configuration
public class Integration {
    @Bean
    public DirectChannel messageChannel() {
        return new DirectChannel();
    }

    @Bean
    public IntegrationFlow inGate() {
        return IntegrationFlows
                .from(WebFlux
                        .inboundGateway("/check/{id}")
                        .requestMapping(r -> r.methods(HttpMethod.GET))
                        .payloadExpression("#pathVariables.id"))
                .channel(messageChannel())
                .get();
    }

    @Bean
    public IntegrationFlow outGate() {
        return IntegrationFlows
                .from(messageChannel())
                .handle(WebFlux
                        .outboundGateway(m -> "http://localhost:8084/api/delivery/" + m.getPayload())
                        .httpMethod(HttpMethod.GET)
                        .expectedResponseType(DeliveryDto.class)
                )
                .get();
    }

}
