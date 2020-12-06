package com.dc297.ecommerce.orderService.controllers;

import com.dc297.ecommerce.entities.Item;
import com.dc297.ecommerce.orderService.repositories.ItemRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/items")
public class ItemController {
    private final ItemRepository itemRepository;

    public ItemController(ItemRepository itemRepository) {
        this.itemRepository = itemRepository;
    }


    @GetMapping
    public List<Item> getAll(){
        return itemRepository.findAll();
    }

    @PostMapping
    public Item create(@RequestBody Item item){
        return itemRepository.save(item);
    }
}
