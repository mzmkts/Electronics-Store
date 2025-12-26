package com.narxoz.ElectronicsStore.Dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PaymentDto {
    private Long paymentIdDto;
    private String paymentMethodDto;
    private LocalDateTime paymentDate;
    private Long orderIdDto;
}
