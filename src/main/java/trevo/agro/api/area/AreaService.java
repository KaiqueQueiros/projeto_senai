package trevo.agro.api.area;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import trevo.agro.api.product.Product;
import trevo.agro.api.repository.AreaRepository;
import trevo.agro.api.repository.ProductRepository;
import trevo.agro.api.utils.ResponseModel;
import trevo.agro.api.utils.ResponseModelEspec;
import trevo.agro.api.utils.ResponseModelEspecNoObject;

import java.util.List;

@Service
public class AreaService {
   @Autowired
   private AreaRepository areaRepository;
   @Autowired
   private ProductRepository productRepository;

    public ResponseEntity<ResponseModel> register(@RequestBody @Valid AreaDTO dto) {
        Area area = new Area(dto);
        areaRepository.save(area);
        return new ResponseEntity<>(new ResponseModelEspec("Area cadastrada",dto), HttpStatus.OK);
    }

    public ResponseEntity<ResponseModel> list() {
        if (areaRepository.findAll().isEmpty()){
            return new ResponseEntity<>(new ResponseModelEspecNoObject("Nenhuma area encontrada"),HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity <>(new ResponseModelEspec("Lista de areas",areaRepository.findAll()),HttpStatus.OK);
    }

    public ResponseEntity<ResponseModel> update(AreaDTO dto, Long id) {
        Area area = areaRepository.findById(id).orElse(null);
        if (!areaRepository.existsById(id)){
            return new ResponseEntity<>(new ResponseModelEspecNoObject("Area com id " + id + " não foi encontrado"),HttpStatus.NOT_FOUND);
        }
        assert area != null;
        area.update(dto);
        areaRepository.save(area);
        return new ResponseEntity<>(new ResponseModelEspecNoObject("Area " + dto.size() + " foi atualizada"),HttpStatus.OK);
    }
    public ResponseEntity<ResponseModel> delete(@PathVariable Long id){
        Area area = areaRepository.findById(id).orElse(null);
        if (!areaRepository.existsById(id)) {
            return new ResponseEntity<>(new ResponseModelEspecNoObject("Area com id " + id + " não foi encontrado"), HttpStatus.NOT_FOUND);
        }
        List<Product> productList = productRepository.findByAreas(area);
        if (productList.isEmpty()){
            areaRepository.deleteById(id);
            return new ResponseEntity<>(new ResponseModelEspecNoObject("Area excluida"),HttpStatus.OK);
        }
        return new ResponseEntity<>(new ResponseModelEspecNoObject("Esta relacionada com produtos"),HttpStatus.BAD_REQUEST);
    }
}
