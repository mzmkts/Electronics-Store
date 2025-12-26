package com.narxoz.ElectronicsStore.Service.ServiceImpl;

import com.narxoz.ElectronicsStore.Dto.OrderDto;
import com.narxoz.ElectronicsStore.Mapper.OrderMapper;
import com.narxoz.ElectronicsStore.Model.Order;
import com.narxoz.ElectronicsStore.Model.OrderItem;
import com.narxoz.ElectronicsStore.Model.Payment;
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
    public void addOrder(OrderDto orderDto) {
        Order order = orderMapper.toEntity(orderDto);

        // Установить ссылку на родителя в OrderItem
        if (order.getOrderItem() != null) {
            for (OrderItem item : order.getOrderItem()) {
                item.setOrder(order);
            }
        }

        // Установить ссылку на родителя в Payment
        if (order.getPayments() != null) {
            for (Payment payment : order.getPayments()) {
                payment.setOrder(order);
            }
        }

        orderRepo.save(order);
    }

    @Override
    public OrderDto getById(Long id) {
        return orderMapper.toDto(orderRepo.findById(id).orElseThrow(() -> new RuntimeException("Order not found with id: " + id)));
    }
}
