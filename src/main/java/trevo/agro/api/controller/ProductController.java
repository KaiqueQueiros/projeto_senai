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
import trevo.agro.api.category.CategoryRepository;
import trevo.agro.api.culture.CultureRepository;
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
    @Autowired
    private CultureRepository cultureRepository;

    @PostMapping(name = "/product")
    @Transactional
    public ResponseEntity<?> register(@RequestBody @Valid ProductDTO dto, UriComponentsBuilder uriBuilder) {
        /*No momento de criação do produto
        esta incluso incluir pelo menos uma categoria, e
        tambem pelo menos um tipo de cultura */
        var categoryIds = dto.categoryIds();
        var cultureIds = dto.cultureIds();
        try {
            categoryRepository.findByIdIn(categoryIds);
            cultureRepository.findByIdIn(cultureIds);
            var cultures = cultureRepository.findByIdIn(cultureIds);
            var categories = categoryRepository.findByIdIn(categoryIds);
            var product = new Product(dto, categories, cultures);
            repository.save(product);
            var uri = uriBuilder.path("/product/{id}").buildAndExpand(product.getId()).toUri();
            return ResponseEntity.created(uri).body(new DetailsProductDTO(product));

        } catch (Exception error) {
            error.printStackTrace();
        }
        return ResponseEntity.internalServerError().build();
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

    @DeleteMapping("delete/{id}")//Só é possivel deletar o produto se o mesmo não estiver relacionado com nenhum pedido.
    ResponseEntity<?> deleteProduct(@PathVariable Long id) {
        repository.deleteById(id);
        return ResponseEntity.noContent().build();
    }

}
