package com.narxoz.ElectronicsStore.Service.ServiceImpl;

import com.narxoz.ElectronicsStore.Dto.OrderDto;
import com.narxoz.ElectronicsStore.Mapper.OrderMapper;
import com.narxoz.ElectronicsStore.Model.Order;
import com.narxoz.ElectronicsStore.Repository.OrderRepo;
import com.narxoz.ElectronicsStore.Service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {
    private final OrderRepo orderRepo;
    private final OrderMapper orderMapper;


    @Override
    public List<OrderDto> getAll() {
        List<Order> orders = orderRepo.findAll();
        List<OrderDto> orderDtos = orderMapper.toDtoList(orders);
        return orderDtos;
    }

    @Override
    public OrderDto getById(Long id) {
        return orderMapper.toDto(orderRepo.findById(id).orElseThrow(() -> new RuntimeException("Order not found with id: " + id)));
    }
}
