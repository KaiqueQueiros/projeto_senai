package trevo.agro.api.product;

import trevo.agro.api.category.Category;
import trevo.agro.api.culture.Culture;

import java.net.URL;
import java.time.LocalDate;
import java.util.List;

public record DetailsProductDTO(Long id, String name, String area_size, String description, LocalDate date, URL img, List<Category>categories,
                                List<Culture> cultures) {
    //Detalhes do produto
    public DetailsProductDTO(Product product) {
        this ((Long) product.getId(),
                product.getName(),
                product.getArea_size(),
                product.getDescription(),
                product.getDate(),
                product.getImg(),
                product.getCategories(),
                product.getCultures()
        );

    }
}
