package trevo.agro.api.controller;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import trevo.agro.api.dto.ProductSaveDTO;
import trevo.agro.api.service.ProductService;

@RestController
@RequestMapping("product")
public class ProductController {
    @Autowired
    ProductService service;

    @PostMapping(value = "/register", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> registerProduct(@RequestBody @Valid ProductSaveDTO dto) {
        return service.register(dto);
    }

    @GetMapping(value = "/list")
    public ResponseEntity<?> list() {
        return service.list();
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<?> detailsProduct(@PathVariable Long id) {
        return service.details(id);
    }

    @DeleteMapping(value = "/delete/{id}")
    ResponseEntity<?> deleteProduct(@PathVariable Long id) {
        return service.delete(id);
    }

    @PutMapping(value = "/status/{id}")
    ResponseEntity<?> statusProduct(@PathVariable Long id) {
        return service.alternateStatus(id);
    }

    @PutMapping(value = "/update/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<?> update(@PathVariable Long id, @RequestBody ProductSaveDTO dto) {
        return service.update(dto, id);
    }
}
