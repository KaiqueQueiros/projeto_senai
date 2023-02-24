package trevo.agro.api.budget;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import trevo.agro.api.product.Product;
import trevo.agro.api.product.ProductRepository;
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
        try {
            List<Product> products = productRepository.findByIdIn(dto.productIds());
            if (products.isEmpty()) {
                return new ResponseEntity<>(new ResponseModelEspecNoObject("Nenhum produto foi selecionado!"), HttpStatus.BAD_REQUEST);
            }
            Budget budget = new Budget(dto, products);
            budgetRepository.save(budget);
            return new ResponseEntity<>(new ResponseModelEspec("Registramos seu interesse em nossos produtos, entraremos em contato em breve!", dto), HttpStatus.OK);

        } catch (Exception error) {
            error.printStackTrace();
        }
        return ResponseEntity.internalServerError().build();
    }

    public ResponseEntity<ResponseModel> list() {
        List<Budget> budgets = budgetRepository.findAll();
        if (budgets.isEmpty()) {
            return new ResponseEntity<>(new ResponseModelEspecNoObject("Listas de orçamentos vazia!"), HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(new ResponseModelEspec("Lista de orçamentos!", budgets), HttpStatus.OK);
    }

    public ResponseEntity<ResponseModel> details(@PathVariable Long id) {
        try {
            Optional<Budget> budget = budgetRepository.findById(id);
            if (budget.isEmpty()) {
                return new ResponseEntity<>(new ResponseModelEspecNoObject("Nenhum orçamento encontrado!"), HttpStatus.NOT_FOUND);
            }
            return new ResponseEntity<>(new ResponseModelEspec("Detalhes do orçamento!", budget), HttpStatus.OK);

        } catch (Error error) {
            error.printStackTrace();
        }
        return ResponseEntity.internalServerError().build();
    }

    public ResponseEntity<ResponseModel> delete(@PathVariable Long id) {
        Optional<Budget> budget = budgetRepository.findById(id);
        if (budget.isEmpty()) {
            return new ResponseEntity<>(new ResponseModelEspecNoObject("Nenhum orçamento encontrado!"), HttpStatus.NOT_FOUND);
        }
        budgetRepository.deleteById(id);
        return new ResponseEntity<>(new ResponseModelEspecNoObject("Orçamento excluido!"), HttpStatus.OK);
    }

    public ResponseEntity<ResponseModel> update(@Valid BudgetDTO dto, @PathVariable Long id) {
        try {
            Optional<Budget> budgetId = budgetRepository.findById(id);
            List<Product> products = productRepository.findByIdIn(dto.productIds());
            if (budgetId.isEmpty()) {
                return new ResponseEntity<>(new ResponseModelEspecNoObject("Orçamento não encontrado!"), HttpStatus.NOT_FOUND);
            }
            Budget budgetExists = budgetRepository.findById(id).orElse(null);
            if (budgetExists == null) {
                return new ResponseEntity<>(new ResponseModelEspecNoObject("Orçamento não encontrado!"), HttpStatus.NOT_FOUND);
            }
            budgetExists.update(dto, products);
            budgetRepository.save(budgetExists);
            return new ResponseEntity<>(new ResponseModelEspecNoObject("Orçamento foi atualizado!"), HttpStatus.OK);
        } catch (Error error) {
            error.printStackTrace();
        }
        return ResponseEntity.internalServerError().build();
    }
}