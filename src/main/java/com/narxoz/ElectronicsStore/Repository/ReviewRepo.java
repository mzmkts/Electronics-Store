package com.narxoz.ElectronicsStore.Repository;

import com.narxoz.ElectronicsStore.Model.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReviewRepo extends JpaRepository<Review, Long> {
}
