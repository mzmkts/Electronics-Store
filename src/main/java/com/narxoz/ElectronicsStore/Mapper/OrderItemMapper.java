package com.narxoz.ElectronicsStore.Mapper;


import com.narxoz.ElectronicsStore.Dto.OrderItemDto;
import com.narxoz.ElectronicsStore.Model.OrderItem;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring", uses = {OrderMapper.class, ProductMapper.class})
public interface OrderItemMapper {

    @Mapping(target = "orderItemIdDto", source = "id")
    @Mapping(target = "quantityDto", source = "quantity")
    @Mapping(target = "productIdDto", source = "product.id")
    @Mapping(target = "orderIdDto", source = "order.id")
    OrderItemDto toDto(OrderItem orderItem);

    @Mapping(target = "id", source = "orderItemIdDto")
    @Mapping(target = "quantity", source = "quantityDto")
    @Mapping(target = "product.id", source = "productIdDto")
    @Mapping(target = "order.id", source = "orderIdDto")
    OrderItem toEntity(OrderItemDto orderItemDto);

    List<OrderItemDto> toDtoList(List<OrderItem> orderItems);
}

