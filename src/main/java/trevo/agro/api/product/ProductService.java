package trevo.agro.api.product;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import trevo.agro.api.category.Category;
import trevo.agro.api.culture.Culture;
import trevo.agro.api.image.Image;
import trevo.agro.api.image.ImageService;
import trevo.agro.api.repository.CategoryRepository;
import trevo.agro.api.repository.CultureRepository;
import trevo.agro.api.repository.ImageRepository;
import trevo.agro.api.repository.ProductRepository;
import trevo.agro.api.utils.ResponseModel;
import trevo.agro.api.utils.ResponseModelEspec;
import trevo.agro.api.utils.ResponseModelEspecNoObject;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProductService {
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private CategoryRepository categoryRepository;
    @Autowired
    private CultureRepository cultureRepository;
    @Autowired
    private ImageRepository imageRepository;
    @Autowired
    private ImageService imageService;

    public ResponseEntity<ResponseModel> register(@RequestBody @Valid ProductSaveDTO dto) {
        try {
            List<Culture> cultures = cultureRepository.findByIdIn(dto.cultureIds());
            List<Category> categories = categoryRepository.findByIdIn(dto.categoryIds());
            List<Image> images = imageRepository.findByIdIn(dto.imageIds());
            if (cultures.isEmpty()) {
                return new ResponseEntity<>(new ResponseModelEspecNoObject("Cultura não encontrada"), HttpStatus.NOT_FOUND);
            }
            if (categories.isEmpty()) {
                return new ResponseEntity<>(new ResponseModelEspecNoObject("Categoria não encontrada"), HttpStatus.NOT_FOUND);
            }
            if (productRepository.existsByName(dto.getName())) {
                return new ResponseEntity<>(new ResponseModelEspecNoObject("Produto já existe!"), HttpStatus.BAD_REQUEST);
            }
            if (dto.getName() == null) {
                return new ResponseEntity<>(new ResponseModelEspecNoObject("Por favor informe um nome de produto válido!"), HttpStatus.BAD_REQUEST);
            }
            if (dto.getAreaSize() == null) {
                return new ResponseEntity<>(new ResponseModelEspecNoObject("Favor informe o tamanho de area do produto!"), HttpStatus.BAD_REQUEST);
            }
            if (dto.getDescription() == null) {
                return new ResponseEntity<>(new ResponseModelEspecNoObject("Favor informe a descrição do produto!"), HttpStatus.BAD_REQUEST);
            }
            Product product = new Product(dto, categories, cultures, images);
            productRepository.save(product);
            return new ResponseEntity<>(new ResponseModelEspec("Produto foi salvo", dto), HttpStatus.OK);
        } catch (Exception error) {
            error.printStackTrace();
        }
        return ResponseEntity.internalServerError().build();
    }

    public ResponseEntity<ResponseModel> list() {
        List<Product> products = productRepository.findAll();
        if (products.isEmpty()) {
            return new ResponseEntity<>(new ResponseModelEspecNoObject("Não existem produtos cadastrados"), HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(new ResponseModelEspec("Lista de produtos", products), HttpStatus.OK);
    }

    public ResponseEntity<?> details(@PathVariable Long id) {
        Product product = productRepository.findById(id).orElse(null);
        if (product == null || product.getImages().isEmpty()) {
            return new ResponseEntity<>(new ResponseModelEspecNoObject("Produto não encontrado"), HttpStatus.NOT_FOUND);
        }
        List<String> list = new ArrayList<>();
        for (Image image : product.getImages()){
            list.add ("http://localhost:8080/image/" + image.getId());
        }
        ProductImgDto productImgDto = new ProductImgDto(product,list);
        return new ResponseEntity<>(new ResponseModelEspec("Aqui esta os detalhes deste produto", productImgDto), HttpStatus.OK);
    }

    public ResponseEntity<ResponseModel> delete(@PathVariable Long id) {
        try {
            if (productRepository.existsById(id)) {
                    productRepository.deleteById(id);
                return new ResponseEntity<>(new ResponseModelEspecNoObject("Produto excluido"), HttpStatus.OK);
            }
            return new ResponseEntity<>(new ResponseModelEspecNoObject("Produto não encontrado!"), HttpStatus.NOT_FOUND);
        } catch (Error error) {
            error.printStackTrace();
        }
        return ResponseEntity.internalServerError().build();
    }

    public ResponseEntity<ResponseModel> alternarStatus(@PathVariable Long id) {
        Product byId = productRepository.findById(id).orElse(null);
        if (byId == null) {
            return new ResponseEntity<>(new ResponseModelEspecNoObject("Produto não encontrado"), HttpStatus.NOT_FOUND);
        }
        Boolean active = byId.getActive();
        if (active) {
            byId.setActive(Boolean.FALSE);
        } else {
            byId.setActive(Boolean.TRUE);
        }
        productRepository.save(byId);
        return new ResponseEntity<>(new ResponseModelEspecNoObject("Status atual do produto é " + active), HttpStatus.OK);
    }

    public ResponseEntity<ResponseModel> update(@Valid ProductSaveDTO dto, @PathVariable Long id) {
        try {
            //Sempre que for fazer alguma atualização de produto, será necessario informar
            // novamente qual o tipo de categoria e tipo de cultura do mesmo.
            List<Culture> cultures = cultureRepository.findByIdIn(dto.cultureIds());
            List<Category> categories = categoryRepository.findByIdIn(dto.categoryIds());
            if (cultures.isEmpty()) {
                return new ResponseEntity<>(new ResponseModelEspecNoObject("Cultura não encontrada"), HttpStatus.NOT_FOUND);
            }
            if (categories.isEmpty()) {
                return new ResponseEntity<>(new ResponseModelEspecNoObject("Categoria não encontrada"), HttpStatus.NOT_FOUND);
            }
            Product productExists = productRepository.findById(id).orElse(null);
            if (productExists == null) {
                return new ResponseEntity<>(new ResponseModelEspecNoObject("Produto não encontrado"), HttpStatus.NOT_FOUND);
            }
            if (productRepository.existsByName(dto.getName())) {
                return new ResponseEntity<>(new ResponseModelEspecNoObject("Produto já existe!"), HttpStatus.BAD_REQUEST);
            }
            productExists.update(dto, categories, cultures);
            productRepository.save(productExists);
            return new ResponseEntity<>(new ResponseModelEspecNoObject("Produto foi atualizado!"), HttpStatus.OK);
        } catch (Exception error) {
            error.printStackTrace();
        }
        return ResponseEntity.internalServerError().build();
    }

}
