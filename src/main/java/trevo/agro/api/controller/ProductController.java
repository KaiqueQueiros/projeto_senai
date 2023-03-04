package trevo.agro.api.controller;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import trevo.agro.api.repository.CategoryRepository;
import trevo.agro.api.repository.CultureRepository;
import trevo.agro.api.product.ProductDTO;
import trevo.agro.api.repository.ProductRepository;
import trevo.agro.api.product.ProductService;
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

    @RequestMapping(method = RequestMethod.POST, value = "/register", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ResponseModel> registerProduct(@RequestBody @Valid ProductDTO dto) {
        return service.register(dto);
    }

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public ResponseEntity<ResponseModel> list() {
        return service.list();
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<ResponseModel> detailsProduct(@PathVariable Long id) {
        return service.details(id);
    }

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE)
    ResponseEntity<ResponseModel> deleteProduct(@PathVariable Long id) {
        return service.delete(id);
    }

    @RequestMapping(value = "/status/{id}", method = RequestMethod.PUT)
    ResponseEntity<ResponseModel> statusProduct(@PathVariable Long id) {
        return service.alternarStatus(id);
    }

    @RequestMapping(value = "/update/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.PUT)
    ResponseEntity<ResponseModel> update(@PathVariable Long id, @RequestBody ProductDTO dto) {
        return service.update(dto, id);
    }

}
