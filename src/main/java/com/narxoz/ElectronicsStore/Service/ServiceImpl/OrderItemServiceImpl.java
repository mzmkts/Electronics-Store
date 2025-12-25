package com.narxoz.ElectronicsStore.Service.ServiceImpl;

import com.narxoz.ElectronicsStore.Dto.OrderItemDto;
import com.narxoz.ElectronicsStore.Mapper.OrderItemMapper;
import com.narxoz.ElectronicsStore.Model.OrderItem;
import com.narxoz.ElectronicsStore.Repository.OrderItemRepo;
import com.narxoz.ElectronicsStore.Service.OrderItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderItemServiceImpl implements OrderItemService {

    private final OrderItemRepo orderItemRepo;
    private final OrderItemMapper orderItemMapper;
    @Override
    public List<OrderItemDto> getAll() {
        List<OrderItem> orderItems = orderItemRepo.findAll();
        List<OrderItemDto> orderItemDtos = orderItemMapper.toDtoList(orderItems);
        return orderItemDtos;
    }

    @Override
    public OrderItemDto getById(Long id) {
        return orderItemMapper.toDto(orderItemRepo.findById(id).orElseThrow(() -> new RuntimeException("Order item not found with id: " + id)));
    }
}
