package trevo.agro.api.product;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import trevo.agro.api.area.Area;
import trevo.agro.api.category.Category;
import trevo.agro.api.culture.Culture;
import trevo.agro.api.exceptions.models.NotFoundException;
import trevo.agro.api.image.Image;
import trevo.agro.api.repository.*;
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
    private AreaRepository areaRepository;

    public ResponseEntity<ResponseModel> register(@RequestBody @Valid ProductSaveDTO dto) {
        List<Category> categories = categoryRepository.findByIdIn(dto.categoryIds());
        List<Culture> cultures = cultureRepository.findByIdIn(dto.cultureIds());
        List<Image> images = imageRepository.findByIdIn(dto.imageIds());
        List<Area> areas = areaRepository.findByIdIn(dto.areasIds());
        if (cultures.isEmpty()) {
            return new ResponseEntity<>(new ResponseModelEspecNoObject("Cultura informada não existe"), HttpStatus.BAD_REQUEST);
        }
        if (categories.isEmpty()) {
            return new ResponseEntity<>(new ResponseModelEspecNoObject("Categoria informada não existe"), HttpStatus.BAD_REQUEST);
        }
        if (images.isEmpty()) {
            return new ResponseEntity<>(new ResponseModelEspecNoObject("Imagem informada não existe"), HttpStatus.BAD_REQUEST);
        }
        if (areas.isEmpty()) {
            return new ResponseEntity<>(new ResponseModelEspecNoObject("Area informada não existe"), HttpStatus.BAD_REQUEST);
        }
        if (productRepository.existsByName(dto.name())) {
            return new ResponseEntity<>(new ResponseModelEspecNoObject("Produto " + dto.name() + " ja existe"),HttpStatus.BAD_REQUEST);
        }
        Product product = new Product(dto, areas, categories, cultures, images);
        productRepository.save(product);
        return new ResponseEntity<>(new ResponseModelEspecNoObject("Produto " + dto.name() + " cadastrado"), HttpStatus.OK);
    }

    public ResponseEntity<ResponseModel> list() {
        List<Product> products = productRepository.findAll();
        if (products.isEmpty()) {
            throw new NotFoundException("Não existem produtos cadastrados");
        }
        return new ResponseEntity<>(new ResponseModelEspec("Lista de produtos",products), HttpStatus.OK);
    }
    public ResponseEntity<ResponseModel> details(@PathVariable Long id) {
        Product product = productRepository.findById(id).orElse(null);
        if (product == null) {
            return new ResponseEntity<>(new ResponseModelEspecNoObject("Produto de id " + id + " não existe"),HttpStatus.BAD_REQUEST);
        }
        List<String> list = new ArrayList<>();
        for (Image image : product.getImages()) {
            list.add("http://localhost:8080/image/" + image.getId());
        }
        ProductImgDto productImgDto = new ProductImgDto(product, list);
        return new ResponseEntity<>(new ResponseModelEspec("Detalhes do produto " + product.getName(), productImgDto), HttpStatus.OK);
    }

    public ResponseEntity<ResponseModel> delete(@PathVariable Long id) {
        if (productRepository.existsById(id)) {
            productRepository.deleteById(id);
            return new ResponseEntity<>(new ResponseModelEspecNoObject("Produto excluido"), HttpStatus.OK);
        }
        throw new NotFoundException("Produto com id " + id + " não encontrado");
    }

    public ResponseEntity<ResponseModel> alternarStatus(@PathVariable Long id) {
        Product byId = productRepository.findById(id).orElse(null);
        if (byId == null) {
            throw  new NotFoundException ("Produto não encontrado");
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
        List<Culture> cultures = cultureRepository.findByIdIn(dto.cultureIds());
        List<Category> categories = categoryRepository.findByIdIn(dto.categoryIds());
        List<Image> images = imageRepository.findByIdIn(dto.imageIds());
        List<Area> areas = areaRepository.findByIdIn(dto.areasIds());
        Product productExists = productRepository.findById(id).orElse(null);
        if (cultures.isEmpty()) {
            return new ResponseEntity<>(new ResponseModelEspecNoObject("Cultura informada não existe"), HttpStatus.BAD_REQUEST);
        }
        if (categories.isEmpty()) {
            return new ResponseEntity<>(new ResponseModelEspecNoObject("Categoria informada não existe"), HttpStatus.BAD_REQUEST);
        }
        if (images.isEmpty()) {
            return new ResponseEntity<>(new ResponseModelEspecNoObject("Imagem informada não existe"), HttpStatus.BAD_REQUEST);
        }
        if (areas.isEmpty()) {
            return new ResponseEntity<>(new ResponseModelEspecNoObject("Area informada não existe"), HttpStatus.BAD_REQUEST);
        }
        if (productRepository.findById(id).isEmpty()) {
            return new ResponseEntity<>(new ResponseModelEspecNoObject("Produto com id "+ id +" não encontrado"),HttpStatus.BAD_REQUEST);
        }
        if (productRepository.existsByName(dto.name())) {
            return new ResponseEntity<>(new ResponseModelEspecNoObject("Produto com nome "  + dto.name() + " ja existe"),HttpStatus.BAD_REQUEST);
        }
        assert productExists != null;
        productExists.update(dto, categories, cultures, images, areas);
        productRepository.save(productExists);
        return new ResponseEntity<>(new ResponseModelEspecNoObject("Produto " + dto.name() +" foi atualizado!"), HttpStatus.OK);
    }
    /*Para as requisições de cadastro e de atualização de produto, sera necessário informar os campos categoria, cultura,
    * area e imagens*/
}
