package trevo.agro.api.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import jakarta.validation.Valid;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;
import trevo.agro.api.exceptions.models.BadRequestException;
import trevo.agro.api.dto.ProductSaveDTO;

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
    @Valid
    @Length(max = 50)
    private String name;
    @Column(name = "description", columnDefinition = "Text", nullable = false)
    private String description;
    @Column(name = "date")
    private LocalDate date;
    @ManyToMany
    @JoinTable(
            name = "TB_PRODUCT_AREA",
            joinColumns = {@JoinColumn(name = "product_id", referencedColumnName = "id")},
            inverseJoinColumns = {@JoinColumn(name = "area_id", referencedColumnName = "id")}
    )
    private List<Area> areas;

    @ManyToMany
    @JoinTable(
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
    @OneToMany
    @JoinTable(
            name = "TB_PRODUCT_IMAGE",
            joinColumns = {@JoinColumn(name = "product_id", referencedColumnName = "id")},
            inverseJoinColumns = {@JoinColumn(name = "image_id", referencedColumnName = "id")}
    )
    @JsonIgnoreProperties({"imageData", "type"})
    private List<Image> images;
    private Boolean active;


    public Product(ProductSaveDTO dto, List<Category> categories, List<Culture> cultures, List<Image> images, List<Area> areas) {
        this.name = dto.name();
        this.description = dto.description();
        this.date = LocalDate.now();
        if (areas.isEmpty()) {
            throw new BadRequestException("Informe uma area válida");
        }
        this.areas = areas;
        if (categories.isEmpty()) {
            throw new BadRequestException("Informe uma categoria válida");
        }
        this.categories = categories;
        if (cultures.isEmpty()) {
            throw new BadRequestException("Informe uma cultura válida");
        }
        this.cultures = cultures;
        if (images.isEmpty()) {
            throw new BadRequestException("Informe uma imagem válida");
        }
        this.images = images;
        this.active = true;
    }

    public Product(ProductSaveDTO dto) {
        this.name = dto.name();
        this.description = dto.description();
        this.date = LocalDate.now();
        this.active = true;
    }

    public void update(ProductSaveDTO dto) {
        if (dto.name() != null) {
            this.name = dto.name();
        }
        if (dto.description() != null) {
            this.description = dto.description();
        }
    }
}

