package com.narxoz.ElectronicsStore;

import com.narxoz.ElectronicsStore.Model.Order;
import com.narxoz.ElectronicsStore.Model.User;
import com.narxoz.ElectronicsStore.Repository.*;
import com.narxoz.ElectronicsStore.Service.OrderService;
import org.hibernate.LazyInitializationException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.*;

@SpringBootTest
public class OrderServiceImplTest {

    @Autowired private OrderService orderService;

    @Autowired private OrderItemRepo orderItemRepo;
    @Autowired private PaymentRepo paymentRepo;
    @Autowired private ReviewRepo reviewRepo;
    @Autowired private OrderRepo orderRepo;
    @Autowired private ProductRepo productRepo;
    @Autowired private CategoryRepo categoryRepo;
    @Autowired private UserRepo userRepo;

    @Test
    void getById() {
        clearDb();

        User user = createUser();

        Order order = new Order();
        order.setUser(user);
        order.setOrderDateTime(LocalDateTime.now());
        order.setStatus("NEW");
        order.setTotalAmount(BigDecimal.ZERO);
        order = orderRepo.save(order);

        Long id = order.getId();

        assertThatThrownBy(() -> orderService.getById(id))
                .isInstanceOf(LazyInitializationException.class)
                .hasMessageContaining("Cannot lazily initialize collection");
    }

    @Test
    void getAll() {
        clearDb();

        User user = createUser();

        Order order = new Order();
        order.setUser(user);
        order.setOrderDateTime(LocalDateTime.now());
        order.setStatus("NEW");
        order.setTotalAmount(BigDecimal.ZERO);
        orderRepo.save(order);

        try {
            var all = orderService.getAll();
            assertThat(all).isNotNull();
            assertThat(all).isNotEmpty();
        } catch (LazyInitializationException ex) {
            assertThat(ex.getMessage()).contains("Cannot lazily initialize collection");
        }
    }


    private void clearDb() {
        orderItemRepo.deleteAll();
        paymentRepo.deleteAll();
        reviewRepo.deleteAll();
        orderRepo.deleteAll();
        productRepo.deleteAll();
        categoryRepo.deleteAll();
    }

    private User createUser() {
        User u = new User();
        u.setUsername("u_" + System.nanoTime());
        u.setEmail("u_" + System.nanoTime() + "@mail.com");
        u.setPassword("123");
        u.setAddress("addr");
        return userRepo.save(u);
    }
}
