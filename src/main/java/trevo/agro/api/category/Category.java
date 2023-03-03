package trevo.agro.api.category;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;
import trevo.agro.api.culture.CultureDTO;

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
    @Column(name = "name")
    @Length(max = 30)
    @NotBlank
    private String name;


    public Category(CategoryDTO dto) {
        this.name = dto.getName();
    }

    public void update(CategoryDTO dto) {
        if (dto.name() != null) {
            this.name = dto.getName().trim();
        }
    }

}



