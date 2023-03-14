package trevo.agro.api.product;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import trevo.agro.api.area.Area;
import trevo.agro.api.category.Category;
import trevo.agro.api.culture.Culture;
import trevo.agro.api.image.Image;
import trevo.agro.api.repository.*;

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
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private CategoryRepository categoryRepository;
    @Autowired
    private CultureRepository cultureRepository;
    @Autowired
    private ImageRepository imageRepository;
    @Autowired
    private AreaRepository areaRepository;

    @Test
    public void whenName_thenPersistenseData() {
        List<Long> categories = new ArrayList<>();
        List<Long> cultures = new ArrayList<>();
        List<Long> images = new ArrayList<>();
        List<Long> areas = new ArrayList<>();
        ProductSaveDTO product = new ProductSaveDTO("Uniport 5030 NPK", "O SmartSet é uma ferramenta construída para auxiliar você no ajuste da faixa de aplicação do seu Uniport 5030 NPK. Em quatro passos fáceis, você obtém os dados de regulagem necessários para uma diversidade de fertilizantes.",areas,
                images, categories, cultures);
        this.service.register(product);
        assertThat(product.name()).isEqualTo("Uniport 5030 NPK");
    }

    @Test
    public void whenDescription_thenPersistenseData() {
        List<Long> categories = new ArrayList<>();
        List<Long> cultures = new ArrayList<>();
        List<Long> images = new ArrayList<>();
        List<Long> areas = new ArrayList<>();
        ProductSaveDTO product = new ProductSaveDTO("Uniport 5030 NPK", "O SmartSet é uma ferramenta construída para auxiliar você no ajuste da faixa de aplicação do seu Uniport 5030 NPK. Em quatro passos fáceis, você obtém os dados de regulagem necessários para uma diversidade de fertilizantes.",areas,
                images, categories, cultures);
        this.service.register(product);
        assertThat(product.description()).isEqualTo("O SmartSet é uma ferramenta construída para auxiliar você no ajuste da faixa de aplicação do seu Uniport 5030 NPK. Em quatro passos fáceis, você obtém os dados de regulagem necessários para uma diversidade de fertilizantes.");
    }


}
