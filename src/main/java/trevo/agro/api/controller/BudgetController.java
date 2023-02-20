package trevo.agro.api.controller;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;
import trevo.agro.api.budget.Budget;
import trevo.agro.api.budget.BudgetDTO;
import trevo.agro.api.budget.BudgetRepository;
import trevo.agro.api.budget.DetailsBudget;
import trevo.agro.api.product.ProductRepository;

@RestController
@RequestMapping("/budget")
public class BudgetController {
    @Autowired
    private BudgetRepository repository;
    @Autowired
    private ProductRepository productRepository;

    @PostMapping(name = "/budget")
    @Transactional
    public ResponseEntity<?> register(@RequestBody @Valid BudgetDTO dto, UriComponentsBuilder uriBuilder) {
        var productIds = dto.productIds();
        try {
            productRepository.findByIdIn(productIds);
            var products = productRepository.findByIdIn(productIds);
            var budget = new Budget(dto, products);
            repository.save(budget);
            var uri = uriBuilder.path("/budget/{id}").buildAndExpand(budget.getId()).toUri();
            return ResponseEntity.created(uri).body(new DetailsBudget(budget));

        } catch (Exception error) {
            error.printStackTrace();
        }
        return ResponseEntity.internalServerError().build();
    }

    @GetMapping("list")//Listar todos os pedidos
    public Page<Budget> listBudget(@PageableDefault(sort = {"name"}) Pageable pagination) {
        return repository.findAll(pagination);
    }


    @DeleteMapping("delete/{id}")//Deletar pedidos.
    @Transactional
    public ResponseEntity<?> delete(@PathVariable Long id) {
        repository.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("find/{id}")//Busca detalhada de pedidos por ID.
    public ResponseEntity<?> detailsClient(@PathVariable Long id) {
        var budget = repository.getReferenceById(id);
        return ResponseEntity.ok(new DetailsBudget(budget));
    }


}
