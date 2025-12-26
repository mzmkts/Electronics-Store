package com.narxoz.ElectronicsStore.Service;

import com.narxoz.ElectronicsStore.Dto.OrderItemDto;

import java.util.List;

public interface OrderItemService {
    List<OrderItemDto> getAll();
    OrderItemDto getById(Long id);
    void addOrderItem(OrderItemDto orderItemDto);
}
