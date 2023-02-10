package trevo.agro.api.controller;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import trevo.agro.api.client.Client;
import trevo.agro.api.client.client_repository;
import trevo.agro.api.client.client_date;
import trevo.agro.api.client.updateClient;

@RestController
@RequestMapping ("/client")
public class client_controller {
    @Autowired
    private client_repository repository;
    @PostMapping
    @Transactional
    public void register(@RequestBody @Valid client_date dados) {
        repository.save(new Client(dados));
    }
    @GetMapping
    public Page<Client> listClient(@PageableDefault (sort = {"name"}) Pageable pagination) {
        return repository.findAll(pagination);
    }
    @PutMapping
    @Transactional
    public void update(@RequestBody @Valid updateClient dados) {
        var client = repository.getReferenceById(dados.id());
        client.updateDate(dados);
    }
    @DeleteMapping("/{id}")
    @Transactional
    public void delete(@PathVariable Long id){
        repository.deleteById(id);
    }


}
