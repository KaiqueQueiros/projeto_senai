package trevo.agro.api.culture;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import trevo.agro.api.dto.CultureDTO;
import trevo.agro.api.models.Culture;
import trevo.agro.api.models.Product;
import trevo.agro.api.dto.ProductSaveDTO;
import trevo.agro.api.repository.CultureRepository;
import trevo.agro.api.repository.ProductRepository;
import trevo.agro.api.service.CultureService;

import java.util.ArrayList;
import java.util.List;

import static org.aspectj.bridge.MessageUtil.fail;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

@ExtendWith(SpringExtension.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class CultureServiceTest {
    @MockBean
    CultureService cultureService;
    @Autowired
    CultureRepository cultureRepository;
    @Autowired
    private ProductRepository productRepository;


    @Test
    public void whenCreate_SholdPersisteseData() {
        CultureDTO culture = (new CultureDTO("Abacate"));
        cultureService.register(culture);
        assertThat(culture.name()).isEqualTo("Abacate");
    }


    @Test
    public void whenDeleteCulture() {
        Culture culture = new Culture (new CultureDTO("Abacate"));
        cultureRepository.save(culture);

    }
    @Test
    public void whenDeleteCultureDate() {
        Culture culture = cultureRepository.save(new Culture(new CultureDTO("Semente")));
        cultureRepository.deleteById(culture.getId());
        assertTrue(cultureRepository.findById(culture.getId()).isEmpty());
    }
    @Test
    public void testDeleteCultureWithAssociatedProducts() {
        Culture culture = new Culture(new CultureDTO("Teste"));
        cultureRepository.save(culture);
        List<Long> cultureId = new ArrayList<>();
        cultureId.add(culture.getId());
        Product product = new Product(new ProductSaveDTO("Produto 1", "Teste", null, null, cultureId, null));
        productRepository.save(product);
        try {
            cultureRepository.deleteById(culture.getId());
            fail("A exceção de violação de restrição não foi lançada.");
        } catch (DataIntegrityViolationException e) {
            assertTrue(true);
        }
    }
}