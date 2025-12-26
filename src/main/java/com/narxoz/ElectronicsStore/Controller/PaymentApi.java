package com.narxoz.ElectronicsStore.Controller;

import com.narxoz.ElectronicsStore.Dto.PaymentDto;
import com.narxoz.ElectronicsStore.Service.PaymentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/payment")
public class PaymentApi {
    private final PaymentService paymentService;

    @PreAuthorize("hasAnyRole('admin', 'manager')")
    @GetMapping
    public ResponseEntity<?> getAll() {
        return new ResponseEntity<>(paymentService.getAll(), HttpStatus.OK);
    }

    @PreAuthorize("hasAnyRole('admin', 'manager')")
    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable Long id) {
        return new ResponseEntity<>(paymentService.getById(id), HttpStatus.OK);
    }

    @PreAuthorize("hasAnyRole('admin', 'manager','user')")
    @PostMapping
    public ResponseEntity<?> addPayment(@RequestBody PaymentDto paymentDto) {
        paymentService.addPayment(paymentDto);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
