package com.narxoz.ElectronicsStore.Repository;

import com.narxoz.ElectronicsStore.Model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepo extends JpaRepository<User, Long> {
}
