package com.narxoz.ElectronicsStore;

import com.narxoz.ElectronicsStore.Dto.CategoryDto;
import com.narxoz.ElectronicsStore.Repository.*;
import com.narxoz.ElectronicsStore.Service.CategoryService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.NoSuchElementException;

import static org.assertj.core.api.Assertions.*;

@SpringBootTest
public class CategoryServiceImplTest {

    @Autowired private CategoryService categoryService;

    @Autowired private CategoryRepo categoryRepo;
    @Autowired private ProductRepo productRepo;
    @Autowired private ReviewRepo reviewRepo;
    @Autowired private OrderItemRepo orderItemRepo;
    @Autowired private PaymentRepo paymentRepo;
    @Autowired private OrderRepo orderRepo;

    @Test
    void addCategory_and_getAll() {
        clearDb();

        CategoryDto dto = new CategoryDto();
        setField(dto, "categoryNameDto", "Cat_" + System.nanoTime());

        categoryService.addCategory(dto);

        assertThat(categoryService.getAll())
                .isNotEmpty()
                .anySatisfy(c -> assertThat(getField(c, "categoryNameDto")).isNotNull());
    }

    @Test
    void getById_returnsCategory_whenExists() {
        clearDb();

        CategoryDto dto = new CategoryDto();
        String name = "Cat_" + System.nanoTime();
        setField(dto, "categoryNameDto", name);
        categoryService.addCategory(dto);

        Long id = categoryRepo.findAll().stream()
                .map(c -> c.getId())
                .max(Long::compareTo)
                .orElseThrow();

        CategoryDto found = categoryService.getById(id);

        assertThat(found).isNotNull();
        assertThat(getField(found, "categoryNameDto")).isEqualTo(name);
    }

    @Test
    void updateCategory_updatesName() {
        clearDb();

        CategoryDto dto = new CategoryDto();
        setField(dto, "categoryNameDto", "Old_" + System.nanoTime());
        categoryService.addCategory(dto);

        Long id = categoryRepo.findAll().stream()
                .map(c -> c.getId())
                .max(Long::compareTo)
                .orElseThrow();

        CategoryDto upd = new CategoryDto();
        setField(upd, "categoryNameDto", "New_" + System.nanoTime());

        categoryService.updateCategory(id, upd);

        CategoryDto after = categoryService.getById(id);
        assertThat(getField(after, "categoryNameDto")).isEqualTo(getField(upd, "categoryNameDto"));
    }

    @Test
    void deleteCategory_deletesById() {
        clearDb();

        CategoryDto dto = new CategoryDto();
        setField(dto, "categoryNameDto", "Del_" + System.nanoTime());
        categoryService.addCategory(dto);

        Long id = categoryRepo.findAll().stream()
                .map(c -> c.getId())
                .max(Long::compareTo)
                .orElseThrow();

        categoryService.deleteCategory(id);

        assertThatThrownBy(() -> categoryService.getById(id))
                .isInstanceOf(NoSuchElementException.class);
    }


    private void clearDb() {
        // дочерние -> родительские (иначе FK)
        orderItemRepo.deleteAll();
        paymentRepo.deleteAll();
        reviewRepo.deleteAll();
        orderRepo.deleteAll();
        productRepo.deleteAll();
        categoryRepo.deleteAll();
    }

    private static void setField(Object obj, String field, Object value) {
        try {
            var f = obj.getClass().getDeclaredField(field);
            f.setAccessible(true);
            f.set(obj, value);
        } catch (Exception ignored) {}
    }

    private static Object getField(Object obj, String field) {
        try {
            var f = obj.getClass().getDeclaredField(field);
            f.setAccessible(true);
            return f.get(obj);
        } catch (Exception e) {
            return null;
        }
    }
}
