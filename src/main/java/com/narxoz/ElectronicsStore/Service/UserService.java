package com.narxoz.ElectronicsStore.Service;

import com.narxoz.ElectronicsStore.Dto.ProductDto;
import com.narxoz.ElectronicsStore.Dto.UserDto;
import com.narxoz.ElectronicsStore.Model.User;

import java.util.List;

public interface UserService {
    List<UserDto> getAll();

    UserDto getById(Long id);

    void registr(UserDto userDto);

    void updateUser(Long id, User user);

    void deleteUser(Long id);
}
