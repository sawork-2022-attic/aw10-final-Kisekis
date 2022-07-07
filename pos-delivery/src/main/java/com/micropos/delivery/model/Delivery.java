package com.micropos.delivery.model;

import com.micropos.carts.model.Item;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class Delivery {
    private String id;
    private Status status;

    public String getId() {
        return id;
    }

    public Status getStatus() {
        return status;
    }

    public Delivery(String id,Status status) {
        this.id = id;
        this.status = status;
    }

    public Delivery(){};


}
