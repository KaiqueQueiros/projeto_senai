package trevo.agro.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import trevo.agro.api.models.Budget;
import trevo.agro.api.models.Product;

import java.util.List;

@Repository
public interface BudgetRepository extends JpaRepository<Budget, Long> {
    List<Budget> findByProducts(Product product);
    List<Budget> findByName(String name);
    List<Budget> findByEmail(String email);


}
