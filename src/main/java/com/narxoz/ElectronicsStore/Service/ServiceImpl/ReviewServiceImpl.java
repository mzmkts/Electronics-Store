package com.narxoz.ElectronicsStore.Service.ServiceImpl;

import com.narxoz.ElectronicsStore.Dto.ReviewDto;
import com.narxoz.ElectronicsStore.Mapper.ReviewMapper;
import com.narxoz.ElectronicsStore.Model.Review;
import com.narxoz.ElectronicsStore.Repository.ReviewRepo;
import com.narxoz.ElectronicsStore.Service.ReviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ReviewServiceImpl implements ReviewService {
    private final ReviewMapper reviewMapper;
    private final ReviewRepo reviewRepo;

    @Override
    public List<ReviewDto> getAll() {
        List<Review> reviews = reviewRepo.findAll();
        List<ReviewDto> reviewDtos = reviewMapper.toDtoList(reviews);
        return reviewDtos;
    }

    @Override
    public ReviewDto getById(Long id) {
        return reviewMapper.toDto(reviewRepo.findById(id).orElseThrow(() -> new RuntimeException("Review not found with id: " + id)));
    }

    @Override
    public void addReview(ReviewDto reviewDto) {
        reviewRepo.save(reviewMapper.toEntity(reviewDto));
    }

    @Override
    public void updateReview(Long id, ReviewDto reviewDto) {
        Review existReview = reviewRepo.findById(id).orElseThrow(() -> new RuntimeException("Review not found with id: " + id));
        existReview.setReview(reviewDto.getReviewDto());
        existReview.setRating(reviewDto.getRatingDto());
    }

    @Override
    public void deleteReview(Long id) {
        reviewRepo.deleteById(id);
    }
}
