package com.micropos.delivery.mapper;

import com.micropos.carts.dto.ItemDto;
import com.micropos.carts.model.Item;
import com.micropos.delivery.dto.DeliveryDto;
import com.micropos.delivery.model.Delivery;
import org.mapstruct.Mapper;

import java.util.Collection;
import java.util.List;

@Mapper
public interface DeliveryMapper {
    List<com.micropos.delivery.dto.ItemDto> toItemsDto(Collection<Item> items);

    Collection<Item> toItems(Collection<ItemDto> items);

    Delivery toDelivery(DeliveryDto deliveryDto);

    DeliveryDto toDeliveryDto(Delivery delivery);
}
