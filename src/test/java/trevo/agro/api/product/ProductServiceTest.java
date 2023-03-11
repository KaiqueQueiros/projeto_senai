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
        List<Long> imageList = new ArrayList<>();
        List<Long> areaList = new ArrayList<>();
        ProductSaveDTO product = new ProductSaveDTO("Condorito 400", "Criado para pulverização em barras",areaList,
                imageList, categoryList, cultureList);
        this.service.register(product);
        assertThat(product.description()).isEqualTo("Criado para pulverização em barras");
    }

    @Test
    public void whenDescription_thenPersistenseData() {
        List<Long> categoryList = new ArrayList<>();
        List<Long> cultureList = new ArrayList<>();
        List<Long> imageList = new ArrayList<>();
        List<Long> areaList = new ArrayList<>();
        ProductSaveDTO product = new ProductSaveDTO("Condorito 400", "Criado para pulverização em barras", areaList,
                imageList, categoryList, cultureList);
        this.service.register(product);
        assertThat(product.name()).isEqualTo("Condorito 400");
    }

    @Test
    public void whenAreaSize_thenPersistenseData() {
        List<Long> categoryList = new ArrayList<>();
        List<Long> cultureList = new ArrayList<>();
        List<Long> imageList = new ArrayList<>();
        List<Long> areaList = new ArrayList<>();
        ProductSaveDTO product = new ProductSaveDTO("Condorito 400", "Criado para pulverização em barras", areaList,
                imageList, categoryList, cultureList);
        this.service.register(product);
    }

}
