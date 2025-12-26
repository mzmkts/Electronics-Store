package com.narxoz.ElectronicsStore.Repository;

import com.narxoz.ElectronicsStore.Model.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderItemRepo extends JpaRepository<OrderItem, Long> {
}
