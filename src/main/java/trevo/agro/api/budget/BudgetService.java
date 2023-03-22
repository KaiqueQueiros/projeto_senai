package trevo.agro.api.budget;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import trevo.agro.api.exceptions.models.BadRequestException;
import trevo.agro.api.exceptions.models.NotFoundException;
import trevo.agro.api.product.Product;
import trevo.agro.api.repository.BudgetRepository;
import trevo.agro.api.repository.ProductRepository;
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

    public ResponseEntity<?> register(@RequestBody @Valid BudgetDTO dto) {
        List<Product> products = productRepository.findByIdIn(dto.productIds());
        Budget budget = new Budget(dto, products);
        budgetRepository.save(budget);
        return new ResponseEntity<>(new ResponseModelEspec(dto.name() + " Obrigado por solicitar um orçamento em nosso site!" +
                " Ficamos felizes em poder ajudá-lo e agradecemos pela confiança em nossos serviços." +
                " Para fornecer um orçamento preciso, precisamos avaliar suas necessidades com mais detalhes." +
                " Entraremos em contato em breve para obter mais informações e esclarecer quaisquer dúvidas." +
                " Nossa equipe está sempre disponível para ajudá-lo no que for preciso." +
                " Agradecemos novamente pela sua preferência e aguardamos ansiosamente seu retorno para seguir com a solicitação do orçamento. Atenciosamente, Trevo SA", products), HttpStatus.OK);
    }

    public ResponseEntity<?> list() {
        List<Budget> budgetList = budgetRepository.findAll();
        if (budgetList.isEmpty()) {
            throw new NotFoundException("Não existem orçamentos cadastrados");
        }
        return ResponseEntity.ok(new ResponseModelEspec("Detalhes de todos os orçamentos", budgetList));
    }

    public ResponseEntity<?> detailsId(@PathVariable Long id) {
        Budget budget = budgetRepository.findById(id).orElse(null);
        if (budget == null) {
            throw new NotFoundException("Nenhum orçamento com id " + id + " encontrado");
        }
        return new ResponseEntity<>(new ResponseModelEspec("Detalhes do orçamento!", budget), HttpStatus.OK);
    }

    public ResponseEntity<?> detailsName(@PathVariable String name) {
        List<Budget> budgetList = budgetRepository.findByName(name);
        if (budgetList.isEmpty()) {
            throw new NotFoundException("Insira o nome completo do cliente e tente novamente");
        }
        return new ResponseEntity<>(new ResponseModelEspec("Lista de pedidos de " + name + ": ", budgetList), HttpStatus.OK);
    }
    public ResponseEntity<?> detailsPhone(@PathVariable String email){
        List<Budget> budgetList = budgetRepository.findByEmail(email);
        if (budgetList.isEmpty()){
            throw new NotFoundException("Insira o email correto do cliente e tente novamente");
        }
        return new ResponseEntity<>(new ResponseModelEspec("Lista de pedidos desse cliente", budgetList), HttpStatus.OK);
    }

    public ResponseEntity<?> delete(@PathVariable Long id) {
        Budget budget = budgetRepository.findById(id).orElse(null);
        if (budget == null) {
            throw new NotFoundException("Orçamento com id " + id + " não encontrado");
        }
        budgetRepository.deleteById(id);
        return new ResponseEntity<>(new ResponseModelEspecNoObject("Orçamento excluido!"), HttpStatus.OK);
    }

    public ResponseEntity<ResponseModel> update(@Valid @RequestBody BudgetDTO dto, @PathVariable Long id) {
        List<Product> products = productRepository.findByIdIn(dto.productIds());
        Budget budget = budgetRepository.findById(id).orElse(null);
        if (!budgetRepository.existsById(id) || budget == null) {
            throw new BadRequestException("Orçamento com id " + id + " não encontrado!");
        }
        budget.update(dto, products);
        budgetRepository.save(budget);
        return new ResponseEntity<>(new ResponseModelEspecNoObject("Orçamento foi atualizado!"), HttpStatus.OK);
    }
}