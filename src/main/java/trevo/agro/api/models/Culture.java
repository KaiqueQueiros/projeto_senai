package trevo.agro.api.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;
import trevo.agro.api.dto.CultureDTO;
import trevo.agro.api.exceptions.models.NotFoundException;

@Entity
@Table(name = "tb_culture")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class Culture {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
<<<<<<< HEAD:src/main/java/trevo/agro/api/models/Culture.java
    @Column(name = "name",unique = true,nullable = false)
=======
    @Column(name = "name", unique = true)
>>>>>>> master:src/main/java/trevo/agro/api/culture/Culture.java
    @Length(max = 20)
    @NotEmpty(message = "O campo nome da cultura é obrigatorio")
    private String name;

    public Culture(CultureDTO dto) {
        this.name = dto.name();
    }

    public void update(CultureDTO dto) {
        if (dto.name() != null) {
            this.name = dto.name();
        }
        if (dto.name() == null) {
            throw new NotFoundException("Informe o nome que deseja atualizar");
        }
    }
}
