package trevo.agro.api.controller;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import trevo.agro.api.category.CategoryDTO;
import trevo.agro.api.repository.CategoryRepository;
import trevo.agro.api.category.CategoryService;
import trevo.agro.api.utils.ResponseModel;

@RestController
@RequestMapping(path = "/category")
public class CategoryController {
    @Autowired
    private CategoryRepository repository;
    @Autowired
    private CategoryService service;

    @RequestMapping(value = "/register", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.POST)
    public ResponseEntity<ResponseModel> register(@RequestBody @Valid CategoryDTO dto) {
        return service.register(dto);
    }

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public ResponseEntity<ResponseModel> list() {
        return service.list();
    }

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<ResponseModel> delete(@PathVariable Long id) {
        return service.delete(id);
    }

    @RequestMapping(value = "/update/{id}", method = RequestMethod.PUT)
    public ResponseEntity<ResponseModel> update(@PathVariable Long id, @RequestBody CategoryDTO dto) {
        return service.update(dto, id);
    }


}
