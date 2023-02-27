package trevo.agro.api.category;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import trevo.agro.api.culture.Culture;
import trevo.agro.api.culture.CultureDTO;
import trevo.agro.api.culture.CultureRepository;
import trevo.agro.api.culture.CultureService;
import trevo.agro.api.product.ProductRepository;

@SpringBootTest
public class CategoryServiceTest {
    @Autowired
    private CategoryService categoryService;
    @Autowired
    private CategoryRepository categoryRepository;
    @Autowired
    private CultureRepository cultureRepository;
    @Autowired
    private CultureService cultureService;

    @Test
    public void deveraCadastrarCategoria(){
        categoryService.register(new CategoryDTO("Pulverizador"));
        Category category = categoryRepository.findById(1L).orElse(null);
        Assertions.assertEquals("PULVERIZADOR",category.getName().trim());
    }

    @Test
    public void devera_cadastrar_produto_repetido(){

    }
}
