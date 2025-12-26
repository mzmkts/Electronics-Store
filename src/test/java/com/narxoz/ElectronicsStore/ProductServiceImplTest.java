package com.narxoz.ElectronicsStore;

import com.narxoz.ElectronicsStore.Dto.ProductDto;
import com.narxoz.ElectronicsStore.Model.Category;
import com.narxoz.ElectronicsStore.Model.Product;
import com.narxoz.ElectronicsStore.Repository.CategoryRepo;
import com.narxoz.ElectronicsStore.Repository.ProductRepo;
import com.narxoz.ElectronicsStore.Service.ProductService;
import jakarta.persistence.EntityManager;
import org.springframework.transaction.annotation.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.lang.reflect.Field;
import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;


@SpringBootTest
@Transactional
public class ProductServiceImplTest {

    @Autowired
    private ProductService productService;

    @Autowired
    private ProductRepo productRepo;

    @Autowired
    private CategoryRepo categoryRepo;

    @Autowired private EntityManager em;

    @Test
    void addProduct_and_getAll() {
        productRepo.deleteAll();

        ProductDto dto = new ProductDto();
        dto.setProductNameDto("Phone");
        dto.setProductPriceDto(new BigDecimal("1000.00"));
        dto.setProductDescriptionDto("Test desc");

        productService.addProduct(dto);

        var all = productService.getAll();

        assertNotNull(all);
        assertTrue(all.size() >= 1);
        assertEquals("Phone", all.get(all.size() - 1).getProductNameDto());
        assertEquals(0, all.get(all.size() - 1).getProductPriceDto().compareTo(new BigDecimal("1000.00")));
    }

    @Test
    void getById() {
        productRepo.deleteAll();

        Product saved = new Product();
        saved.setProductName("Phone");
        saved.setProductPrice(new BigDecimal("123.45"));
        saved.setProductDescription("Desc");
        saved = productRepo.save(saved);

        ProductDto dto = productService.getById(saved.getId());

        assertNotNull(dto);
        assertEquals(saved.getId(), dto.getProductIdDto());
        assertEquals("Phone", dto.getProductNameDto());
        assertEquals(0, dto.getProductPriceDto().compareTo(new BigDecimal("123.45")));
        assertEquals("Desc", dto.getProductDescriptionDto());
    }
    @Test
    void getById_test() {
        productRepo.deleteAll();
        assertThrows(RuntimeException.class, () -> productService.getById(999999L));
    }

    @Test
    void updateProduct() {
        productRepo.deleteAll();
        categoryRepo.deleteAll();

        Category c = new Category();
        c.setCategoryName("TestCat");
        c = categoryRepo.save(c);

        Product saved = new Product();
        saved.setProductName("Old");
        saved.setProductPrice(new BigDecimal("10.00"));
        saved.setProductDescription("OldDesc");
        saved.setCategory(c);
        saved = productRepo.save(saved);

        ProductDto upd = new ProductDto();
        upd.setProductNameDto("NewName");
        upd.setProductPriceDto(new BigDecimal("777.00"));
        upd.setProductDescriptionDto("NewDesc");

        // если у ProductDto есть поле categoryIdDto/CategoryIdDto — проставим рефлексией (не ломает проект)
        setIfExists(upd, "categoryIdDto", c.getId());
        setIfExists(upd, "productCategoryIdDto", c.getId()); // на всякий случай, если у тебя другое имя

        productService.updateProduct(saved.getId(), upd);

        em.flush();
        em.clear();

        Product after = productRepo.findById(saved.getId()).orElseThrow();

        assertEquals("NewName", after.getProductName());
        assertEquals(0, after.getProductPrice().compareTo(new BigDecimal("777.00")));
        assertEquals("NewDesc", after.getProductDescription());
    }
    @Test
    void deleteProduct_deletesById() {
        productRepo.deleteAll();

        Product saved = new Product();
        saved.setProductName("ToDelete");
        saved.setProductPrice(new BigDecimal("1.00"));
        saved.setProductDescription("D");
        saved = productRepo.save(saved);

        productService.deleteProduct(saved.getId());

        assertFalse(productRepo.findById(saved.getId()).isPresent());
    }
    private static void setIfExists(Object target, String fieldName, Object value) {
        try {
            Field f = target.getClass().getDeclaredField(fieldName);
            f.setAccessible(true);
            f.set(target, value);
        } catch (NoSuchFieldException ignored) {
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}

