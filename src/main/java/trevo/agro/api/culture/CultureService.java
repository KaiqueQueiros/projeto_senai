package trevo.agro.api.culture;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import trevo.agro.api.product.ProductRepository;
import trevo.agro.api.utils.ResponseModel;
import trevo.agro.api.utils.ResponseModelEspec;
import trevo.agro.api.utils.ResponseModelEspecNoObject;

import java.util.List;
import java.util.Optional;

@Service
public class CultureService {
    @Autowired
    private CultureRepository cultureRepository;
    @Autowired
    private ProductRepository repository;

    public ResponseEntity<ResponseModel> register(@RequestBody @Valid CultureDTO dto) {
        if (cultureExist(dto.getName().toUpperCase())) {
            return new ResponseEntity<>(new ResponseModelEspecNoObject("Cultura já existe!"), HttpStatus.BAD_REQUEST);
        }
        if (dto.setName() == "") {
            return new ResponseEntity<>(new ResponseModelEspecNoObject("For favor insira o nome da cultura"), HttpStatus.BAD_REQUEST);
        }

        Culture culture = new Culture(dto);
        cultureRepository.save(culture);
        return new ResponseEntity<>(new ResponseModelEspec("Cultura cadastrada!", dto), HttpStatus.OK);
    }

    public Boolean cultureExist(String name) {
        return cultureRepository.existsByName(name);
    }

    public ResponseEntity<ResponseModel> list() {
        List<Culture> cultures = cultureRepository.findAll();
        if (cultures.isEmpty()) {
            return new ResponseEntity<>(new ResponseModelEspecNoObject("Não existem culturas cadastradas!"), HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(new ResponseModelEspec("Lista de culturas!", cultures), HttpStatus.OK);
    }

    public ResponseEntity<ResponseModel> delete(@PathVariable Long id) {
        try {
            Optional<Culture> culture = cultureRepository.findById(id);
            if (culture.isEmpty()) {
                return new ResponseEntity<>(new ResponseModelEspecNoObject("Cultura não encontrada!"), HttpStatus.NOT_FOUND);
            }
            cultureRepository.deleteById(id);
            return new ResponseEntity<>(new ResponseModelEspecNoObject("Cultura excluida!"), HttpStatus.OK);
        } catch (Error error) {
            error.printStackTrace();
        }
        return ResponseEntity.internalServerError().build();
    }

    public ResponseEntity<ResponseModel> update(@Valid CultureDTO dto, @PathVariable Long id) {
        try {
            var cultures = cultureRepository.findById(id);
            if (cultures.isEmpty() || dto.getName() == null) {
                return new ResponseEntity<>(new ResponseModelEspecNoObject("Parametros invalidos!"), HttpStatus.NOT_FOUND);
            }
            Culture cultureExists = cultureRepository.findById(id).orElse(null);
            if (cultureExists == null) {
                return new ResponseEntity<>(new ResponseModelEspecNoObject("Cultura não encontrada!"), HttpStatus.NOT_FOUND);
            }
            cultureExists.update(dto);
            cultureRepository.save(cultureExists);
            return new ResponseEntity<>(new ResponseModelEspecNoObject("Cultura foi atualizada!"), HttpStatus.OK);
        } catch (Exception error) {
            error.printStackTrace();
        }
        return ResponseEntity.internalServerError().build();
    }
}



