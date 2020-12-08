package com.dc297.ecommerce.apigateway.controllers;

import com.dc297.ecommerce.apigateway.clients.ItemClient;
import com.dc297.ecommerce.apigateway.dtos.Item;
import com.dc297.ecommerce.apigateway.dtos.ItemDto;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;


@RestController
class ItemController {
    public final ItemClient itemClient;


    ItemController(ItemClient itemClient) {
        this.itemClient = itemClient;
    }

    @GetMapping("/items")
    @CrossOrigin
    public List<Item> read(){
        return itemClient.read();
    }

    @PostMapping("/items")
    @CrossOrigin
    public Item create(@RequestBody Item item){
        return itemClient.create(item);
    }

}
