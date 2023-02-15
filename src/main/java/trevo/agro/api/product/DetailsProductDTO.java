package trevo.agro.api.product;

import java.net.URL;
import java.time.LocalDateTime;

public record DetailsProductDTO(Long id, String name, String area_size, LocalDateTime date, String culture, URL img) {
    public DetailsProductDTO(Product product) {
        this((Long) product.getId(), product.getName(), product.getArea_size(), product.getDate(), product.getCulture(), product.getImg());
    }
}
