package com.narxoz.ElectronicsStore.Dto;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class OrderItemDto {
    private Long orderItemIdDto;
    private Integer quantityDto;
    private Long productIdDto;
    private Long orderIdDto;
}
