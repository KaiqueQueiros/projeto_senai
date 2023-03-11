package trevo.agro.api.budget;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import trevo.agro.api.exceptions.models.NotFoundException;
import trevo.agro.api.product.Product;
import trevo.agro.api.repository.BudgetRepository;
import trevo.agro.api.repository.ProductRepository;
import trevo.agro.api.utils.ResponseModel;
import trevo.agro.api.utils.ResponseModelEspec;
import trevo.agro.api.utils.ResponseModelEspecNoObject;
import java.util.List;
import java.util.Optional;

@Service
public class BudgetService {
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private BudgetRepository budgetRepository;


    public ResponseEntity<ResponseModel> register(@RequestBody @Valid BudgetDTO dto) {

            List<Product> products = productRepository.findByIdIn(dto.productIds());
            Budget budget = new Budget(dto, products);
            budgetRepository.save(budget);
            return new ResponseEntity<>(new ResponseModelEspec("Registramos seu interesse em nossos produtos, entraremos em contato em breve!", dto), HttpStatus.OK);
    }

    public ResponseEntity<ResponseModel> list() {
        List<Budget> budgets = budgetRepository.findAll();
        if (budgets.isEmpty()) {
            throw new NotFoundException("Nenhum orçamento encontrado");
        }
        return new ResponseEntity<>(new ResponseModelEspec("Lista de orçamentos!", budgets), HttpStatus.OK);
    }

    public ResponseEntity<ResponseModel> details(@PathVariable Long id) {
            Optional<Budget> budget = budgetRepository.findById(id);
            if (budget.isEmpty()) {
                throw new NotFoundException("Nenhum orçamento encontrado");
            }
            return new ResponseEntity<>(new ResponseModelEspec("Detalhes do orçamento!", budget), HttpStatus.OK);
    }

    public ResponseEntity<ResponseModel> delete(@PathVariable Long id) {
        Optional<Budget> budget = budgetRepository.findById(id);
        if (budget.isEmpty()) {
            throw new NotFoundException("Nenhum orçamento encontrado");
        }
        budgetRepository.deleteById(id);
        return new ResponseEntity<>(new ResponseModelEspecNoObject("Orçamento excluido!"), HttpStatus.OK);
    }

    public ResponseEntity<ResponseModel> update(@Valid BudgetDTO dto, @PathVariable Long id) {
            List<Product> products = productRepository.findByIdIn(dto.productIds());
            if (!budgetRepository.existsById(id)) {
                return new ResponseEntity<>(new ResponseModelEspecNoObject("Orçamento não encontrado!"), HttpStatus.NOT_FOUND);
            }
            Budget budgetExists = budgetRepository.findById(id).orElse(null);
            assert budgetExists != null;
            budgetExists.update(dto, products);
            budgetRepository.save(budgetExists);
            return new ResponseEntity<>(new ResponseModelEspecNoObject("Orçamento foi atualizado!"), HttpStatus.OK);

    }
}