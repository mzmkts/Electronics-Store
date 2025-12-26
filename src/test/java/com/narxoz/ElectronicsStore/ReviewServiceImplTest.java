package com.narxoz.ElectronicsStore;

import com.narxoz.ElectronicsStore.Dto.ReviewDto;
import com.narxoz.ElectronicsStore.Model.Product;
import com.narxoz.ElectronicsStore.Model.Review;
import com.narxoz.ElectronicsStore.Model.User;
import com.narxoz.ElectronicsStore.Repository.ReviewRepo;
import com.narxoz.ElectronicsStore.Repository.ProductRepo;
import com.narxoz.ElectronicsStore.Repository.UserRepo;
import com.narxoz.ElectronicsStore.Service.ReviewService;
import jakarta.persistence.EntityManager;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class ReviewServiceImplTest {

    @Autowired
    private ReviewService reviewService;

    @Autowired
    private ReviewRepo reviewRepo;

    @Autowired
    private ProductRepo productRepo;

    @Autowired
    private UserRepo userRepo;


    private User createUniqueUser() {
        String u = UUID.randomUUID().toString().substring(0, 8);

        User user = new User();
        user.setUsername("test_" + u);
        user.setEmail("test_" + u + "@mail.com");
        user.setPassword("123");
        user.setAddress("addr_" + u);

        return userRepo.save(user);
    }

    private Product createUniqueProduct() {
        String p = UUID.randomUUID().toString().substring(0, 8);

        Product product = new Product();
        product.setProductName("prod_" + p);
        product.setProductPrice(new BigDecimal("100.00"));
        product.setProductDescription("desc_" + p);

        return productRepo.save(product);
    }

    private Review createReviewEntity(User user, Product product, String text, int rating) {
        Review review = new Review();
        review.setUser(user);
        review.setProduct(product);
        review.setReview(text);
        review.setRating(rating);
        return reviewRepo.save(review);
    }

    @Test
    void addReview() {
        long before = reviewRepo.count();

        User user = createUniqueUser();
        Product product = createUniqueProduct();

        ReviewDto dto = new ReviewDto();
        dto.setReviewDto("Nice!");
        dto.setRatingDto(5);

        reviewService.addReview(product.getId(), user, dto);

        long after = reviewRepo.count();
        assertEquals(before + 1, after);

        Review saved = reviewRepo.findAll().stream()
                .filter(r -> r.getUser() != null && r.getProduct() != null)
                .filter(r -> r.getUser().getId().equals(user.getId()))
                .filter(r -> r.getProduct().getId().equals(product.getId()))
                .reduce((first, second) -> second)
                .orElseThrow();

        assertEquals("Nice!", saved.getReview());
        assertEquals(5, saved.getRating());

        reviewRepo.deleteById(saved.getId());
    }

    @Test
    void getById_returnsReview() {
        User user = createUniqueUser();
        Product product = createUniqueProduct();

        Review review = createReviewEntity(user, product, "Good", 4);

        ReviewDto got = reviewService.getById(review.getId());
        assertNotNull(got);
        assertEquals("Good", got.getReviewDto());
        assertEquals(4, got.getRatingDto());

        reviewRepo.deleteById(review.getId());
    }

    @Test
    void getById_test() {
        assertThrows(RuntimeException.class, () -> reviewService.getById(999999999L));
    }


    @Test
    void deleteReview_deletesById() {
        User user = createUniqueUser();
        Product product = createUniqueProduct();

        Review review = createReviewEntity(user, product, "To delete", 3);

        reviewService.deleteReview(review.getId());

        assertFalse(reviewRepo.findById(review.getId()).isPresent());
    }
    private static void setIfExists(Object target, String fieldName, Object value) {
        try {
            Field f = target.getClass().getDeclaredField(fieldName);
            f.setAccessible(true);
            f.set(target, value);
        } catch (NoSuchFieldException ignored) {
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
