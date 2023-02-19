package trevo.agro.api.controller;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;
import trevo.agro.api.client.*;

@RestController
@RequestMapping("/client")
public class ClientController {
    @Autowired
    private ClientRepository repository;

    @PostMapping
    @Transactional
    public ResponseEntity<?> register(@RequestBody @Valid ClientDate dto, UriComponentsBuilder uriBuilder) {
        var client = new Client(dto);

        repository.save(client);

        var uri = uriBuilder.path("/client/{id}").buildAndExpand(client.getId()).toUri();

        return ResponseEntity.created(uri).body(new DetailsClient(client));
    }

    @GetMapping
    public Page<Client> listClient(@PageableDefault(sort = {"name"}) Pageable pagination) {
        return repository.findAll(pagination);
    }

    @PutMapping
    @Transactional
    public ResponseEntity<?> update(@RequestBody @Valid UpdateClient dto) {
        var client = repository.getReferenceById(dto.id());
        client.updateDate(dto);

        return ResponseEntity.ok(new DetailsClient(client));
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<?> delete(@PathVariable Long id) {
        repository.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> detailsClient(@PathVariable Long id) {
        var client = repository.getReferenceById(id);
        return ResponseEntity.ok(new DetailsClient(client));
    }


}
