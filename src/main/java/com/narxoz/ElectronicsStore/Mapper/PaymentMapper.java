package com.narxoz.ElectronicsStore.Mapper;


import com.narxoz.ElectronicsStore.Dto.PaymentDto;
import com.narxoz.ElectronicsStore.Model.Payment;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring", uses = OrderMapper.class)
public interface PaymentMapper {

    @Mapping(target = "paymentIdDto", source = "id")
    @Mapping(target = "paymentMethodDto", source = "paymentMethod")
    @Mapping(target = "paymentDate", source = "paymentDate")
    @Mapping(target = "orderIdDto", source = "order.id")
    PaymentDto toDto(Payment payment);

    @Mapping(target = "id", source = "paymentIdDto")
    @Mapping(target = "paymentMethod", source = "paymentMethodDto")
    @Mapping(target = "paymentDate", source = "paymentDate")
    @Mapping(target = "order.id", source = "orderIdDto")
    Payment toEntity(PaymentDto paymentDto);

    List<PaymentDto> toDtoList(List<Payment> payments);
}

