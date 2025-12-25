package com.narxoz.ElectronicsStore.Service;


import com.narxoz.ElectronicsStore.Dto.ReviewDto;

import java.util.List;

public interface ReviewService {
    List<ReviewDto> getAll();

    ReviewDto getById(Long id);

    void addReview(ReviewDto reviewDto);

    void updateReview(Long id, ReviewDto reviewDto);

    void deleteReview(Long id);
}
