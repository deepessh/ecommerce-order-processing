package com.dc297.ecommerce.itemservice.services;

import com.dc297.ecommerce.itemservice.dtos.ItemDto;

import java.util.List;
import java.util.UUID;

public interface IItemService {
    ItemDto addItemToOrder(ItemDto item, UUID orderId);

    List<ItemDto> getItemsForOrder(UUID orderId);
}
