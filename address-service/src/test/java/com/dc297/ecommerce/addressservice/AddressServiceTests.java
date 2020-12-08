package com.dc297.ecommerce.addressservice;

import com.dc297.ecommerce.addressservice.dtos.AddressDto;
import com.dc297.ecommerce.addressservice.entities.Address;
import com.dc297.ecommerce.addressservice.entities.OrderAddress;
import com.dc297.ecommerce.addressservice.exceptions.NotFoundException;
import com.dc297.ecommerce.addressservice.repositories.AddressRepository;
import com.dc297.ecommerce.addressservice.repositories.OrderAddressRepository;
import com.dc297.ecommerce.addressservice.services.AddressService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.*;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;


public class AddressServiceTests {

    private final AddressService addressService;

    private final Address testAddress = new Address();
    private final OrderAddress orderAddress = new OrderAddress();

    private final UUID testOrderId = UUID.randomUUID();
    private final UUID testAddressId = UUID.randomUUID();


    private final String testLine1 = "test_line_1";
    private final String testLine2 = "test_line_2";
    private final String testCity = "test_city";
    private final String testState = "test_state";
    private final String testZip = "test_zip";
    private final int testAddressType = 1;

    private final AddressRepository addressRepository;
    private final OrderAddressRepository orderAddressRepository;

    public AddressServiceTests(){
        addressRepository = Mockito.mock(AddressRepository.class);
        orderAddressRepository = Mockito.mock(OrderAddressRepository.class);

        setupData();

        addressService = new AddressService(addressRepository, orderAddressRepository);
    }


    @Test
    public void addPaymentMethodToOrder_Should_Return_Success_Given_Success_From_Dependencies(){
        Mockito.when(addressRepository.findById(Mockito.any(UUID.class))).thenReturn(Optional.of(testAddress));
        Mockito.when(orderAddressRepository.save(Mockito.any(OrderAddress.class))).thenReturn(orderAddress);

        AddressDto address = new AddressDto();
        address.id = testAddressId;
        address.city = testCity;
        address.line1 = testLine1;
        address.line2 = testLine2;
        address.state = testState;
        address.zip = testZip;
        address.type = testAddressType;

        AddressDto savedAddress = addressService.addAddressToOrder(address, testOrderId);

        checkAddress(savedAddress);
    }

    @Test
    public void addPaymentMethodToOrder_Should_ThrowNotFoundException_Given_InvalidPaymentMethod(){
        AddressDto addressDto = new AddressDto();
        assertThrows(NotFoundException.class, () -> addressService.addAddressToOrder(addressDto, testOrderId));
    }

    @Test
    public void getPaymentMethodsForOrder_Returns_Success_Given_Success_Response_From_Dependencies(){
        Mockito.when(orderAddressRepository.findAllByOrderId(Mockito.any(UUID.class))).thenReturn(new ArrayList<>(Arrays.asList(orderAddress, orderAddress)));
        List<AddressDto> addresses = addressService.getAddressForOrder(testOrderId);

        assertThat(addresses).isNotNull();
        assertThat(addresses.size()).isEqualTo(2);
        addresses.forEach(this::checkAddress);
    }

    private void checkAddress(AddressDto addressDto) {
        assertThat(addressDto).isNotNull();
        assertThat(addressDto.id).isEqualTo(testAddressId);
        assertThat(addressDto.line1).isEqualTo(testLine1);
        assertThat(addressDto.line2).isEqualTo(testLine2);
        assertThat(addressDto.city).isEqualTo(testCity);
        assertThat(addressDto.state).isEqualTo(testState);
        assertThat(addressDto.zip).isEqualTo(testZip);
        assertThat(addressDto.type).isEqualTo(testAddressType);
    }

    private void setupData() {
        testAddress.id = testAddressId;
        testAddress.line1 = testLine1;
        testAddress.line2 = testLine2;
        testAddress.city = testCity;
        testAddress.state = testState;
        testAddress.zip = testZip;

        orderAddress.orderId = testOrderId;
        orderAddress.address = new Address();
        orderAddress.address.id = testAddressId;
        orderAddress.address.line1 = testLine1;
        orderAddress.address.line2 = testLine2;
        orderAddress.address.city = testCity;
        orderAddress.address.state = testState;
        orderAddress.address.zip = testZip;
        orderAddress.type = testAddressType;
    }
}
