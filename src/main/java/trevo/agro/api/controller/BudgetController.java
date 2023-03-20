package trevo.agro.api.controller;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import trevo.agro.api.budget.BudgetDTO;
import trevo.agro.api.budget.BudgetService;

@RestController
@RequestMapping("budget")
public class BudgetController {
    @Autowired
    private BudgetService service;

    @PostMapping(value = "/register", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> register(@RequestBody @Valid BudgetDTO dto) {
        return service.register(dto);
    }

    @GetMapping(value = "/list")
    public ResponseEntity<?> list() {
        return service.list();
    }

    @DeleteMapping(value = "/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        return service.delete(id);
    }

    @GetMapping(value = "/find/{id}")
    public ResponseEntity<?> detailsClient(@PathVariable Long id) {
        return service.details(id);
    }

    @PutMapping(value = "/update/{id}")
    public ResponseEntity<?> update(@PathVariable Long id, @RequestBody @Valid BudgetDTO dto) {
        return service.update(dto, id);
    }


}
