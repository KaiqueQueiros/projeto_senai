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
import trevo.agro.api.budget.*;
import trevo.agro.api.product.ProductRepository;
import trevo.agro.api.utils.ResponseModel;

@RestController
@RequestMapping("budget")
public class BudgetController {
    @Autowired
    private BudgetRepository repository;
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private BudgetService service;

    @RequestMapping(method = RequestMethod.POST,value = "/register",consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ResponseModel> register(@RequestBody @Valid BudgetDTO dto) {
        return service.register(dto);
    }

    @RequestMapping(value =  "/list",method = RequestMethod.GET)
    public ResponseEntity<ResponseModel> list(){
        return service.list();
    }

    @RequestMapping(method = RequestMethod.DELETE,value = "/delete/{id}")
    public ResponseEntity<ResponseModel> delete(@PathVariable Long id) {
            return service.delete(id);
    }
    @RequestMapping(method = RequestMethod.GET,value = "/find/{id}")
    public ResponseEntity<ResponseModel> detailsClient(@PathVariable Long id) {
        return service.details(id);
    }@RequestMapping(method = RequestMethod.PUT,value = "/update/{id}")
    public ResponseEntity<ResponseModel> update(@PathVariable Long id,@RequestBody BudgetDTO dto){
        return service.update(dto,id);
    }



}
