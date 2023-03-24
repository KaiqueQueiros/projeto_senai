package trevo.agro.api.category;

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
import trevo.agro.api.repository.CategoryRepository;
import trevo.agro.api.repository.ProductRepository;
import trevo.agro.api.utils.ResponseModelEspec;
import trevo.agro.api.utils.ResponseModelEspecNoObject;
import java.util.List;

@Service
public class CategoryService {
    @Autowired
    private CategoryRepository categoryRepository;
    @Autowired
    private ProductRepository productRepository;

    public ResponseEntity<?> register(@RequestBody @Valid CategoryDTO dto) {
        if (categoryRepository.existsByName(dto.name())) {
            throw new BadRequestException("Nome de categoria ja existe");
        }
        Category category = new Category(dto);
        categoryRepository.save(category);
        return new ResponseEntity<>(new ResponseModelEspec("Categoria " + dto.name() + " cadastrada!", dto), HttpStatus.OK);
    }

    public ResponseEntity<?> list() {
        List<Category> categories = categoryRepository.findAll();
        if (categories.isEmpty()) {
            throw new NotFoundException("Não existem categorias cadastradas");
        }
        return new ResponseEntity<>(new ResponseModelEspec("Lista de categorias", categories), HttpStatus.OK);
    }

    public ResponseEntity<?> delete(@PathVariable Long id) {
        Category category = categoryRepository.findById(id).orElse(null);
        List<Product> productList = productRepository.findByCategories(category);
        if (!categoryRepository.existsById(id)) {
            throw new NotFoundException("Categoria com id " + id + " não foi encontrada");
        }
        if (productList.isEmpty()) {
            categoryRepository.deleteById(id);
            return new ResponseEntity<>(new ResponseModelEspecNoObject("Categoria excluida"), HttpStatus.OK);
        }
        throw new BadRequestException("Categoria não pode ser excluida pois possui relacionamento com produto");
    }

    public ResponseEntity<?> update(@Valid CategoryDTO dto, @PathVariable Long id) {
        Category category = categoryRepository.findById(id).orElse(null);
        if (category == null) {
            throw new NotFoundException("Categoria inexistente");
        }
        if (categoryRepository.existsByName(dto.name())) {
            throw new BadRequestException("Nome " + dto.name() + " já existe");
        }
        category.update(dto);
        categoryRepository.save(category);
        return new ResponseEntity<>(new ResponseModelEspec("Categoria foi atualizada!", category), HttpStatus.OK);
    }
}
