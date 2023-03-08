package trevo.agro.api.product;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;
import trevo.agro.api.category.Category;
import trevo.agro.api.culture.Culture;
import trevo.agro.api.image.Image;
import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@Table(name = "tb_product")
@Entity
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column(name = "name", unique = true, nullable = false)
    @Length(max = 50)
    private String name;
    @Column(name = "description", columnDefinition = "Text", nullable = false)
    private String description;
    @Column(name = "areaSize", nullable = false)
    @Length(max = 50)
    private String areaSize;
    @Column(name = "date")
    private LocalDate date;
    @ManyToMany
    @JoinTable
            (
            name = "TB_PRODUCT_CATEGORY",
            joinColumns = {@JoinColumn(name = "product_id", referencedColumnName = "id")},
            inverseJoinColumns = {@JoinColumn(name = "category_id", referencedColumnName = "id")}
            )
    private List<Category> categories;
    @ManyToMany
    @JoinTable(
            name = "TB_PRODUCT_CULTURE",
            joinColumns = {@JoinColumn(name = "product_id", referencedColumnName = "id")},
            inverseJoinColumns = {@JoinColumn(name = "culture_id", referencedColumnName = "id")}

                )
    private List<Culture> cultures;
    @ManyToMany
    @JoinTable(
            name = "TB_PRODUCT_IMAGE",
            joinColumns = {@JoinColumn(name = "product_id", referencedColumnName = "id")},
            inverseJoinColumns = {@JoinColumn(name = "image_id", referencedColumnName = "id")}

    )
    private List<Image> images;
    private Boolean active;

    public Product(ProductSaveDTO dto, List<Category> categories, List<Culture> cultures, List<Image> images) {
        this.name = dto.getName();
        this.areaSize = dto.getAreaSize();
        this.description = dto.getDescription();
        this.date = LocalDate.now();
        this.categories = categories;
        this.cultures = cultures;
        this.images = images;
        this.active = true;
    }

    public Product(ProductSaveDTO dto) {
        this.name = dto.getName();
        this.areaSize = dto.areaSize();
        this.description = dto.description();
        this.date = LocalDate.now();
        this.active = true;
    }


    public void update(ProductSaveDTO dto, List<Category> categories, List<Culture> cultures) {
        if (dto.name() != null) {
            this.name = dto.name();
        }
        if (dto.areaSize() != null) {
            this.areaSize = dto.areaSize();
        }
        if (dto.description() != null) {
            this.description = dto.description();
        }
        if (categories != null) {
            this.categories = categories;
        }
        if (cultures != null) {
            this.cultures = cultures;
        }
    }
}

