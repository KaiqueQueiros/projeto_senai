package trevo.agro.api.controller;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.web.PageableDefault;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import trevo.agro.api.culture.Culture;
import trevo.agro.api.culture.cultureDate;
import trevo.agro.api.culture.cultureRepository;

@RequestMapping("/culture")
@RestController
public class CultureController {
    @Autowired
    private cultureRepository repository;
    @PostMapping
    @Transactional
    public void register (@RequestBody @Valid cultureDate dados){
        repository.save(new Culture(dados));
    }
    @GetMapping
    //Defini que a resquisição GET de listagem esta por ordem alfabetica.
    public Page<Culture> listCulture(@PageableDefault(sort = {"name"})Pageable pagination) {
        return repository.findAll(pagination);
    }


}
