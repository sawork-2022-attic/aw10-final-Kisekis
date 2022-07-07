package com.micropos.order.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.micropos.carts.model.Cart;
import com.micropos.carts.model.Item;
import com.micropos.carts.model.Order;
import com.micropos.carts.model.Product;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.stream.function.StreamBridge;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;

import java.io.Serializable;
import java.util.Objects;


@RequiredArgsConstructor
@Service
public class OrderServiceImp implements OrderService, Serializable {

    private final int numOfConsumers = 2;
    private int nextConsumer = 0;
    @Autowired
    private StreamBridge streamBridge;

    @Autowired
    @LoadBalanced
    protected RestTemplate restTemplate;

    @Autowired
    private WebClient.Builder webClient;
    @Override
    public void supply(Order order) {
        System.out.println("order supplied");
        String binding = "receive" + nextConsumer;
        streamBridge.send(binding, order);
        nextConsumer = (nextConsumer+1)%2;
    }

    @Override
    public Order checkout() throws JsonProcessingException {
        Order order = getOrder();
        supply(order);
        return order;
    }

    public Order getOrder() throws JsonProcessingException {
        String cart = webClient.build().get().uri("http://cart-service/api/carts").exchange()
                .block()
                .bodyToMono(String.class)
                .block();
        Cart c = new ObjectMapper().readValue(cart, Cart.class);

        Order order = new Order(c);
        return order;
    }
}
