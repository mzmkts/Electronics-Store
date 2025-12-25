package com.narxoz.ElectronicsStore.Service;

import com.narxoz.ElectronicsStore.Dto.CategoryDto;
import com.narxoz.ElectronicsStore.Model.Category;

import java.util.List;

public interface CategoryService {
    List<CategoryDto> getAll();
    CategoryDto getById(Long id);
    void addCategory(CategoryDto categoryDto);
    Category updateCategory(Long id, CategoryDto categoryDto);
    void deleteCategory(Long id);
}
