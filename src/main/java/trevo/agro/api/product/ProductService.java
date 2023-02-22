package trevo.agro.api.product;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import trevo.agro.api.category.Category;
import trevo.agro.api.category.CategoryRepository;
import trevo.agro.api.culture.Culture;
import trevo.agro.api.culture.CultureRepository;
import trevo.agro.api.utils.ResponseModel;

import java.util.List;

@Service
public class ProductService {
    @Autowired
    private ProductRepository repository;
    @Autowired
    private CategoryRepository categoryRepository;
    @Autowired
    private CultureRepository cultureRepository;

    public ResponseEntity<ResponseModel> register(@Valid ProductDTO dto) {
        try {
            List<Culture> cultures = cultureRepository.findByIdIn(dto.cultureIds());
            List<Category> categories = categoryRepository.findByIdIn(dto.categoryIds());
//            var product = new Product(dto, categories, cultures);
            if (cultures.isEmpty() || categories.isEmpty()) {
                return new ResponseEntity<>(new ResponseModel("Não encontrado", null), HttpStatus.NOT_FOUND);
//                return ResponseEntity.notFound().build();
            }
            Product product = new Product(dto, categories, cultures);
            repository.save(product);
            return new ResponseEntity<>(new ResponseModel("Produto foi salvo", dto), HttpStatus.OK);

//            var uri = uriBuilder.path("/product/{id}").buildAndExpand(product.getId()).toUri();
//            return ResponseEntity.created(uri).body(new DetailsProductDTO(product));
//            return ResponseEntity.ok().body(new ResponseModel("Produto cadastrado",dto));
        } catch (Exception error) {
            error.printStackTrace();
        }
        return ResponseEntity.internalServerError().build();
    }






}
