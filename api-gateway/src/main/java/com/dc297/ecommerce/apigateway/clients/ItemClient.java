package com.dc297.ecommerce.apigateway.clients;

import com.dc297.ecommerce.apigateway.dtos.Item;
import com.dc297.ecommerce.apigateway.dtos.ItemDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@FeignClient("item-service")
public interface ItemClient {
    @GetMapping("/items")
    @CrossOrigin
    List<Item> read();

    @PostMapping("/items")
    @CrossOrigin
    Item create(@RequestBody Item item);

    @GetMapping("/orders/{orderId}/items")
    @CrossOrigin
    List<ItemDto> getItemsForOrder(@PathVariable UUID orderId);

    @PutMapping("/orders/{orderId}/items")
    @CrossOrigin
    ItemDto addItemToOrder(@RequestBody ItemDto item, @PathVariable UUID orderId);
}
