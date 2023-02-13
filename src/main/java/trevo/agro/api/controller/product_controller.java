package trevo.agro.api.controller;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriBuilder;
import org.springframework.web.util.UriComponentsBuilder;
import org.w3c.dom.Text;
import org.springframework.beans.factory.annotation.Autowired;
import trevo.agro.api.product.Product;
import trevo.agro.api.product.detailsProduct;
import trevo.agro.api.product.product_date;
import trevo.agro.api.product.product_repository;

@RestController
@RequestMapping("/product")
public class product_controller {
    @Autowired
    private product_repository repository;
    @PostMapping
    public ResponseEntity register(@RequestBody @Valid product_date dados, UriComponentsBuilder uriBuilder){
        var product = new Product(dados);
        repository.save(product);
        var uri = uriBuilder.path("/product/{id}").buildAndExpand(product.getId()).toUri();

        return ResponseEntity.created(uri).body(new detailsProduct(product));

    }
    @GetMapping
    public Page<Product> listProduct(@PageableDefault(sort = {"name"})Pageable pagination) {
        return repository.findAll(pagination);
    }
}
