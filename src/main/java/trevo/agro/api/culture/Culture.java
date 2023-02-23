package trevo.agro.api.culture;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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
    private String name;

    public Culture(CultureDTO dto) {
        this.name = dto.getName().toUpperCase();
    }

    public void update(CultureDTO dto) {
        if (dto.name() != null){
            this.name = dto.getName().toUpperCase();
        }
    }
}
