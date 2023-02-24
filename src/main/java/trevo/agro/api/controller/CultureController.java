package trevo.agro.api.controller;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import trevo.agro.api.culture.CultureDTO;
import trevo.agro.api.culture.CultureRepository;
import trevo.agro.api.culture.CultureService;
import trevo.agro.api.utils.ResponseModel;

@RequestMapping("/culture")
@RestController
public class CultureController {
    @Autowired
    private CultureRepository repository;
    @Autowired
    private CultureService service;

    @RequestMapping(value = "/register", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.POST)
    public ResponseEntity<ResponseModel> register(@RequestBody @Valid CultureDTO dto) {
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

    @RequestMapping(value = "/update/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.PUT)
    ResponseEntity<ResponseModel> update(@PathVariable Long id, @RequestBody CultureDTO dto) {
        return service.update(dto, id);
    }


}
