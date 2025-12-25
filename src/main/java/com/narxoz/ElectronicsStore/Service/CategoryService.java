package com.narxoz.ElectronicsStore.Service;

import com.narxoz.ElectronicsStore.Dto.CategoryDto;

import java.util.List;

public interface CategoryService {
    List<CategoryDto> getAll();
    CategoryDto getById(Long id);
    void addCategory(CategoryDto categoryDto);
    void updateCategory(Long id, CategoryDto categoryDto);
    void deleteCategory(Long id);
}
