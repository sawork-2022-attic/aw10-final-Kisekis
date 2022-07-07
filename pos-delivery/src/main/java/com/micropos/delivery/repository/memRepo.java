//package com.micropos.delivery.repository;
//
//
//import com.micropos.delivery.model.Delivery;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.cache.annotation.Cacheable;
//import org.springframework.stereotype.Repository;
//
//import java.io.IOException;
//import java.net.URL;
//import java.util.ArrayList;
//import java.util.List;
//
//@Repository
//public class memRepo implements DeliveryRepository {
//    private List<Delivery> deliveries;
//
//    @Autowired
//    public memRepo() {
//        deliveries = new ArrayList<>();
//    }
//
//    @Cacheable(value="deliveries",key = "#p0")
//    @Override
//    public Delivery findDelivery(String id) {
//        for(Delivery d : deliveries) {
//            if(d.getId().equals(id)) {
//                return d;
//            }
//        }
//        return null;
//    }
//
//    public void addDelivery(Delivery d) {
//        if(findDelivery(d.getId()) == null) {
//            deliveries.add(d);
//        }
//    }
//
//}
