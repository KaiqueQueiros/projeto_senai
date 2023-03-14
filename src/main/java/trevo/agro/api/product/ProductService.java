package trevo.agro.api.product;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import trevo.agro.api.area.Area;
import trevo.agro.api.budget.Budget;
import trevo.agro.api.category.Category;
import trevo.agro.api.culture.Culture;
import trevo.agro.api.exceptions.models.BadRequestException;
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
    private BudgetRepository budgetRepository;
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
        if (productRepository.existsByName(dto.name())) {
            return new ResponseEntity<>(new ResponseModelEspecNoObject("Produto " + dto.name() + " ja existe"),HttpStatus.BAD_REQUEST);
        }
        Product product = new Product(dto, areas, categories, cultures, images);
        productRepository.save(product);
        return new ResponseEntity<>(new ResponseModelEspecNoObject("Produto " + dto.name() + " cadastrado"), HttpStatus.OK);
    }

    public ResponseEntity<?> list() {
        List<Product> productList = productRepository.findAll();
        if (productList.isEmpty()){
            throw new NotFoundException("Não existem produtos cadastrados");
        }
        List<ProductImgDTO> productImgDTOList = new ArrayList<>();
        for (Product product : productList) {
            List<String> list = new ArrayList<>();
            for (Image image : product.getImages()) {
                list.add("http://localhost:8080/image/" + image.getId());
            }
            ProductImgDTO productImgDto = new ProductImgDTO(product, list);
            productImgDTOList.add(productImgDto);
        }

        return new ResponseEntity<>(new ResponseModelEspec("Detalhes de todos os produtos", productImgDTOList), HttpStatus.OK);
    }
    public ResponseEntity<?> details(@PathVariable Long id) {
        Product product = productRepository.findById(id).orElse(null);
        if (product == null) {
            return new ResponseEntity<>(new ResponseModelEspecNoObject("Produto de id " + id + " não existe"),HttpStatus.BAD_REQUEST);
        }
        List<String> list = new ArrayList<>();
        for (Image image : product.getImages()) {
            list.add("http://localhost:8080/image/" + image.getId());
        }
        ProductImgDTO productImgDto = new ProductImgDTO(product, list);
        return new ResponseEntity<>(new ResponseModelEspec("Detalhes do produto " + product.getName(), productImgDto), HttpStatus.OK);
    }

    public ResponseEntity<?> delete(@PathVariable Long id) {
        Product product = productRepository.findById(id).orElse(null);
        List<Budget> budgetList = budgetRepository.findByProducts(product);
        if (!productRepository.existsById(id)) {
            throw new BadRequestException("Produto com id "+ id + " não encontrado");
        }
        if (budgetList.isEmpty()){
            productRepository.deleteById(id);
            return new ResponseEntity<>(new ResponseModelEspecNoObject("Produto excluido"),HttpStatus.OK);
        }
        throw new BadRequestException("Não foi possivel excluir esse produto pois o mesmo possui relacionamento com pedidos");
    }

    public ResponseEntity<?> alternarStatus(@PathVariable Long id) {
        Product byId = productRepository.findById(id).orElse(null);
        if (byId == null) {
            throw  new NotFoundException ("Produto não encontrado");
        }
        Boolean status = byId.getActive();
        if (status) {
            byId.setActive(Boolean.FALSE);
        } else {
            byId.setActive(Boolean.TRUE);
        }
        productRepository.save(byId);
        return new ResponseEntity<>(new ResponseModelEspecNoObject("Status atual do produto é " + status), HttpStatus.OK);
    }

    public ResponseEntity<?> update(@RequestBody @Valid ProductSaveDTO dto, @PathVariable Long id) {
        List<Culture> cultures = cultureRepository.findByIdIn(dto.cultureIds());
        List<Category> categories = categoryRepository.findByIdIn(dto.categoryIds());
        List<Image> images = imageRepository.findByIdIn(dto.imageIds());
        List<Area> areas = areaRepository.findByIdIn(dto.areasIds());
        Product productExists = productRepository.findById(id).orElse(null);
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
}
