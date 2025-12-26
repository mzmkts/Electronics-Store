package com.narxoz.ElectronicsStore.Service.ServiceImpl;

import com.narxoz.ElectronicsStore.Dto.CategoryDto;
import com.narxoz.ElectronicsStore.Mapper.CategoryMapper;
import com.narxoz.ElectronicsStore.Model.Category;
import com.narxoz.ElectronicsStore.Repository.CategoryRepo;
import com.narxoz.ElectronicsStore.Service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {

    private final CategoryMapper categoryMapper;
    private final CategoryRepo categoryRepo;


    @Override
    public List<CategoryDto> getAll() {
        List<Category> categories = categoryRepo.findAll();
        List<CategoryDto> categoryDtos = categoryMapper.toDtoList(categories);
        return categoryDtos;
    }

    @Override
    public CategoryDto getById(Long id) {
        return categoryMapper.toDto(categoryRepo.findById(id).orElseThrow());
    }

    @Override
    public void addCategory(CategoryDto categoryDto) {
        categoryRepo.save(categoryMapper.toEntity(categoryDto));
    }

    @Override
    public void updateCategory(Long id, CategoryDto categoryDto) {
        Category existCategory = categoryRepo.findById(id).orElseThrow(() -> new RuntimeException("Category not found with id: " + id));
        existCategory.setCategoryName(categoryDto.getCategoryNameDto());

        categoryRepo.save(existCategory);
    }

    @Override
    public void deleteCategory(Long id) {
        categoryRepo.deleteById(id);
    }
}
