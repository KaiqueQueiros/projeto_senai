package trevo.agro.api.product;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import trevo.agro.api.category.Category;

import java.net.URL;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Data
@Table(name = "tb_product")
@Entity
/*@SequenceGenerator(name = "Product_seq",
                    sequenceName = "Product_seq",
                    initialValue = 1,allocationSize = 1)*/
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY /*generator = "Product_seq"*/)
    @Column(name = "id")
    private Long id;
    @Column(name = "name")
    private String name;
    @Column(name = "description")
    private String description;
    @Column(name = "culture")
    private String culture;
    @Column(name = "area_size")
    private String area_size;
    @Column(name = "date")
    private LocalDateTime date;
    @Column(name = "img")
    private URL img;
    @OneToMany
    @JoinTable
            (
                    name = "TB_PRODUCT_CATEGORY",
                    joinColumns = {@JoinColumn(name = "product_id", referencedColumnName = "id")},
                    inverseJoinColumns = {@JoinColumn(name = "category_id", referencedColumnName = "id")}
            )
    private List<Category> categories;

    public Product(ProductDTO dados, List<Category> categories) {
        this.name = dados.name();
        this.area_size = dados.area_size();
        this.description = dados.description();
        this.date = LocalDateTime.now();
        this.culture = dados.culture();
        this.img = dados.img();
        this.categories = categories;

    }
}

