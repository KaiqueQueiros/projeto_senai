package trevo.agro.api.area;

import jakarta.validation.ConstraintViolationException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import trevo.agro.api.dto.AreaDTO;
import trevo.agro.api.models.Area;
import trevo.agro.api.repository.AreaRepository;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

@ExtendWith(SpringExtension.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)

public class AreaRepositoryTest {
    @Autowired
    AreaRepository areaRepository;

    @Test
    public void whenCreate_thenPersistenseData() {
        Area area = new Area(new AreaDTO("500"));
        this.areaRepository.save(area);
        assertThat(area.getId()).isNotNull();
        assertThat(area.getSize()).isEqualTo("500");
    }

    @Test
    public void whenDeleteShouldRemoveData() {
        Area area = new Area(new AreaDTO("500"));
        this.areaRepository.save(area);
        areaRepository.deleteById(area.getId());
        assertThat(areaRepository.findById(area.getId())).isEmpty();
    }

    @Test
    public void whenUpdateShouldChandAndPersistData() {
        Area area = new Area(new AreaDTO("600"));
        this.areaRepository.save(area);
        area.setSize("850");
        area = this.areaRepository.save(area);
        assertThat(area.getSize()).isEqualTo("850");
    }

    @Test
    public void whenNotEmptyName_thenNoConstraintViolations() {
        Exception exception = assertThrows(
                ConstraintViolationException.class,
                () -> areaRepository.save(new Area(new AreaDTO(null))));
        assertTrue(exception.getMessage().contains("Tamanho de area obrigatoria"));
    }

    @Test
    public void whenDuplicateName_thenNoConstraintViolations() {
        Area areaDTO = new Area(new AreaDTO("1000"));
        Area areaDTO2 = new Area(new AreaDTO("1000"));
        areaRepository.save(areaDTO);
        assertThrows(DataIntegrityViolationException.class, () -> areaRepository.save(areaDTO2));
    }
}
