package com.narxoz.ElectronicsStore;

import com.narxoz.ElectronicsStore.Dto.PaymentDto;
import com.narxoz.ElectronicsStore.Model.Order;
import com.narxoz.ElectronicsStore.Model.User;
import com.narxoz.ElectronicsStore.Repository.*;
import com.narxoz.ElectronicsStore.Service.PaymentService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.*;

@SpringBootTest
public class PaymentServiceImplTest {

    @Autowired private PaymentService paymentService;

    @Autowired private OrderItemRepo orderItemRepo;
    @Autowired private PaymentRepo paymentRepo;
    @Autowired private ReviewRepo reviewRepo;
    @Autowired private OrderRepo orderRepo;
    @Autowired private ProductRepo productRepo;
    @Autowired private CategoryRepo categoryRepo;
    @Autowired private UserRepo userRepo;

    @Test
    void addPayment() {
        clearDb();

        User user = createUser();
        Order order = createOrder(user);

        long before = paymentRepo.count();

        PaymentDto dto = new PaymentDto();
        setField(dto, "amountDto", BigDecimal.valueOf(1000));
        setField(dto, "statusDto", "PAID");
        setField(dto, "methodDto", "CARD");


        setField(dto, "orderIdDto", order.getId());

        paymentService.addPayment(dto);

        assertThat(paymentRepo.count()).isEqualTo(before + 1);

        var lastPayment = paymentRepo.findAll().stream()
                .max((a, b) -> a.getId().compareTo(b.getId()))
                .orElseThrow();

        assertThat(lastPayment.getOrder()).isNotNull();
        assertThat(lastPayment.getOrder().getId()).isEqualTo(order.getId());
    }


    @Test
    void addPayment_usesOrderId() {
        clearDb();

        User user = createUser();
        Order order = createOrder(user);

        long before = paymentRepo.count();

        PaymentDto dto = new PaymentDto();
        setField(dto, "orderIdDto", order.getId());
        setField(dto, "amountDto", BigDecimal.valueOf(500));
        setField(dto, "statusDto", "PAID");
        setField(dto, "methodDto", "CASH");

        paymentService.addPayment(dto);

        assertThat(paymentRepo.count()).isEqualTo(before + 1);

        var lastPayment = paymentRepo.findAll().stream()
                .max((a, b) -> a.getId().compareTo(b.getId()))
                .orElseThrow();

        assertThat(lastPayment.getOrder()).isNotNull();
        assertThat(lastPayment.getOrder().getId()).isEqualTo(order.getId());
    }

    @Test
    void getById() {
        paymentRepo.deleteAll();

        Long id = 999999L;
        assertThatThrownBy(() -> paymentService.getById(id))
                .isInstanceOf(RuntimeException.class)
                .hasMessageContaining("Payment not found with id: " + id);
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

    private Order createOrder(User user) {
        Order o = new Order();
        o.setUser(user);
        o.setOrderDateTime(LocalDateTime.now());
        o.setStatus("NEW");
        o.setTotalAmount(BigDecimal.ZERO);
        return orderRepo.save(o);
    }

    private static void setField(Object obj, String field, Object value) {
        try {
            var f = obj.getClass().getDeclaredField(field);
            f.setAccessible(true);
            f.set(obj, value);
        } catch (Exception ignored) {}
    }
}
