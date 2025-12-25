package com.narxoz.ElectronicsStore.Mapper;


import com.narxoz.ElectronicsStore.Dto.ReviewDto;
import com.narxoz.ElectronicsStore.Model.Review;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring", uses = {UserMapper.class, ProductMapper.class})
public interface ReviewMapper {

    @Mapping(target = "reviewIdDto", source = "id")
    @Mapping(target = "reviewDto", source = "review")
    @Mapping(target = "ratingDto", source = "rating")
    @Mapping(target = "userIdDto", source = "user.id")
    ReviewDto toDto(Review review);

    @Mapping(target = "id", source = "reviewIdDto")
    @Mapping(target = "review", source = "reviewDto")
    @Mapping(target = "rating", source = "ratingDto")
    @Mapping(target = "user.id", source = "userIdDto")
    Review toEntity(ReviewDto reviewDto);

    List<ReviewDto> toDtoList(List<Review> reviews);
}