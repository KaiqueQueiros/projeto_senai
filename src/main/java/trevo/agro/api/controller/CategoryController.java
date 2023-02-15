package trevo.agro.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import trevo.agro.api.category.Category;
import trevo.agro.api.category.CategoryRepository;

import java.util.List;

@RestController
@RequestMapping(path = "/category")
public class CategoryController {
    @Autowired
    private CategoryRepository repository;

    @PostMapping
    @Transactional
    public Category register(@RequestBody Category category) {
        return repository.save(category);
    }

    @GetMapping
    public List<Category> getCategory() {
        return repository.findAll();
    }

    @GetMapping("/find/{id}")
    public Category getCategory(@PathVariable("id") Long id) {
        return repository.findById(id).orElse(null);
    }


}
