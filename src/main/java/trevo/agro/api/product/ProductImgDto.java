package trevo.agro.api.product;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import trevo.agro.api.category.Category;
import trevo.agro.api.culture.Culture;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class ProductImgDto {
    String name;
    String description;
    String areaSize;
    List<Category> categories;
    List<Culture> cultures;
    List<String> urlImages;

    public ProductImgDto(Product product, List<String> urlImages) {
        this.name = product.getName();
        this.areaSize = product.getAreaSize();
        this.description = product.getDescription();
        this.categories = product.getCategories();
        this.cultures = product.getCultures();
        this.urlImages = urlImages;

    }
}
