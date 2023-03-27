package trevo.agro.api.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;
import trevo.agro.api.dto.AreaDTO;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Table(name = "tb_area")
@Entity
public class Area {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "size",unique = true,nullable = false)
    @Length(max = 10)
    @NotEmpty(message = "Tamanho de area obrigatoria")
    private String size;

    public Area(AreaDTO dto){
        this.size = dto.size();
    }

    public void update(AreaDTO dto) {
        if (dto.size() != null){
            this.size = dto.size();
        }
    }
}