package trevo.agro.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import trevo.agro.api.budget.Budget;

@Repository
public interface BudgetRepository extends JpaRepository<Budget, Long> {

}
