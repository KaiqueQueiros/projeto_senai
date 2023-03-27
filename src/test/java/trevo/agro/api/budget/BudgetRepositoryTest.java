package trevo.agro.api.budget;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import trevo.agro.api.dto.BudgetDTO;
import trevo.agro.api.models.Budget;
import trevo.agro.api.repository.BudgetRepository;
import java.util.ArrayList;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(SpringExtension.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class BudgetRepositoryTest {
    @Autowired
    BudgetRepository budgetRepository;

    @Test
    public void whenCreate_thenPersistenseData() {
        Budget budget = new Budget(new BudgetDTO("Antonio da silva", "antonio.123@gmail.com"
                , "123456789", "Argentina", "Jacto",null));
        budgetRepository.save(budget);
        assertThat(budget.getId()).isNotNull();
        assertThat(budget.getName()).isEqualTo("Antonio da silva");
        assertThat(budget.getEmail()).isEqualTo("antonio.123@gmail.com");
        assertThat(budget.getPhone()).isEqualTo("123456789");
        assertThat(budget.getCountry()).isEqualTo("Argentina");
        assertThat(budget.getCompany()).isEqualTo("Jacto");
    }

    @Test
    public void whenDeleteSholdBudget() {
        Budget budget = new Budget(new BudgetDTO("Antonio da silva", "antonio.123@gmail.com"
                , "123456789", "Argentina", "Jacto", new ArrayList<>()));
        Budget save = budgetRepository.save(budget);
        budgetRepository.deleteById(save.getId());
        assertThat(budgetRepository.findById(save.getId())).isEmpty();
    }

    @Test
    public void whenUpdateNameClient() {
        Budget budget = new Budget(new BudgetDTO("Antonio da silva", "antonio.123@gmail.com"
                , "123456789", "Argentina", "Jacto", null));
        budgetRepository.save(budget);
        budget.setName("Agnaldo timotio");
        budget = this.budgetRepository.save(budget);
        assertThat(budget.getName()).isEqualTo("Agnaldo timotio");
    }

    @Test
    public void whenUpdateEmailClient() {
        Budget budget = new Budget(new BudgetDTO("Antonio da silva", "antonio.123@gmail.com"
                , "123456789", "Argentina", "Jacto", null));
        budgetRepository.save(budget);
        budget.setEmail("agnaldo.@gmail.com");
        budget = this.budgetRepository.save(budget);
        assertThat(budget.getEmail()).isEqualTo("agnaldo.@gmail.com");
    }

    @Test
    public void whenUpdateCountryClient() {
        Budget budget = new Budget(new BudgetDTO("Antonio da silva", "antonio.123@gmail.com"
                , "123456789", "Argentina", "Jacto", null));
        budgetRepository.save(budget);
        budget.setCountry("Portugal");
        budget = this.budgetRepository.save(budget);
        assertThat(budget.getCountry()).isEqualTo("Portugal");
    }

    @Test
    public void whenUpdateCompanyClient() {
        Budget budget = new Budget(new BudgetDTO("Antonio da silva", "antonio.123@gmail.com"
                , "123456789", "Argentina", "Jacto", null));
        budgetRepository.save(budget);
        budget.setCompany("Trevo");
        budget = this.budgetRepository.save(budget);
        assertThat(budget.getCompany()).isEqualTo("Trevo");
    }

}
