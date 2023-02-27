package trevo.agro.api.culture;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import trevo.agro.api.category.CategoryRepository;
@SpringBootTest
public class CultureServiceTest {
    @Autowired
    private CultureService cultureService;
    @Autowired
    private CultureRepository cultureRepository;
    @Test
    public void deveraCadastrarCultura(){
        cultureService.register(new CultureDTO("Cereais"));
        Culture culture = cultureRepository.findById(1L).orElse(null);
        Assertions.assertEquals("CEREAIS",culture.getName().trim());
    }
}
