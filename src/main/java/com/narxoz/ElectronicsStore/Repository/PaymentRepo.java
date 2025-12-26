package com.narxoz.ElectronicsStore.Repository;

import com.narxoz.ElectronicsStore.Model.Payment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PaymentRepo extends JpaRepository<Payment, Long> {
}
