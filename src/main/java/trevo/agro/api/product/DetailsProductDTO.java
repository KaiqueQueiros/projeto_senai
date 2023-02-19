package trevo.agro.api.product;

import trevo.agro.api.category.Category;

import java.net.URL;
import java.time.LocalDate;

public record DetailsProductDTO(Long id, String name, String area_size, String description, LocalDate date, URL img) {
    public DetailsProductDTO(Product product) {
        this ((Long) product.getId(),
                product.getName(),
                product.getArea_size(),
                product.getDescription(),
                product.getDate(),
                product.getImg());

    }
}
