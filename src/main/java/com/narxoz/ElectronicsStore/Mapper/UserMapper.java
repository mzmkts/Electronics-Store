package com.narxoz.ElectronicsStore.Mapper;

import com.narxoz.ElectronicsStore.Dto.UserDto;
import com.narxoz.ElectronicsStore.Model.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface UserMapper {

    @Mapping(target = "userIdDto", source = "id")
    @Mapping(target = "usernameDto", source = "username")
    @Mapping(target = "passwordDto", source = "password")
    @Mapping(target = "emailDto", source = "email")
    @Mapping(target = "addressDto", source = "address")
    @Mapping(target = "roles", source = "roles")
    UserDto toDto(User user);

    @Mapping(target = "id", source = "userIdDto")
    @Mapping(target = "username", source = "usernameDto")
    @Mapping(target = "password", source = "passwordDto")
    @Mapping(target = "email", source = "emailDto")
    @Mapping(target = "address", source = "addressDto")
    @Mapping(target = "roles", source = "roles")
    User toEntity(UserDto userDto);

    List<UserDto> toDtoList(List<User> users);
}
