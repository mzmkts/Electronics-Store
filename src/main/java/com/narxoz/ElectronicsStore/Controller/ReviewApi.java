package com.narxoz.ElectronicsStore.Controller;

import com.narxoz.ElectronicsStore.Dto.ReviewDto;
import com.narxoz.ElectronicsStore.Model.User;
import com.narxoz.ElectronicsStore.Repository.UserRepo;
import com.narxoz.ElectronicsStore.Service.ReviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;


@RestController
@RequiredArgsConstructor
@RequestMapping("/products")
public class ReviewApi {
    private final ReviewService reviewService;
    private final UserRepo userRepo;

    @GetMapping
    public ResponseEntity<?> getAll() {
        return new ResponseEntity<>(reviewService.getAll(), HttpStatus.OK);
    }

    @GetMapping("/reviews/{productsId}")
    public ResponseEntity<?> getById(@PathVariable Long productsId) {
        return new ResponseEntity<>(reviewService.getById(productsId), HttpStatus.OK);
    }

    @PostMapping("/reviews/{productsId}/{userId}")
    public ResponseEntity<?> addReview(@PathVariable Long productsId, @PathVariable Long userId, @RequestBody ReviewDto reviewDto) {
        User user = userRepo.findById(userId).orElseThrow();

        reviewService.addReview(productsId, user, reviewDto);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping("/reviews/{id}")
    public ResponseEntity<?> updateReview(@PathVariable Long id, @RequestBody ReviewDto reviewDto) {
        reviewService.updateReview(id, reviewDto);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/reviews/{id}")
    public ResponseEntity<?> deleteReview(@PathVariable Long id) {
        reviewService.deleteReview(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}

