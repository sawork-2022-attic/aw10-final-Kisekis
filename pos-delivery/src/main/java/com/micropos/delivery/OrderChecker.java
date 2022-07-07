package com.micropos.delivery;

import com.micropos.carts.model.*;
import com.micropos.delivery.model.Delivery;
import com.micropos.delivery.model.Status;
import com.micropos.delivery.repository.DeliveryRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.function.StreamBridge;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;

import java.util.List;
import java.util.Random;
import java.util.function.Consumer;

public class OrderChecker implements Consumer<Order> {

    public static final Logger log = LoggerFactory.getLogger(OrderChecker.class);
    private static final Long MAX_ACCOUNT = 10000L;

    @Autowired
    private StreamBridge streamBridge;

    @Autowired
    private DeliveryRepository deliveryRepository;
    @Override
    public void accept(Order order) {
        Random r = new Random();
        List<Item> items = order.getItems();
        int total_price = 0;
        for(Item i : items) {
            total_price+= (i.getQuantity()*i.getProduct().getPrice());
        }
        log.info("status : "+ order.getOrderStatus() + " order accepted,total price is : "+ total_price);

        if (total_price > MAX_ACCOUNT) {
            order.setOrderStatus(OrderStatus.DECLINED);
            streamBridge.send("order-declined", message(order));
        } else {
            order.setOrderStatus(OrderStatus.APPROVED);
            streamBridge.send("order-approved", message(order));
            Status s = Status.values()[new Random().nextInt(Status.values().length)];
            int id = r.nextInt(100000);
            Delivery d = new Delivery(id+"",s);
            deliveryRepository.addDelivery(d);
            log.info("id : "+ id);
        }

        log.info("status : "+ order.getOrderStatus());

    }

    private static final <T> Message<T> message(T val) {
        return MessageBuilder.withPayload(val).build();
    }
    
}
