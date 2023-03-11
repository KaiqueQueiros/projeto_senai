package trevo.agro.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import trevo.agro.api.area.Area;
import trevo.agro.api.category.Category;
import trevo.agro.api.product.Product;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    List<Product> findByIdIn(List<Long> products);
    Boolean existsByName(String name);
//    List<Product> findByCategory(Category category);
    List<Product> findByAreas(Area areas);

}
