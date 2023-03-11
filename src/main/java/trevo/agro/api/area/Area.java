package trevo.agro.api.area;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Table(name = "tb_area_product")
public class Area {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
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
