package trevo.agro.api.controller;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import trevo.agro.api.client.client;
import trevo.agro.api.client.client_repository;
import trevo.agro.api.client.dados_cadastros_client;

@RestController
@RequestMapping ("/client")
public class client_controller {
    @Autowired
    private client_repository repository;
    @PostMapping
    @Transactional
    public void register(@RequestBody @Valid dados_cadastros_client dados) {
        repository.save(new client(dados));
    }
    @GetMapping
    public Page<client> list_client(@PageableDefault (size=10,sort = {"name"}) Pageable pagination) {
        return repository.findAll(pagination);
    }

}
