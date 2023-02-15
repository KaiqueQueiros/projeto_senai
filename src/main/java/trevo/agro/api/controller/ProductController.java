package trevo.agro.api.controller;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;
import trevo.agro.api.category.CategoryRepository;
import trevo.agro.api.product.DetailsProductDTO;
import trevo.agro.api.product.Product;
import trevo.agro.api.product.ProductDTO;
import trevo.agro.api.product.ProductRepository;

@RestController
@RequestMapping("/product")
public class ProductController {
    @Autowired
    private ProductRepository repository;
    @Autowired
    private CategoryRepository categoryRepository;

    @PostMapping
    public ResponseEntity register(@RequestBody @Valid ProductDTO dados, UriComponentsBuilder uriBuilder) {
        /*Pegar lista de id do DTO
         * Pegar o reposity de categoria
         * Buscar um lista de categoria usando a repository */
        var categoryIds = dados.categoryIds();
        try {
            categoryRepository.findByIdIn(categoryIds);

        } catch (Exception error) {
            error.printStackTrace();
        }
        var categories = categoryRepository.findByIdIn(categoryIds);
        var product = new Product(dados, categories);
        repository.save(product);
        var uri = uriBuilder.path("/product/{id}").buildAndExpand(product.getId()).toUri();
        return ResponseEntity.created(uri).body(new DetailsProductDTO(product));

    }

    @GetMapping
    public Page<Product> listProduct(@PageableDefault(sort = {"name"}) Pageable pagination) {
        return repository.findAll(pagination);
    }
}
