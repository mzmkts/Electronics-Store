package com.narxoz.ElectronicsStore.Controller;

import com.narxoz.ElectronicsStore.Dto.OrderDto;
import com.narxoz.ElectronicsStore.Service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/order")
public class OrderApi {
    private final OrderService orderService;

    @GetMapping
    public ResponseEntity<?> getAll(){
        return new ResponseEntity<>(orderService.getAll(), HttpStatus.OK);
    }
    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable Long id){
        return new ResponseEntity<>(orderService.getById(id),HttpStatus.OK);
    }
    @PostMapping
    public ResponseEntity<?> addOrder(@RequestBody OrderDto orderDto){
        orderService.addOrder(orderDto);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
