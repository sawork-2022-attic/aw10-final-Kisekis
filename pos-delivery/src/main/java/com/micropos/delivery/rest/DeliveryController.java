package com.micropos.delivery.rest;

import com.micropos.delivery.api.DeliveryApi;
import com.micropos.delivery.dto.DeliveryDto;
import com.micropos.delivery.mapper.DeliveryMapper;
import com.micropos.delivery.model.Delivery;
import com.micropos.delivery.service.DeliveryService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api")
public class DeliveryController implements DeliveryApi {

    private final DeliveryMapper deliveryMapper;

    private final DeliveryService deliveryService;

    public DeliveryController(DeliveryService deliveryService, DeliveryMapper deliveryMapper) {
        this.deliveryMapper = deliveryMapper;
        this.deliveryService = deliveryService;
    }

    @Override
    public Mono<ResponseEntity<DeliveryDto>> showDeliveryById(String id, ServerWebExchange exchange) {
        try{
            Delivery ds = this.deliveryService.getDelivery(id);
            DeliveryDto d = deliveryMapper.toDeliveryDto(ds);
            return Mono.just(new ResponseEntity<>(d, HttpStatus.OK));
        }catch (Exception ignored) { }
        return Mono.just(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

}
