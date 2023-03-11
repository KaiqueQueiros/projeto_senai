package trevo.agro.api.product;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import trevo.agro.api.area.Area;
import trevo.agro.api.category.Category;
import trevo.agro.api.culture.Culture;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor

public class ProductImgDto {
    String name;
    String description;
    LocalDate date;
    List<Category> categories;
    List<Culture> cultures;
    List<Area> areas;
    List<String> urlImages;

    public ProductImgDto(Product product, List<String> urlImages) {
        this.name = product.getName();
        this.description = product.getDescription();
        this.date = product.getDate();
        this.categories = product.getCategories();
        this.cultures = product.getCultures();
        this.areas = product.getAreas();
        this.urlImages = urlImages;
    }

}
