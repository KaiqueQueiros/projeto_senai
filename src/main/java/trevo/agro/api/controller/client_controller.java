package trevo.agro.api.controller;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriBuilder;
import org.springframework.web.util.UriComponentsBuilder;
import trevo.agro.api.client.*;

@RestController
@RequestMapping ("/client")
public class client_controller {
    @Autowired
    private clientController repository;
    @PostMapping
    @Transactional
    public ResponseEntity register(@RequestBody @Valid client_date dados, UriComponentsBuilder uriBuilder) {
        var client = new Client(dados);

        repository.save(client);

        var uri = uriBuilder.path("/client/{id}").buildAndExpand(client.getId()).toUri();

        return ResponseEntity.created(uri).body(new detailsClient(client));

    }
    @GetMapping
    public Page<Client> listClient(@PageableDefault (sort = {"name"}) Pageable pagination) {
        return repository.findAll(pagination);
    }
    @PutMapping
    @Transactional
    public ResponseEntity update(@RequestBody @Valid updateClient dados) {
        var client = repository.getReferenceById(dados.id());
        client.updateDate(dados);

        return ResponseEntity.ok(new detailsClient(client));
    }
    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity delete(@PathVariable Long id){
        repository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
    @GetMapping("/{id}")
    public ResponseEntity detailsClient(@PathVariable Long id){
        var client = repository.getReferenceById(id);
        return ResponseEntity.ok(new detailsClient(client));
    }


}
