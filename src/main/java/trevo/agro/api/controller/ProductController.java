package trevo.agro.api.controller;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;
import trevo.agro.api.category.CategoryRepository;
import trevo.agro.api.culture.CultureRepository;
import trevo.agro.api.product.*;
import trevo.agro.api.utils.ResponseModel;

@RestController
@RequestMapping("product")
public class ProductController {
    @Autowired
    private ProductService service;
    @Autowired
    private ProductRepository repository;
    @Autowired
    private CategoryRepository categoryRepository;
    @Autowired
    private CultureRepository cultureRepository;

    @PostMapping(value = "/register", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ResponseModel> register(@RequestBody @Valid ProductDTO dto) {
        return service.register(dto);
    }

    @GetMapping("list")//Listar todos os produtos
    public Page<Product> listProduct(@PageableDefault(sort = {"name"}) Pageable pagination) {
        return repository.findAll(pagination);
    }

    @GetMapping("find/{id}")//Get para fazer uma busca mais detalhada de um produto.
    public ResponseEntity<DetailsProductDTO> detailProduct(@PathVariable Long id) {
        var product = repository.getReferenceById(id);
        return ResponseEntity.ok(new DetailsProductDTO(product));
    }

    @DeleteMapping("delete/{id}")
//Só é possivel deletar o produto se o mesmo não estiver relacionado com nenhum pedido.
    ResponseEntity<?> deleteProduct(@PathVariable Long id) {
        repository.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("find/{name}")//Get para fazer uma busca mais detalhada de um produto.
    public ResponseEntity<DetailsProductDTO> detailProduct(@PathVariable String name) {
        var product = repository.findByName(name);
        return ResponseEntity.ok(new DetailsProductDTO(product));
    }

}
