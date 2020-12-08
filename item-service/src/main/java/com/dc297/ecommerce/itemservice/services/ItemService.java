package com.dc297.ecommerce.itemservice.services;

import com.dc297.ecommerce.itemservice.dtos.ItemDto;
import com.dc297.ecommerce.itemservice.entities.OrderItem;
import com.dc297.ecommerce.itemservice.exceptions.NotFoundException;
import com.dc297.ecommerce.itemservice.repositories.ItemRepository;
import com.dc297.ecommerce.itemservice.repositories.OrderItemRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class ItemService implements IItemService {

    private final ItemRepository itemRepository;
    private final OrderItemRepository orderItemRepository;

    public ItemService(ItemRepository itemRepository, OrderItemRepository orderItemRepository) {
        this.itemRepository = itemRepository;
        this.orderItemRepository = orderItemRepository;
    }

    @Override
    public ItemDto addItemToOrder(ItemDto item, UUID orderId) {
        if(item == null || orderId == null) return null;
        OrderItem orderItem = new OrderItem();

        orderItem.orderId = orderId;
        orderItem.item = itemRepository.findById(item.id).orElseThrow(NotFoundException::new);
        orderItem.quantity = item.quantity;

        return itemEntityToDto(orderItemRepository.save(orderItem));
    }

    @Override
    public List<ItemDto> getItemsForOrder(UUID orderId) {
        return orderItemRepository.findAllByOrderId(orderId).parallelStream().map(this::itemEntityToDto).collect(Collectors.toList());
    }

    private ItemDto itemEntityToDto(OrderItem x) {
        if(x == null) return null;

        ItemDto output = new ItemDto();
        output.id = x.item.id;
        output.name = x.item.name;
        output.price = x.item.price;
        output.quantity = x.quantity;
        return output;
    }
}
