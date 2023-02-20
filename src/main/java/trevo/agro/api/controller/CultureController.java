package trevo.agro.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import trevo.agro.api.culture.Culture;
import trevo.agro.api.culture.CultureRepository;

import java.util.List;

@RequestMapping("/culture")
@RestController
public class CultureController {
    @Autowired
    private CultureRepository repository;

    @PostMapping
    @Transactional
    public Culture registerCulture(@RequestBody Culture culture) {
        return repository.save(culture);

    }
    @GetMapping
    public List<Culture> getCulture() {
        return repository.findAll();
    }
    @DeleteMapping("delete/{id}")//Só sera possivel fazer o delete da cultura se a mesma não estiver relacionada com nenhum produto
    ResponseEntity<?>deleteBudget(@PathVariable Long id){
        repository.deleteById(id);
        return ResponseEntity.noContent().build();
    }


}
