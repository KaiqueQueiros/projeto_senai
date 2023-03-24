package trevo.agro.api.controller;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import trevo.agro.api.product.ProductSaveDTO;
import trevo.agro.api.product.ProductService;

@RestController
@RequestMapping("product")
public class ProductController {
    @Autowired
    ProductService service;

    @RequestMapping(method = RequestMethod.POST, value = "/register", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> registerProduct(@RequestBody @Valid ProductSaveDTO dto) {
        return service.register(dto);
    }

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public ResponseEntity<?> list() {
        return service.list();
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<?> detailsProduct(@PathVariable Long id) {
        return service.details(id);
    }

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE)
    ResponseEntity<?> deleteProduct(@PathVariable Long id) {
        return service.delete(id);
    }

    @RequestMapping(value = "/status/{id}", method = RequestMethod.PUT)
    ResponseEntity<?> statusProduct(@PathVariable Long id) {
        return service.alternateStatus(id);
    }

    @RequestMapping(value = "/update/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.PUT)
    ResponseEntity<?> update(@PathVariable Long id, @RequestBody ProductSaveDTO dto) {
        return service.update(dto, id);
    }
}
