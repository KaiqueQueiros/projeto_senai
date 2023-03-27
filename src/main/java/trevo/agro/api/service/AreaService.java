package trevo.agro.api.service;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import trevo.agro.api.dto.AreaDTO;
import trevo.agro.api.exceptions.models.BadRequestException;
import trevo.agro.api.exceptions.models.NotFoundException;
import trevo.agro.api.models.Area;
import trevo.agro.api.models.Product;
import trevo.agro.api.repository.AreaRepository;
import trevo.agro.api.repository.ProductRepository;
import trevo.agro.api.utils.ResponseModelEspec;
import java.util.List;

@Service
public class AreaService {
    @Autowired
    private AreaRepository areaRepository;
    @Autowired
    private ProductRepository productRepository;

    public ResponseEntity<?> register(@RequestBody @Valid AreaDTO dto) {
        Area area = new Area(dto);
        if (areaRepository.existsBySize(dto.size())) {
            throw new BadRequestException("Area " + dto.size() + " ja existe");
        }
        areaRepository.save(area);
        return new ResponseEntity<>(new ResponseModelEspec("Area cadastrada", dto), HttpStatus.OK);
    }

    public ResponseEntity<?> list() {
        if (areaRepository.findAll().isEmpty()) {
            throw new NotFoundException("Nenhuma area encontrada");
        }
        List<Area> areas = areaRepository.findAll();
        return new ResponseEntity<>(new ResponseModelEspec("Lista de areas", areas), HttpStatus.OK);
    }

    public ResponseEntity<?> update(AreaDTO dto, Long id) {
        Area area = areaRepository.findById(id).orElse(null);
        if (!areaRepository.existsById(id) || area == null) {
            throw new NotFoundException("Area com id " + id + " não foi encontrado");
        }
        area.update(dto);
        areaRepository.save(area);
        return ResponseEntity.ok().body("Area " + dto.size() + " foi atualizada");
    }

    public ResponseEntity<?> delete(@PathVariable Long id) {
        Area area = areaRepository.findById(id).orElse(null);
        List<Product> productList = productRepository.findByAreas(area);
        if (!areaRepository.existsById(id)) {
            throw new NotFoundException("Area com id " + id + " não foi encontrado");
        }
        if (productList.isEmpty()) {
            areaRepository.deleteById(id);
            return ResponseEntity.ok().body("Area excluida");
        }
        throw new BadRequestException("Area não pode ser excluida pois esta relacionada com produto");
    }
}
