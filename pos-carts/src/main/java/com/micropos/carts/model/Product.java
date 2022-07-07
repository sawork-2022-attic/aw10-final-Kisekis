package com.micropos.carts.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
public class Product implements Serializable {
    private String id;
    private String name;
    private double price;
    private String image;

    public Product() {
    }
    public Product(String id,String name,double price,String image){
        this.id = id;
        this.name = name;
        this.price = price;
        this.image = image;
    }
    public String getId() {
        return id;
    }

    public double getPrice() {
        return price;
    }

    public String getImage() {
        return image;
    }

    public String getName() {
        return name;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
