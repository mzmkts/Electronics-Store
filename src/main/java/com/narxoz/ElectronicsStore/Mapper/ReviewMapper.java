package com.narxoz.ElectronicsStore.Mapper;


import com.narxoz.ElectronicsStore.Dto.ReviewDto;
import com.narxoz.ElectronicsStore.Model.Review;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = {UserMapper.class, ProductMapper.class})
public interface ReviewMapper {

    @Mapping(target = "reviewIdDto", source = "id")
    @Mapping(target = "reviewDto", source = "review")
    @Mapping(target = "ratingDto", source = "rating")
    @Mapping(target = "userDto", source = "user")
    ReviewDto toDto(Review review);

    @Mapping(target = "id", source = "reviewIdDto")
    @Mapping(target = "review", source = "reviewDto")
    @Mapping(target = "rating", source = "ratingDto")
    @Mapping(target = "user", source = "userDto")
    Review toEntity(ReviewDto reviewDto);
}