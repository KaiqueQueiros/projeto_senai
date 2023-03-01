package trevo.agro.api.culture;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@AutoConfigureMockMvc
public class CultureServiceTest {
    @Autowired
    private CultureService cultureService;
    @Autowired
    private CultureRepository cultureRepository;

//    @Test
//    public void deveraCadastrarCultura() {
//        cultureService.register(new CultureDTO("Cereais"));
//        Culture culture = cultureRepository.findById(5L).orElse(null);
//        Assertions.assertEquals("CEREAIS", culture.getName().trim());
//    }
}
