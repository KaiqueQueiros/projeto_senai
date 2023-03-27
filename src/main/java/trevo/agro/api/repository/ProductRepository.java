package trevo.agro.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import trevo.agro.api.models.Area;
import trevo.agro.api.models.Category;
import trevo.agro.api.models.Culture;
import trevo.agro.api.models.Image;
import trevo.agro.api.models.Product;
import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    List<Product> findByIdIn(List<Long> products);

    Boolean existsByName(String name);

    List<Product> findByCategories(Category category);

    List<Product> findByAreas(Area area);

    List<Product> findByCultures(Culture culture);

    List<Product> findByImages(Image image);


}
