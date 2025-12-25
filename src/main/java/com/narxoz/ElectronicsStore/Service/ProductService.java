package com.narxoz.ElectronicsStore.Service;


import com.narxoz.ElectronicsStore.Dto.ProductDto;

import java.util.List;

public interface ProductService {
    List<ProductDto> getAll();
    ProductDto getById(Long id);
    void addProduct(ProductDto productDto);
    void updateProduct(Long id, ProductDto productDto);
    void deleteProduct(Long id);
}
