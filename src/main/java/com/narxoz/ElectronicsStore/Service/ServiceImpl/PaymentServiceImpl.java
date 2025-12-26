package com.narxoz.ElectronicsStore.Service.ServiceImpl;

import com.narxoz.ElectronicsStore.Dto.OrderDto;
import com.narxoz.ElectronicsStore.Dto.PaymentDto;
import com.narxoz.ElectronicsStore.Mapper.OrderMapper;
import com.narxoz.ElectronicsStore.Mapper.PaymentMapper;
import com.narxoz.ElectronicsStore.Model.Order;
import com.narxoz.ElectronicsStore.Model.Payment;
import com.narxoz.ElectronicsStore.Repository.OrderRepo;
import com.narxoz.ElectronicsStore.Repository.PaymentRepo;
import com.narxoz.ElectronicsStore.Service.PaymentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PaymentServiceImpl implements PaymentService {
    private final PaymentRepo paymentRepo;
    private final PaymentMapper paymentMapper;


    @Override
    public List<PaymentDto> getAll() {
        List<Payment> payments = paymentRepo.findAll();
        List<PaymentDto> paymentDtos = paymentMapper.toDtoList(payments);
        return paymentDtos;
    }

    @Override
    public PaymentDto getById(Long id) {
        return paymentMapper.toDto(paymentRepo.findById(id).orElseThrow(() -> new RuntimeException("Payment not found with id: " + id)));
    }

    @Override
    public void addPayment(PaymentDto paymentDto) {
        paymentRepo.save(paymentMapper.toEntity(paymentDto));
    }
}
