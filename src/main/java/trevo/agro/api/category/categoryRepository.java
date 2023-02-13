package trevo.agro.api.category;

import org.springframework.data.jpa.repository.JpaRepository;
import trevo.agro.api.category.Category;

public interface categoryRepository extends JpaRepository <Category,Long> {
}
