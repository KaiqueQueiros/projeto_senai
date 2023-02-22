package trevo.agro.api.controller;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import trevo.agro.api.category.CategoryRepository;
import trevo.agro.api.culture.CultureRepository;
import trevo.agro.api.product.ProductDTO;
import trevo.agro.api.product.ProductRepository;
import trevo.agro.api.product.ProductService;
import trevo.agro.api.utils.ResponseModel;
import trevo.agro.api.utils.ResponseModelEspec;
import trevo.agro.api.utils.ResponseModelEspecNoObject;

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
    public ResponseEntity<ResponseModel> registerProduct(@RequestBody @Valid ProductDTO dto) {
        return service.register(dto);
    }

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public ResponseEntity<ResponseModel> listProduct() {
        return service.list();
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<ResponseModel> detailsProduct(@PathVariable Long id) {
        return service.details(id);
    }

    @RequestMapping(value = "delete/{id}", method = RequestMethod.DELETE)
    ResponseEntity<ResponseModel> deleteProduct(@PathVariable Long id) {
        return service.delete(id);
    }
    @PutMapping(value = "update/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<ResponseModel> update(@PathVariable Long id, @RequestBody ProductDTO dto){
        return service.update(dto,id);
    }

}
