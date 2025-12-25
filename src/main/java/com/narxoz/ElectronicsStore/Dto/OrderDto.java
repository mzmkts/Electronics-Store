package com.narxoz.ElectronicsStore.Dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class OrderDto {
    private Long orderIdDto;
    private LocalDateTime orderDateTime;
    private String status;
    private BigDecimal totalAmount;
    private UserDto userDto;
    private List<OrderItemDto> orderItemDto;
    private List<PaymentDto> paymentsDto;
}
