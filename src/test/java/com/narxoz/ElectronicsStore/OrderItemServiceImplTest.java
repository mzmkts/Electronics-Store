package com.narxoz.ElectronicsStore;

import com.narxoz.ElectronicsStore.Dto.OrderItemDto;
import com.narxoz.ElectronicsStore.Model.*;
import com.narxoz.ElectronicsStore.Repository.*;
import com.narxoz.ElectronicsStore.Service.OrderItemService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.Assertions.*;

@SpringBootTest
public class OrderItemServiceImplTest {

    @Autowired private OrderItemService orderItemService;

    @Autowired private OrderItemRepo orderItemRepo;
    @Autowired private PaymentRepo paymentRepo;
    @Autowired private ReviewRepo reviewRepo;
    @Autowired private OrderRepo orderRepo;
    @Autowired private ProductRepo productRepo;
    @Autowired private CategoryRepo categoryRepo;
    @Autowired private UserRepo userRepo;

    @Test
    void addOrderItem_and_getAll() {
        clearDb();

        User user = createUser();
        Order order = createOrder(user);
        Category category = createCategory();
        Product product = createProduct(category);

        OrderItemDto dto = new OrderItemDto();
        dto.setOrderIdDto(order.getId());
        dto.setProductIdDto(product.getId());
        dto.setQuantityDto(2);

        orderItemService.addOrderItem(dto);

        List<OrderItemDto> all = orderItemService.getAll();
        assertThat(all).isNotEmpty();
        assertThat(all.get(0).getQuantityDto()).isEqualTo(2);
    }

    @Test
    void getById_returnsItem_whenExists() {
        clearDb();

        User user = createUser();
        Order order = createOrder(user);
        Category category = createCategory();
        Product product = createProduct(category);

        OrderItem saved = new OrderItem();
        saved.setOrder(order);
        saved.setProduct(product);
        saved.setQuantity(7);
        saved = orderItemRepo.save(saved);

        OrderItemDto found = orderItemService.getById(saved.getId());

        assertThat(found).isNotNull();
        assertThat(found.getOrderItemIdDto()).isEqualTo(saved.getId());
        assertThat(found.getQuantityDto()).isEqualTo(7);
    }

    @Test
    void getById_throws_whenNotFound() {
        orderItemRepo.deleteAll();

        Long id = 999999L;
        assertThatThrownBy(() -> orderItemService.getById(id))
                .isInstanceOf(RuntimeException.class)
                .hasMessageContaining("Order item not found with id: " + id);
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

    private Category createCategory() {
        Category c = new Category();
        c.setCategoryName("cat_" + System.nanoTime());
        return categoryRepo.save(c);
    }

    private Product createProduct(Category category) {
        Product p = new Product();
        p.setProductName("p_" + System.nanoTime());
        p.setProductDescription("desc");
        p.setProductPrice(BigDecimal.valueOf(100));
        p.setCategory(category);
        return productRepo.save(p);
    }
}
