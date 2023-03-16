package trevo.agro.api.product;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import trevo.agro.api.repository.ProductRepository;
import java.util.ArrayList;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(SpringExtension.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class ProductServiceTest {
    @MockBean
    ProductService service;
    @Autowired
    ProductRepository productRepository;

    @Test
    public void whenName_thenPersistenseData() {
        ProductSaveDTO product = new ProductSaveDTO("Uniport 5030 NPK", "O SmartSet é uma ferramenta construída para auxiliar você no ajuste da faixa de aplicação do seu Uniport 5030 NPK. " +
                "Em quatro passos fáceis, você obtém os dados de regulagem necessários para uma diversidade de fertilizantes.", null,
                null, null, null);
        this.service.register(product);
        Assertions.assertEquals("Uniport 5030 NPK", product.name());
    }

    @Test
    public void whenDescription_thenPersistenseData() {
        ProductSaveDTO product = new ProductSaveDTO("Uniport 5030 NPK", "O SmartSet é uma ferramenta construída para auxiliar você no ajuste da faixa de aplicação do seu Uniport 5030 NPK. Em quatro passos fáceis, você obtém os dados de regulagem necessários para uma diversidade de fertilizantes.", new ArrayList<>(),
                new ArrayList<>(), new ArrayList<>(), new ArrayList<>());
        this.service.register(product);
        assertThat(product.description()).isEqualTo("O SmartSet é uma ferramenta construída para auxiliar você no ajuste da faixa de aplicação do seu Uniport 5030 NPK. " +
                "Em quatro passos fáceis, você obtém os dados de regulagem necessários para uma diversidade de fertilizantes.");
    }

    @Test
    public void whenCreateProduct_thenDeleteProduct() {
        Product product = new Product(new ProductSaveDTO("Uniport 5030", "O SmartSet é uma ferramenta construída para auxiliar você no ajuste da faixa de " +
                "aplicação do seu Uniport 5030 NPK. Em quatro passos fáceis, você obtém os dados de regulagem necessários para uma diversidade de fertilizantes.", null,
                null, null, null));
        productRepository.save(product);
        productRepository.deleteById(product.getId());
        Optional<Product> byId = productRepository.findById(product.getId());
        Assertions.assertTrue(byId.isEmpty());

    }
}
