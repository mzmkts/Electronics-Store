package com.narxoz.ElectronicsStore.Repository;

import com.narxoz.ElectronicsStore.Model.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepo extends JpaRepository<Category, Long> {
}
