package trevo.agro.api.culture;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import trevo.agro.api.exceptions.models.NotFoundException;
import trevo.agro.api.repository.CultureRepository;
import trevo.agro.api.utils.ResponseModel;
import trevo.agro.api.utils.ResponseModelEspec;
import trevo.agro.api.utils.ResponseModelEspecNoObject;
import java.util.List;

@Service
public class CultureService {
    @Autowired
    private CultureRepository cultureRepository;

    public ResponseEntity<ResponseModel> register(@RequestBody @Valid CultureDTO dto) {
        if (cultureRepository.existsByName(dto.name())) {
            throw new NotFoundException("Nome ja existe");
        }
        Culture culture = new Culture(dto);
        cultureRepository.save(culture);
        return new ResponseEntity<>(new ResponseModelEspec("Cultura cadastrada!", dto), HttpStatus.OK);
    }

    public ResponseEntity<ResponseModel> list() {
        List<Culture> cultures = cultureRepository.findAll();
        if (cultures.isEmpty()) {
            throw new NotFoundException("Não existem culturas cadastradas");
        }
        return new ResponseEntity<>(new ResponseModelEspec("Lista de culturas!", cultures), HttpStatus.OK);
    }

    public ResponseEntity<ResponseModel> delete(@PathVariable Long id) {

        if (cultureRepository.findById(id).isPresent()) {
            cultureRepository.deleteById(id);
            return new ResponseEntity<>(new ResponseModelEspecNoObject("Cultura Excluida!"), HttpStatus.OK);
        }
        throw new NotFoundException("Cultura inexistente");
    }

        public ResponseEntity<ResponseModel> update (@Valid CultureDTO dto, @PathVariable Long id){
            Culture cultureExists = cultureRepository.findById(id).orElse(null);
            if (cultureExists == null) {
                throw new NotFoundException("Cultura não encontrada");
            }
            cultureExists.update(dto);
            cultureRepository.save(cultureExists);
            return new ResponseEntity<>(new ResponseModelEspecNoObject("Cultura foi atualizada!"), HttpStatus.OK);
        }
    }


