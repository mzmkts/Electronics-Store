package com.narxoz.ElectronicsStore.Dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProductDto {
    private Long productIdDto;
    private String productNameDto;
    private BigDecimal productPriceDto;
    private String productDescriptionDto;
    private CategoryDto categoryDto;
    private List<ReviewDto> reviewsDto;
}
