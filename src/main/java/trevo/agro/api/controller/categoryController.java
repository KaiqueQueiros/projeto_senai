package trevo.agro.api.controller;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import trevo.agro.api.category.Category;
import trevo.agro.api.category.categoryDate;
import trevo.agro.api.category.categoryRepository;
import org.springframework.data.domain.Page;
@RestController
@RequestMapping ("/category")

public class categoryController {
    @Autowired
    private categoryRepository repository;
    @PostMapping
    @Transactional
    public void register(@RequestBody@Valid categoryDate dados) {
        repository.save(new Category(dados));
    }
    @GetMapping Page<Category> listCategory(@PageableDefault(sort ={"name"})Pageable pagination){
        return repository.findAll(pagination);
    }


}
