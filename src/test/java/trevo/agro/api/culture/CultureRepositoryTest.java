package trevo.agro.api.culture;

import jakarta.validation.ConstraintViolationException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import trevo.agro.api.repository.CultureRepository;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

@ExtendWith(SpringExtension.class)
@DataJpaTest
@AutoConfigureMockMvc
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class CultureRepositoryTest {
    @Autowired
    CultureRepository cultureRepository;

    @Test
    public void whenCreate_thenPersistenseData() {
        Culture culture = new Culture(new CultureDTO("Cereais"));
        this.cultureRepository.save(culture);
        assertThat(culture.getId()).isNotNull();
        assertThat(culture.getName()).isEqualTo("Cereais");
    }

    @Test
    public void whenDeleteShouldRemoveData() {
        Culture culture = new Culture(new CultureDTO("Cereais"));
        this.cultureRepository.save(culture);
        cultureRepository.delete(culture);
        assertThat(cultureRepository.findById(culture.getId())).isEmpty();
    }

    @Test
    public void whenUpdateShouldChandAndPersistData() {
        Culture culture = new Culture(new CultureDTO("Cerais"));
        this.cultureRepository.save(culture);
        culture.setName("Cereais");
        culture = this.cultureRepository.save(culture);
        assertThat(culture.getName()).isEqualTo("Cereais");
    }

    @Test
    public void whenNotEmptyName_thenNoConstraintViolations() {
        Exception exception = assertThrows(
                ConstraintViolationException.class,
                () -> cultureRepository.save(new Culture(new CultureDTO(""))));
        assertTrue(exception.getMessage().contains("O campo nome da cultura é obrigatorio"));
    }

}
