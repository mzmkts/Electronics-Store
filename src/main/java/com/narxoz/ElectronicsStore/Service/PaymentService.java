package com.narxoz.ElectronicsStore.Service;

import com.narxoz.ElectronicsStore.Dto.PaymentDto;

import java.util.List;

public interface PaymentService {
    List<PaymentDto> getAll();
    PaymentDto getById(Long id);
    void addPayment(PaymentDto paymentDto);
}
