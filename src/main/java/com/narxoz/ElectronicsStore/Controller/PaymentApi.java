package com.narxoz.ElectronicsStore.Controller;

import com.narxoz.ElectronicsStore.Dto.PaymentDto;
import com.narxoz.ElectronicsStore.Service.PaymentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/payment")
public class PaymentApi {
    private final PaymentService paymentService;

    @GetMapping
    public ResponseEntity<?> getAll(){
        return new ResponseEntity<>(paymentService.getAll(), HttpStatus.OK);
    }
    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable Long id){
        return new ResponseEntity<>(paymentService.getById(id),HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<?> addPayment(@RequestBody PaymentDto paymentDto){
        paymentService.addPayment(paymentDto);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
