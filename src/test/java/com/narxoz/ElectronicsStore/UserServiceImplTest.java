package com.narxoz.ElectronicsStore;

import com.narxoz.ElectronicsStore.Dto.UserDto;
import com.narxoz.ElectronicsStore.Model.Role;
import com.narxoz.ElectronicsStore.Model.User;
import com.narxoz.ElectronicsStore.Repository.RoleRepo;
import com.narxoz.ElectronicsStore.Repository.UserRepo;
import com.narxoz.ElectronicsStore.Service.ServiceImpl.UserServiceImpl;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class UserServiceImplTest {

    @Autowired
    private UserServiceImpl userService;

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private RoleRepo roleRepo;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Test
    void getAll_returnsList() {
        User saved = ensureUser("all_test_" + System.nanoTime() + "@mail.com");
        List<?> all = userService.getAll();
        assertNotNull(all);
        assertTrue(all.size() >= 1);
        cleanupUser(saved.getId());
    }

    @Test
    void getById() {
        User saved = ensureUser("byid_" + System.nanoTime() + "@mail.com");
        var dto = userService.getById(saved.getId());
        assertNotNull(dto);
        assertEquals(saved.getId(), dto.getUserIdDto());
        assertEquals(saved.getEmail(), dto.getEmailDto());
        cleanupUser(saved.getId());
    }

    @Test
    void registr() {
        String email = "reg_" + System.nanoTime() + "@mail.com";
        ensureRoleUserExists();

        UserDto dto = new UserDto();
        dto.setEmailDto(email);
        dto.setUsernameDto("u_" + System.nanoTime());
        dto.setPasswordDto("12345");
        dto.setAddressDto("addr");

        userService.registr(dto);

        User fromDb = userRepo.getByEmail(email);
        assertNotNull(fromDb);
        assertNotEquals("12345", fromDb.getPassword());
        assertTrue(passwordEncoder.matches("12345", fromDb.getPassword()));

        assertNotNull(fromDb.getRoles());
        assertFalse(fromDb.getRoles().isEmpty());
        assertTrue(fromDb.getRoles().stream().anyMatch(r -> "user".equalsIgnoreCase(r.getName())));

        cleanupUser(fromDb.getId());
    }

    @Test
    void registr_test() {
        String email = "exists_" + System.nanoTime() + "@mail.com";
        ensureRoleUserExists();
        User saved = ensureUser(email);

        UserDto dto = new UserDto();
        dto.setEmailDto(email);
        dto.setUsernameDto("another");
        dto.setPasswordDto("pass");
        dto.setAddressDto("addr");

        assertThrows(UsernameNotFoundException.class, () -> userService.registr(dto));

        cleanupUser(saved.getId());
    }

    @Test
    void updateUser() {
        User saved = ensureUser("upd_" + System.nanoTime() + "@mail.com");

        User update = new User();
        update.setUsername("newName");
        update.setEmail("new_" + System.nanoTime() + "@mail.com");
        update.setPassword("newPass");
        update.setAddress("newAddr");

        userService.updateUser(saved.getId(), update);

        User fromDb = userRepo.findById(saved.getId()).orElseThrow();
        assertEquals("newName", fromDb.getUsername());
        assertEquals(update.getEmail(), fromDb.getEmail());
        assertEquals("newAddr", fromDb.getAddress());
        assertTrue(passwordEncoder.matches("newPass", fromDb.getPassword()));

        cleanupUser(saved.getId());
    }

    @Test
    void deleteUser_deletesById() {
        User saved = ensureUser("del_" + System.nanoTime() + "@mail.com");
        Long id = saved.getId();

        userService.deleteUser(id);

        assertTrue(userRepo.findById(id).isEmpty());
    }

    @Test
    void loadUserByUsername() {
        String email = "load_" + System.nanoTime() + "@mail.com";
        User saved = ensureUser(email);

        User loaded = userService.loadUserByUsername(email);

        assertNotNull(loaded);
        assertEquals(email, loaded.getEmail());

        cleanupUser(saved.getId());
    }

    @Test
    void loadUserByUsername_Test() {
        assertThrows(UsernameNotFoundException.class,
                () -> userService.loadUserByUsername("nope_" + System.nanoTime() + "@mail.com"));
    }


    private void ensureRoleUserExists() {
        Role role = roleRepo.getRoleByName("user");
        if (role == null) {
            Role r = new Role();
            r.setName("user");
            roleRepo.save(r);
        }
    }

    private User ensureUser(String email) {
        ensureRoleUserExists();

        User existing = userRepo.getByEmail(email);
        if (existing != null) return existing;

        Role role = roleRepo.getRoleByName("user");

        User u = new User();
        u.setEmail(email);
        u.setUsername("name_" + System.nanoTime());
        u.setPassword(passwordEncoder.encode("pass"));
        u.setAddress("address");
        u.setRoles(List.of(role));
        return userRepo.save(u);
    }

    private void cleanupUser(Long userId) {
        if (userId == null) return;
        if (userRepo.existsById(userId)) {
            userRepo.deleteById(userId);
        }
    }
}
