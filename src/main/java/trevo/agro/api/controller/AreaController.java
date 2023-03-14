package trevo.agro.api.controller;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import trevo.agro.api.area.AreaDTO;
import trevo.agro.api.area.AreaService;
import trevo.agro.api.utils.ResponseModel;

@RestController
@RequestMapping("/area")
public class AreaController {
    @Autowired
    private AreaService service;

    @PostMapping(value = "/register", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> register(@RequestBody @Valid AreaDTO dto) {
        return service.register(dto);
    }
    @GetMapping(value = "/list")
    public ResponseEntity<?> list(){
        return service.list();
    }
    @PutMapping(value = "/update/{id}",consumes = MediaType.APPLICATION_JSON_VALUE,produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity <?> update(@Valid @RequestBody AreaDTO dto, @PathVariable Long id){
        return service.update(dto,id);
    }
    @DeleteMapping(value = "/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id){
        return service.delete(id);
    }
}
