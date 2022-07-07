
package com.micropos.order.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.micropos.carts.mapper.CartMapper;
import com.micropos.carts.model.Cart;
import com.micropos.carts.model.Order;
import com.micropos.order.dto.OrderDto;
import com.micropos.order.service.OrderService;
import com.micropos.order.api.OrderApi;
import com.micropos.order.mapper.OrderMapper;
import com.micropos.order.service.OrderService;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api")
public class OrderController implements OrderApi {

    private final OrderMapper OrderMapper;

    private final OrderService OrderService;

    public OrderController(OrderService orderService, OrderMapper orderMapper) {
        this.OrderService = orderService;
        this.OrderMapper = orderMapper;
    }

    @Override
    public Mono<ResponseEntity<OrderDto>> orderCheckoutGet(ServerWebExchange exchange) throws JsonProcessingException {
        Order order = OrderService.checkout();
        return Mono.just(new ResponseEntity<>(OrderMapper.toOrderDto(order), HttpStatus.OK));
    }
}


