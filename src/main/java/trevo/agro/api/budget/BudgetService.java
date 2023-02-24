package trevo.agro.api.budget;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import trevo.agro.api.product.Product;
import trevo.agro.api.product.ProductRepository;
import trevo.agro.api.utils.ResponseModel;
import trevo.agro.api.utils.ResponseModelEspec;
import trevo.agro.api.utils.ResponseModelEspecNoObject;

import java.util.List;

@Service
public class BudgetService {
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private BudgetRepository budgetRepository;


    public ResponseEntity<ResponseModel> register(@RequestBody @Valid BudgetDTO dto){
        try {
            List<Product> products  = productRepository.findByIdIn(dto.productIds());
            if(products.isEmpty()) {
                return new ResponseEntity<>(new ResponseModelEspecNoObject("Nenhum produto foi selecionado!"), HttpStatus.BAD_REQUEST);
            }
            Budget budget = new Budget(dto, products);
            budgetRepository.save(budget);
            return new ResponseEntity<>(new ResponseModelEspec("Registramos seu interesse em nossos produtos, entraremos em contato em breve!",dto),HttpStatus.OK);

        } catch (Exception error) {
            error.printStackTrace();
        }
        return ResponseEntity.internalServerError().build();
    }
    public ResponseEntity<ResponseModel> list(){
        List<Budget> budgets = budgetRepository.findAll();
        if (budgets.isEmpty()){
            new ResponseEntity<>(new ResponseModelEspecNoObject("Listas de orçamentos vazia!"),HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(new ResponseModelEspec("Lista de orçamentos!",budgets),HttpStatus.OK);
    }

}
