package trevo.agro.api.culture;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import trevo.agro.api.exceptions.models.BadRequestException;
import trevo.agro.api.exceptions.models.NotFoundException;
import trevo.agro.api.product.Product;
import trevo.agro.api.repository.CultureRepository;
import trevo.agro.api.repository.ProductRepository;
import trevo.agro.api.utils.ResponseModelEspec;
import trevo.agro.api.utils.ResponseModelEspecNoObject;

import java.util.List;

@Service
public class CultureService {
    @Autowired
    CultureRepository cultureRepository;
    @Autowired
    ProductRepository productRepository;

    public ResponseEntity<?> register(@RequestBody @Valid CultureDTO dto) {
        if (cultureRepository.existsByName(dto.name())) {
            throw new BadRequestException("Nome " + dto.name() + " ja existe");
        }
        Culture culture = new Culture(dto);
        cultureRepository.save(culture);
        return new ResponseEntity<>(new ResponseModelEspec("Cultura cadastrada!", dto), HttpStatus.OK);
    }

    public ResponseEntity<?> list() {
        List<Culture> cultures = cultureRepository.findAll();
        if (cultures.isEmpty()) {
            throw new NotFoundException("Nenhuma cultura cadastrada");
        }
        return new ResponseEntity<>(new ResponseModelEspec("Lista de culturas!", cultures), HttpStatus.OK);
    }

    public ResponseEntity<?> delete(@PathVariable Long id) {
        Culture culture = cultureRepository.findById(id).orElse(null);
        List<Product> productList = productRepository.findByCultures(culture);
        if (!cultureRepository.existsById(id)) {
            throw new BadRequestException("Cultura com id " + id + " não encontrada");
        }
        if (productList.isEmpty()) {
            cultureRepository.deleteById(id);
            return new ResponseEntity<>(new ResponseModelEspecNoObject("Cultura excluida"), HttpStatus.OK);
        }
        throw new BadRequestException("Não é possivel excluir a cultura pois esta relacionada com produtos");
    }

    public ResponseEntity<?> update(@Valid @RequestBody CultureDTO dto, @PathVariable Long id) {
        Culture cultureExists = cultureRepository.findById(id).orElse(null);
        if (cultureRepository.existsByName(dto.name())){
            throw new BadRequestException("Nome já existe");
        }
        if (!cultureRepository.existsById(id)|| cultureExists == null) {
            throw new BadRequestException("Cultura não encontrada");
        }
        cultureExists.update(dto);
        cultureRepository.save(cultureExists);
        return new ResponseEntity<>(new ResponseModelEspec("Cultura foi atualizada!",cultureExists), HttpStatus.OK);
    }
}


