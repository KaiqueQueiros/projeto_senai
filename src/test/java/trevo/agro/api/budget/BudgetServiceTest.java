package trevo.agro.api.budget;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import trevo.agro.api.repository.BudgetRepository;

import java.util.Optional;

@ExtendWith(SpringExtension.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class BudgetServiceTest {
    @MockBean
    BudgetService budgetService;
    @Autowired
    private BudgetRepository budgetRepository;

    @Test
    public void whenCreate_thenNamePersistenseData() {
        BudgetDTO budget = new BudgetDTO("Antonio da silva", "antonio.123@gmail.com"
                , "123456789", "Argentina", "Jacto", null);
        budgetService.register(budget);
        Assertions.assertEquals("Antonio da silva", budget.name());
    }

    @Test
    public void whenCreateBudget_thenDeleteBudget() {
        Budget budget = new Budget(new BudgetDTO("Antonio da silva", "antonio.123@gmail.com"
                , "123456789", "Argentina", "Jacto", null));
        budgetRepository.save(budget);
        budgetRepository.deleteById(budget.getId());
        Optional<Budget> byId = budgetRepository.findById(budget.getId());
        Assertions.assertTrue(byId.isEmpty());
    }
}
