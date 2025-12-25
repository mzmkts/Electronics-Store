package com.narxoz.ElectronicsStore.Mapper;


import com.narxoz.ElectronicsStore.Dto.ProductDto;
import com.narxoz.ElectronicsStore.Model.Product;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(
        componentModel = "spring",
        uses = {CategoryMapper.class, OrderItemMapper.class, ReviewMapper.class}
)
public interface ProductMapper {

    @Mapping(target = "productIdDto", source = "id")
    @Mapping(target = "productNameDto", source = "productName")
    @Mapping(target = "productPriceDto", source = "productPrice")
    @Mapping(target = "productDescriptionDto", source = "productDescription")
    @Mapping(target = "categoryDto", source = "category")
    @Mapping(target = "reviewsDto", source = "reviews")
    ProductDto toDto(Product product);

    @Mapping(target = "id", source = "productIdDto")
    @Mapping(target = "productName", source = "productNameDto")
    @Mapping(target = "productPrice", source = "productPriceDto")
    @Mapping(target = "productDescription", source = "productDescriptionDto")
    @Mapping(target = "category", source = "categoryDto")
    @Mapping(target = "reviews", source = "reviewsDto")
    Product toEntity(ProductDto productDto);

    List<ProductDto> toDtoList(List<Product> products);
}
