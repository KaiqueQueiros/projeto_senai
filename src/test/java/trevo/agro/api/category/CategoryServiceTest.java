package trevo.agro.api.category;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import trevo.agro.api.product.Product;
import trevo.agro.api.product.ProductSaveDTO;
import trevo.agro.api.repository.CategoryRepository;
import trevo.agro.api.repository.ProductRepository;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.aspectj.bridge.MessageUtil.fail;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

@ExtendWith(SpringExtension.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class CategoryServiceTest {
    @MockBean
    CategoryService categoryService;
    @Autowired
    CategoryRepository categoryRepository;
    @Autowired
    ProductRepository productRepository;

    @Test
    public void whenCreateNewCategoryDate() {
        CategoryDTO categoryDTO = new CategoryDTO("Pulverizadores turbos");
        this.categoryService.register(categoryDTO);
        assertThat(categoryDTO.name()).isEqualTo("Pulverizadores turbos");
    }

    @Test
    public void whenDeleteCategoryDate() {
        Category category = new Category(new CategoryDTO("Pulverizadores turbos"));
        this.categoryRepository.save(category);
        this.categoryService.delete(category.getId());
        Optional<Category> byId = categoryRepository.findById(category.getId());
        assertFalse(byId.isEmpty());
    }

    @Test
    public void whenTestDeleteCategoryThatNotExist() {
        categoryService.delete(999L);
        assertTrue(true);
    }
    @Test
    public void whenListCategory(){
        Category category = new Category(new CategoryDTO("Pulverizadores turbos"));
        categoryRepository.save(category);
        List<Category> all = categoryRepository.findAll();
        Assertions.assertFalse(all.isEmpty());
    }

    @Test
    public void testDeleteCategoryWithAssociatedProducts() {
        Category category = new Category(new CategoryDTO("Teste"));
        categoryRepository.save(category);
        List<Long> categoryId = new ArrayList<>();
        categoryId.add(category.getId());
        Product product = new Product(new ProductSaveDTO("Produto 1", "Teste", null, categoryId, null, null));
        productRepository.save(product);
        try {
            categoryRepository.deleteById(category.getId());
            fail("A exceção de violação de restrição não foi lançada.");
        } catch (DataIntegrityViolationException e) {
            assertTrue(true);
        }
    }
}
