package trevo.agro.api.product;
import java.net.URL;
import jakarta.persistence.*;
import jakarta.persistence.Table;
import lombok.*;
import org.springframework.web.bind.annotation.GetMapping;
import trevo.agro.api.category.Category;
import trevo.agro.api.category.categoryDate;

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

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;

    public Product(product_date dados) {
        this.name = dados.name();
        this.area_size = dados.area_size();
        this.description = dados.description();
        this.date = LocalDateTime.now();
        this.culture = dados.culture();
        this.img = dados.img();
        this.category = dados.category();


    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getArea_size() {
        return area_size;
    }

    public void setArea_size(String area_size) {
        this.area_size = area_size;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }


    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public String getCulture() {
        return culture;
    }

    public void setCulture(String culture) {
        this.culture = culture;
    }

    public URL getImg() {
        return img;
    }

    public void setImg(URL img) {
        this.img = img;
    }


}

