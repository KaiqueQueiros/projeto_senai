package trevo.agro.api.product;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import java.util.ArrayList;
import java.util.List;
import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(SpringExtension.class)
@DataJpaTest
@AutoConfigureMockMvc
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class ProductServiceTest {
    @MockBean
    ProductService service;

    @Test
    public void whenCreate_thenPersistenseData() {
        List<Long> categoryList = new ArrayList<>();
        List<Long> cultureList = new ArrayList<>();
        ProductDTO product = new ProductDTO("Condorito 400", "Criado para pulverização em barras", "500",
                "www.google.com.br", categoryList, cultureList);
        this.service.register(product);
        assertThat(product.getDescription()).isEqualTo("Criado para pulverização em barras");
    }

    @Test
    public void whenDescription_thenPersistenseData() {
        List<Long> categoryList = new ArrayList<>();
        List<Long> cultureList = new ArrayList<>();
        ProductDTO product = new ProductDTO("Condorito 400", "Criado para pulverização em barras", "500",
                "www.google.com.br", categoryList, cultureList);
        this.service.register(product);
        assertThat(product.getName()).isEqualTo("Condorito 400");
    }

    @Test
    public void whenAreaSize_thenPersistenseData() {
        List<Long> categoryList = new ArrayList<>();
        List<Long> cultureList = new ArrayList<>();
        ProductDTO product = new ProductDTO("Condorito 400", "Criado para pulverização em barras", "500",
                "www.google.com.br", categoryList, cultureList);
        this.service.register(product);
        assertThat(product.getArea_size()).isEqualTo("500");
    }

    @Test
    public void whenImage_thenPersistenseData() {
        List<Long> categoryList = new ArrayList<>();
        List<Long> cultureList = new ArrayList<>();
        ProductDTO product = new ProductDTO("Condorito 400", "Criado para pulverização em barras", "500",
                "www.google.com.br", categoryList, cultureList);
        this.service.register(product);
        assertThat(product.getImg()).isEqualTo("www.google.com.br");
    }
}
