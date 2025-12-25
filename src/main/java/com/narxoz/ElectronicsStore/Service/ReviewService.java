package com.narxoz.ElectronicsStore.Service;


import com.narxoz.ElectronicsStore.Dto.ReviewDto;
import com.narxoz.ElectronicsStore.Model.User;

import java.util.List;

public interface ReviewService {
    List<ReviewDto> getAll();

    ReviewDto getById(Long id);

    void addReview(Long productId, User user, ReviewDto reviewDto);

    void updateReview(Long id, ReviewDto reviewDto);

    void deleteReview(Long id);
}
