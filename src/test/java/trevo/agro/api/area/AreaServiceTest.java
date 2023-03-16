package trevo.agro.api.area;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import trevo.agro.api.repository.AreaRepository;

import java.util.Optional;

@ExtendWith(SpringExtension.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class AreaServiceTest {
    @MockBean
    AreaService areaService;
    @Autowired
    private AreaRepository areaRepository;

    @Test
    public void whenCreate_thenNamePersistenseData() {
        AreaDTO areaDTO = new AreaDTO("1001");
        areaService.register(areaDTO);
        Assertions.assertEquals("1001", areaDTO.size());
    }

    @Test
    public void whenCreateBudget_thenDeleteBudget() {
        Area area = new Area(new AreaDTO("100"));
        areaRepository.save(area);
        areaRepository.deleteById(area.getId());
        Optional<Area> byId = areaRepository.findById(area.getId());
        Assertions.assertTrue(byId.isEmpty());
    }
}
