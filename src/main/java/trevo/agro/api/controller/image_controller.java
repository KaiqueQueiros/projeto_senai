package trevo.agro.api.controller;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import trevo.agro.api.image.Image;
import trevo.agro.api.image.image_repository;
import trevo.agro.api.image.image_data;

import java.util.List;


@RestController
@RequestMapping("/image")
public class image_controller {
    @Autowired
    private image_repository repository;
    @PostMapping
    @Transactional
    public void register(@Valid @RequestBody image_data dados){
        repository.save(new Image(dados));
    }
    @GetMapping
    public List<Image> list() {
        return repository.findAll();
    }
}