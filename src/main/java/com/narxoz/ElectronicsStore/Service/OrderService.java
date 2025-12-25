package com.narxoz.ElectronicsStore.Service;

import com.narxoz.ElectronicsStore.Dto.OrderDto;

import java.util.List;

public interface OrderService {
    List<OrderDto> getAll();
    OrderDto getById(Long id);

}
