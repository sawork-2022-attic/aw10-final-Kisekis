package com.micropos.delivery.repository;


import com.micropos.delivery.model.Delivery;


public interface DeliveryRepository {

    public Delivery findDelivery(String id);

    public void addDelivery(Delivery d);

}