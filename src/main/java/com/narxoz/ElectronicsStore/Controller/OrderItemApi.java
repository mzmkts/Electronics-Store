package com.narxoz.ElectronicsStore.Controller;

import com.narxoz.ElectronicsStore.Dto.OrderItemDto;
import com.narxoz.ElectronicsStore.Service.OrderItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/orderItem")
public class OrderItemApi {
    private final OrderItemService orderItemService;

    @PreAuthorize("hasAnyRole('admin', 'manager')")
    @GetMapping
    public ResponseEntity<?> getAll() {
        return new ResponseEntity<>(orderItemService.getAll(), HttpStatus.OK);
    }

    @PreAuthorize("hasAnyRole('admin', 'manager')")
    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable Long id) {
        return new ResponseEntity<>(orderItemService.getById(id), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<?> addOrderItem(@RequestBody OrderItemDto orderItemDto) {
        orderItemService.addOrderItem(orderItemDto);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
