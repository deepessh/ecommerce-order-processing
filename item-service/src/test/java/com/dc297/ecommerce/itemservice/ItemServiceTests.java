package com.dc297.ecommerce.itemservice;

import com.dc297.ecommerce.itemservice.dtos.ItemDto;
import com.dc297.ecommerce.itemservice.entities.Item;
import com.dc297.ecommerce.itemservice.entities.OrderItem;
import com.dc297.ecommerce.itemservice.exceptions.NotFoundException;
import com.dc297.ecommerce.itemservice.repositories.ItemRepository;
import com.dc297.ecommerce.itemservice.repositories.OrderItemRepository;
import com.dc297.ecommerce.itemservice.services.ItemService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.*;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;


public class ItemServiceTests {

    private final ItemService itemService;

    private final Item testItem = new Item();
    private final OrderItem orderItem = new OrderItem();

    private final UUID testOrderId = UUID.randomUUID();
    private final UUID testItemId = UUID.randomUUID();


    private final String testName = "test_name";
    private final double testPrice = 10.0;
    private final int testQuantity = 2;

    private final ItemRepository itemRepository;
    private final OrderItemRepository orderItemRepository;

    public ItemServiceTests(){
        itemRepository = Mockito.mock(ItemRepository.class);
        orderItemRepository = Mockito.mock(OrderItemRepository.class);

        setupData();

        itemService = new ItemService(itemRepository, orderItemRepository);
    }


    @Test
    public void addPaymentMethodToOrder_Should_Return_Success_Given_Success_From_Dependencies(){
        Mockito.when(itemRepository.findById(Mockito.any(UUID.class))).thenReturn(Optional.of(testItem));
        Mockito.when(orderItemRepository.save(Mockito.any(OrderItem.class))).thenReturn(orderItem);

        ItemDto item = new ItemDto();
        item.id = testItemId;
        item.name = testName;
        item.price = testPrice;
        item.quantity = testQuantity;

        ItemDto savedItem = itemService.addItemToOrder(item, testOrderId);

        checkItem(savedItem);
    }

    @Test
    public void addPaymentMethodToOrder_Should_ThrowNotFoundException_Given_InvalidPaymentMethod(){
        ItemDto itemDto = new ItemDto();
        assertThrows(NotFoundException.class, () -> itemService.addItemToOrder(itemDto, testOrderId));
    }

    @Test
    public void getPaymentMethodsForOrder_Returns_Success_Given_Success_Response_From_Dependencies(){
        Mockito.when(orderItemRepository.findAllByOrderId(Mockito.any(UUID.class))).thenReturn(new ArrayList<>(Arrays.asList(orderItem, orderItem)));
        List<ItemDto> items = itemService.getItemsForOrder(testOrderId);

        assertThat(items).isNotNull();
        assertThat(items.size()).isEqualTo(2);
        items.forEach(this::checkItem);
    }

    private void checkItem(ItemDto itemDto) {
        assertThat(itemDto).isNotNull();
        assertThat(itemDto.id).isEqualTo(testItemId);
        assertThat(itemDto.name).isEqualTo(testName);
        assertThat(itemDto.price).isEqualTo(testPrice);
        assertThat(itemDto.quantity).isEqualTo(testQuantity);
    }

    private void setupData() {
        testItem.id = testItemId;
        testItem.name = testName;
        testItem.price = testPrice;

        orderItem.orderId = testOrderId;
        orderItem.item = new Item();
        orderItem.item.id = testItemId;
        orderItem.item.name = testName;
        orderItem.item.price = testPrice;
        orderItem.quantity = testQuantity;
    }
}
