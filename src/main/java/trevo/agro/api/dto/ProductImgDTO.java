package trevo.agro.api.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import trevo.agro.api.models.Area;
import trevo.agro.api.models.Category;
import trevo.agro.api.models.Culture;
import trevo.agro.api.models.Product;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor

public class ProductImgDTO {
    Long id;
    String name;
    String description;
    LocalDate date;
    List<Category> categories;
    List<Culture> cultures;
    List<Area> areas;
    List<String> urlImages;
    Boolean status;

    public ProductImgDTO(Product product, List<String> urlImages) {
        this.id = product.getId();
        this.name = product.getName();
        this.description = product.getDescription();
        this.date = product.getDate();
        this.categories = product.getCategories();
        this.cultures = product.getCultures();
        this.areas = product.getAreas();
        this.urlImages = urlImages;
        this.status = product.getActive();
    }

}
