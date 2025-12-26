package com.narxoz.ElectronicsStore.Controller;

import com.narxoz.ElectronicsStore.Dto.UserDto;
import com.narxoz.ElectronicsStore.Model.User;
import com.narxoz.ElectronicsStore.Service.UserService;
import jakarta.annotation.security.PermitAll;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
public class UserApi {
    private final UserService userService;

    @PreAuthorize("hasAnyRole('admin', 'manager')")
    @GetMapping("/user")
    public ResponseEntity<?> getAll() {
        return new ResponseEntity<>(userService.getAll(), HttpStatus.OK);
    }

    @PreAuthorize("hasAnyRole('admin', 'manager')")
    @GetMapping("/user/{id}")
    public ResponseEntity<?> getById(@PathVariable Long id) {
        return new ResponseEntity<>(userService.getById(id), HttpStatus.OK);
    }


    @PostMapping("/user/registr")
    public ResponseEntity<?> registr(@RequestBody UserDto userDto) {
        userService.registr(userDto);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PreAuthorize("hasAnyRole('admin', 'manager', 'user')")
    @PutMapping("/user/{id}")
    public ResponseEntity<?> updateUser(@PathVariable Long id, @RequestBody User user) {
        userService.updateUser(id, user);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PreAuthorize("hasAnyRole('admin', 'manager','user')")
    @DeleteMapping("/user/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
