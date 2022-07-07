package com.micropos.carts.model;
import lombok.*;

import java.io.Serializable;

@Data
public class Item implements Serializable {
    private Product product;
    private int quantity;

    public Item() {
    }
    public Item(Product product,int quantity) {
        this.product = product;
        this.quantity = quantity;
    }
    public int getQuantity() {
        return quantity;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }


}

