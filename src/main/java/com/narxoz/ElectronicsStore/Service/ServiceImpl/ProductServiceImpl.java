package com.narxoz.ElectronicsStore.Service.ServiceImpl;

import com.narxoz.ElectronicsStore.Dto.ProductDto;
import com.narxoz.ElectronicsStore.Mapper.ProductMapper;
import com.narxoz.ElectronicsStore.Model.Product;
import com.narxoz.ElectronicsStore.Repository.ProductRepo;
import com.narxoz.ElectronicsStore.Service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductMapper productMapper;
    private final ProductRepo productRepo;

    @Override
    public List<ProductDto> getAll() {
        List<Product> products = productRepo.findAll();
        List<ProductDto> productDtos = productMapper.toDtoList(products);
        return productDtos;
    }

    @Override
    public ProductDto getById(Long id) {
        return productMapper.toDto(productRepo.findById(id).orElseThrow(() -> new RuntimeException("Product not found with id: " + id)));
    }

    @Override
    public void addProduct(ProductDto productDto) {
        productRepo.save(productMapper.toEntity(productDto));
    }

    @Override
    public void updateProduct(Long id, ProductDto productDto) {
        Product existProduct = productRepo.findById(id).orElseThrow(() -> new RuntimeException("Product not found with id: " + id));
        existProduct.setProductName(productDto.getProductNameDto());
        existProduct.setProductPrice(productDto.getProductPriceDto());
        existProduct.setProductDescription(productDto.getProductDescriptionDto());
    }

    @Override
    public void deleteProduct(Long id) {
        productRepo.deleteById(id);
    }
}
