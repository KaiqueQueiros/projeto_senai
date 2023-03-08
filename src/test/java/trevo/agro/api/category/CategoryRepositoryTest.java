package trevo.agro.api.category;

import jakarta.validation.ConstraintViolationException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import trevo.agro.api.repository.CategoryRepository;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

@ExtendWith(SpringExtension.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class CategoryRepositoryTest {
    @Autowired
    CategoryRepository categoryRepository;

    @Test
    public void whenCreate_thenPersistenseData() {
        Category category = new Category(new CategoryDTO("Pulverizador com barras"));
        this.categoryRepository.save(category);
        assertThat(category.getId()).isNotNull();
         assertThat(category.getName()).isEqualTo("Pulverizador com barras");
    }

    @Test
    public void whenDeleteShouldRemoveData() {
        Category category = new Category(new CategoryDTO("Pulverizador com barras"));
        this.categoryRepository.save(category);
        categoryRepository.delete(category);
        assertThat(categoryRepository.findById(category.getId())).isEmpty();
    }

    @Test
    public void whenUpdateShouldChandAndPersistData() {
        Category category = new Category(new CategoryDTO("Pulverizador"));
        this.categoryRepository.save(category);
        category.setName("Pulverizador de barras");
        category = this.categoryRepository.save(category);
        assertThat(category.getName()).isEqualTo("Pulverizador de barras");
    }
    @Test
    public void whenNotEmptyName_thenNoConstraintViolations() {
        Exception exception = assertThrows(
                ConstraintViolationException.class,
                () -> categoryRepository.save(new Category(new CategoryDTO(""))));
        assertTrue(exception.getMessage().contains("O campo nome da categoria é obrigatorio"));
    }

}
