package com.micropos.carts.model;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class Order {
    private List<Item> items = new ArrayList<>();
    private OrderStatus orderStatus;
    public Order(Cart cart) {
        this.items = cart.getItems();
        orderStatus = OrderStatus.PENDING;
    }
    public Order(){}

    public List<Item> getItems() {
        return items;
    }

    public OrderStatus getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(OrderStatus orderStatus) {
        this.orderStatus = orderStatus;
    }
}

