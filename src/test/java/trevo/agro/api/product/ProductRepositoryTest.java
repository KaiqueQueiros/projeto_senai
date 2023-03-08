package trevo.agro.api.product;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import trevo.agro.api.repository.CategoryRepository;
import trevo.agro.api.repository.ProductRepository;

import java.util.ArrayList;
import java.util.List;
import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(SpringExtension.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class ProductRepositoryTest {
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    CategoryRepository categoryRepository;

    @Test
    public void whenCreate_thenPersistenseData() {
        List<Long> categoryList = new ArrayList<>();
        List<Long> cultureList = new ArrayList<>();
        List<Long> imageList = new ArrayList<>();
        Product product = new Product(new ProductSaveDTO("Condorito 400", "Criado para pulverização em barras", "500",
                 imageList,categoryList, cultureList));
        this.productRepository.save(product);
        assertThat(product.getId()).isNotNull();
        assertThat(product.getName()).isEqualTo("Condorito 400");
        assertThat(product.getDescription()).isEqualTo("Criado para pulverização em barras");
        assertThat(product.getAreaSize()).isEqualTo("500");

    }

    @Test
    public void whenDeleteShouldRemoveData() {
        List<Long> categoryList = new ArrayList<>();
        List<Long> cultureList = new ArrayList<>();
        List<Long> imageList = new ArrayList<>();
        Product product = new Product(new ProductSaveDTO("Condorito 400", "Criado para pulverização em barras", "500",
                imageList, categoryList, cultureList));
        this.productRepository.save(product);
        productRepository.delete(product);
        assertThat(categoryRepository.findById(product.getId())).isEmpty();
    }

    @Test
    public void whenUpdateNameShouldChandAndPersistData() {
        List<Long> categoryList = new ArrayList<>();
        List<Long> cultureList = new ArrayList<>();
        List<Long> imageList = new ArrayList<>();
        Product product = new Product(new ProductSaveDTO("Condorito 400", "Criado para pulverização em barras", "500",
                 imageList,categoryList, cultureList));
        this.productRepository.save(product);
        product.setName("Condor");
        product = this.productRepository.save(product);
        assertThat(product.getName()).isEqualTo("Condor");
    }

    @Test
    public void whenUpdateDescriptionShouldChandAndPersistData() {
        List<Long> categoryList = new ArrayList<>();
        List<Long> cultureList = new ArrayList<>();
        List<Long> imageList = new ArrayList<>();
        Product product = new Product(new ProductSaveDTO("Condorito 400", "Criado para pulverização em barras", "500",
                imageList,categoryList, cultureList));
        this.productRepository.save(product);
        product.setDescription("Teste de atualização de campo descrição");
        product = this.productRepository.save(product);
        assertThat(product.getDescription()).isEqualTo("Teste de atualização de campo descrição");
    }

    @Test
    public void whenUpdateArea_sizeShouldChandAndPersistData() {
        List<Long> categoryList = new ArrayList<>();
        List<Long> cultureList = new ArrayList<>();
        List<Long> imageList = new ArrayList<>();

        Product product = new Product(new ProductSaveDTO("Condorito 400", "Criado para pulverização em barras", "500",
                 imageList,categoryList, cultureList));
        this.productRepository.save(product);
        product.setAreaSize("Teste de atualização de campo area size");
        product = this.productRepository.save(product);
        assertThat(product.getAreaSize()).isEqualTo("Teste de atualização de campo area size");
    }

}
