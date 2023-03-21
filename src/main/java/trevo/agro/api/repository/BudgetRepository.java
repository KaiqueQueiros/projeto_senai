package trevo.agro.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import trevo.agro.api.budget.Budget;
import trevo.agro.api.product.Product;

import java.util.List;

@Repository
public interface BudgetRepository extends JpaRepository<Budget, Long> {
    List<Budget> findByProducts(Product product);
//    @Query("SELECT b FROM Budget b WHERE b.name LIKE %:name%")
    List<Budget> findByName(String name);

}
