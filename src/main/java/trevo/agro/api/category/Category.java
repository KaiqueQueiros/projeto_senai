package trevo.agro.api.category;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import trevo.agro.api.culture.CultureDTO;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "tb_category")
@Entity
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column(name = "name",unique = true)
    private String name;


    public Category(CategoryDTO dto) {
        this.name = dto.getName().toUpperCase();
    }

    public void update(CultureDTO dto) {
        if (dto.name() != null){
            this.name = dto.getName().toUpperCase();
        }
    }
}



