package com.narxoz.ElectronicsStore.Service.ServiceImpl;


import com.narxoz.ElectronicsStore.Dto.UserDto;
import com.narxoz.ElectronicsStore.Mapper.UserMapper;
import com.narxoz.ElectronicsStore.Model.Role;
import com.narxoz.ElectronicsStore.Model.User;
import com.narxoz.ElectronicsStore.Repository.RoleRepo;
import com.narxoz.ElectronicsStore.Repository.UserRepo;
import com.narxoz.ElectronicsStore.Service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService, UserDetailsService {

    @Autowired
    private UserRepo userRepo;
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private RoleRepo roleRepo;

    @Override
    public List<UserDto> getAll() {
        List<User> users = userRepo.findAll();
        List<UserDto> userDtos = userMapper.toDtoList(users);
        return userDtos;
    }

    @Override
    public UserDto getById(Long id) {
        return userMapper.toDto(userRepo.findById(id).orElseThrow(() -> new RuntimeException("User not found with id: " + id)));
    }

    @Override
    public void registr(User user) {
        User check = userRepo.getByEmail(user.getEmail());
        if(check == null) {
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            List<Role> roles =List.of(roleRepo.getRoleByName("user"));
            user.setRoles(roles);
            userRepo.save(user);
        }else{
            throw new UsernameNotFoundException("User already exits");
        }
    }

    @Override
    public void updateUser(Long id, User user) {
        User existUser = userRepo.findById(id).orElseThrow(() -> new RuntimeException("User not found with id: " + id));
        existUser.setUsername(user.getUsername());
        existUser.setPassword(passwordEncoder.encode(user.getPassword()));
        existUser.setEmail(user.getEmail());
        existUser.setAddress(user.getAddress());
        userRepo.save(existUser);
    }

    @Override
    public void deleteUser(Long id) {
        userRepo.deleteById(id);
    }

    @Override
    public User loadUserByUsername(String email) {
        User user = userRepo.getByEmail(email);
        if (Objects.nonNull(user)) {
            return user;
        }
        throw new UsernameNotFoundException("User not found with email: " + email);
    }

}
