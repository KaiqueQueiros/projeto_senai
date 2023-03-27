package trevo.agro.api.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;
import trevo.agro.api.dto.CategoryDTO;
import trevo.agro.api.exceptions.models.NotFoundException;

@Getter
@Setter
@NoArgsConstructor
@Table(name = "tb_category")
@Entity
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @NotEmpty(message = "O campo nome da categoria é obrigatorio")
    @Column(name = "name",unique = true,nullable = false)
    @Length(max = 30)
    private String name;


    public Category(CategoryDTO dto) {
        this.name = dto.name();
    }

    public void update(CategoryDTO dto) {
        if (dto.name() != null) {
            this.name = dto.name().trim();
        }
        if (dto.name() == null) {
            throw new NotFoundException(" Informe o nome de categoria que deseja atualizar");
        }
    }

}



