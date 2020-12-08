package com.dc297.ecommerce.itemservice.controllers;

import com.dc297.ecommerce.itemservice.dtos.ItemDto;
import com.dc297.ecommerce.itemservice.entities.Item;
import com.dc297.ecommerce.itemservice.repositories.ItemRepository;
import com.dc297.ecommerce.itemservice.services.IItemService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping
public class ItemController {
    private final ItemRepository itemRepository;
    private final IItemService itemService;

    public ItemController(ItemRepository itemRepository, IItemService itemService) {
        this.itemRepository = itemRepository;
        this.itemService = itemService;
    }

    @GetMapping("/items")
    public List<Item> getAll(){
        return itemRepository.findAll();
    }

    @PostMapping("/items")
    public Item create(@RequestBody Item item){
        return itemRepository.save(item);
    }

    @PutMapping("orders/{orderId}/items")
    public ItemDto addItemToOrder(@RequestBody ItemDto item, @PathVariable UUID orderId){
        return itemService.addItemToOrder(item, orderId);
    }

    @GetMapping("orders/{orderId}/items")
    public List<ItemDto> getItemsForOrder(@PathVariable UUID orderId){
        return itemService.getItemsForOrder(orderId);
    }
}
