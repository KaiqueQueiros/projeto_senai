package trevo.agro.api.product;
import java.net.URL;
import jakarta.persistence.*;
import jakarta.persistence.Table;
import lombok.*;
import java.time.LocalDateTime;

@Table(name = "tb_product")
@Entity(name = "Product")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Product {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String area_size;
    private String description;
    private LocalDateTime date;
    private String culture;
    private URL img;

    public Product(product_date dados) {
        this.name = dados.name();
        this.area_size = dados.area_size();
        this.description = dados.description();
        this.date = LocalDateTime.now();
        this.culture = dados.culture();
        this.img = dados.img();

    }
}

