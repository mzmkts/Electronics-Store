package com.narxoz.ElectronicsStore.Mapper;


import com.narxoz.ElectronicsStore.Dto.CategoryDto;
import com.narxoz.ElectronicsStore.Model.Category;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring", uses = ProductMapper.class)
public interface CategoryMapper {

    @Mapping(target = "categoryIdDto", source = "id")
    @Mapping(target = "categoryNameDto", source = "categoryName")
    CategoryDto toDto(Category category);

    @Mapping(target = "id", source = "categoryIdDto")
    @Mapping(target = "categoryName", source = "categoryNameDto")
    Category toEntity(CategoryDto categoryDto);

    List<CategoryDto> toDtoList(List<Category> categories);
}

