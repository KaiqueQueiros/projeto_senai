package trevo.agro.api.controller;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import trevo.agro.api.budget.BudgetDTO;
import trevo.agro.api.budget.BudgetService;
import trevo.agro.api.repository.BudgetRepository;
import trevo.agro.api.repository.ProductRepository;

@RestController
@RequestMapping("budget")
public class BudgetController {
    @Autowired
    private BudgetRepository repository;
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private BudgetService service;

    @RequestMapping(method = RequestMethod.POST, value = "/register", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> register(@RequestBody @Valid BudgetDTO dto) {
        return service.register(dto);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/list")
    public ResponseEntity<?> list() {
        return service.list();
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        return service.delete(id);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/find/{id}")
    public ResponseEntity<?> detailsClient(@PathVariable Long id) {
        return service.details(id);
    }

    @RequestMapping(method = RequestMethod.PUT, value = "/update/{id}")
    public ResponseEntity<?> update(@PathVariable Long id, @RequestBody @Valid BudgetDTO dto) {
        return service.update(dto, id);
    }


}
