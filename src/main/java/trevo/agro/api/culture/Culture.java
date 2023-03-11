package trevo.agro.api.culture;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;
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
    @Column(name = "name")
    @Length(max = 20)
    private String name;

    public Culture(CultureDTO dto) {
        this.name = dto.name();
    }

    public void update(CultureDTO dto) {
        if (dto.name() != null) {
            this.name = dto.name().trim();
        }
        if (dto.name() == null) {
            throw new NotFoundException("Informe o nome que deseja atualizar");
        }
    }
}
