package trevo.agro.api.product;

import java.net.URL;
import java.time.LocalDateTime;

public record detailsProduct(Long id, String name, String area_size, LocalDateTime date, String culture, URL img, trevo.agro.api.category.Category category_id) {
    public detailsProduct(Product product){
        this((Long) product.getId(),product.getName(),product.getArea_size(),product.getDate(),product.getCulture(),product.getImg(),product.getCategory());
    }
}
