package com.narxoz.ElectronicsStore.Dto;

import com.narxoz.ElectronicsStore.Model.Role;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {
    private Long userIdDto;
    private String usernameDto;
    private String passwordDto;
    private String emailDto;
    private String addressDto;
    private Role role;
}
