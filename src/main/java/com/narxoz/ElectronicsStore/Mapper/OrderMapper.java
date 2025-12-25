package com.narxoz.ElectronicsStore.Mapper;


import com.narxoz.ElectronicsStore.Dto.OrderDto;
import com.narxoz.ElectronicsStore.Model.Order;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring", uses = {UserMapper.class, OrderItemMapper.class, PaymentMapper.class})
public interface OrderMapper {

    @Mapping(target = "orderIdDto", source = "id")
    @Mapping(target = "orderDateTime", source = "orderDateTime")
    @Mapping(target = "status", source = "status")
    @Mapping(target = "totalAmount", source = "totalAmount")
    @Mapping(target = "userDto", source = "user")
    @Mapping(target = "orderItemDto", source = "orderItem")
    @Mapping(target = "paymentsDto", source = "payments")
    OrderDto toDto(Order order);

    @Mapping(target = "id", source = "orderIdDto")
    @Mapping(target = "orderDateTime", source = "orderDateTime")
    @Mapping(target = "status", source = "status")
    @Mapping(target = "totalAmount", source = "totalAmount")
    @Mapping(target = "user", source = "userDto")
    @Mapping(target = "orderItem", source = "orderItemDto")
    @Mapping(target = "payments", source = "paymentsDto")
    Order toEntity(OrderDto orderDto);

    List<OrderDto> toDtoList(List<Order> orders);
}
