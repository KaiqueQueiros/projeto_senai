package trevo.agro.api.category;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import trevo.agro.api.exceptions.models.NotFoundException;
import trevo.agro.api.repository.CategoryRepository;
import trevo.agro.api.repository.ProductRepository;
import trevo.agro.api.utils.ResponseModel;
import trevo.agro.api.utils.ResponseModelEspec;
import trevo.agro.api.utils.ResponseModelEspecNoObject;
import java.util.List;
import java.util.Optional;

@Service
public class CategoryService {
    @Autowired
    private CategoryRepository categoryRepository;
    @Autowired
    private ProductRepository productRepository;

    public ResponseEntity<ResponseModel> register(@RequestBody @Valid CategoryDTO dto) {
        if (categoryRepository.existsByName(dto.name())) {
            throw new NotFoundException("Nome de categoria ja existe");
        }
        Category category = new Category(dto);
        categoryRepository.save(category);
        return new ResponseEntity<>(new ResponseModelEspec("Categoria cadastrada!", dto), HttpStatus.OK);
    }

    public ResponseEntity<ResponseModel> list() {
        List<Category> categories = categoryRepository.findAll();
        if (categories.isEmpty()) {
            throw new NotFoundException("Não existem categorias cadastradas");
        }
        return new ResponseEntity<>(new ResponseModelEspec("Lista de categorias", categories), HttpStatus.OK);
    }

    public ResponseEntity<ResponseModel> delete(@PathVariable Long id) {
            Optional<Category> category = categoryRepository.findById(id);
            if (category.isEmpty()) {
                throw new NotFoundException("Categoria não encontrada");
            }
            categoryRepository.deleteById(id);
            return new ResponseEntity<>(new ResponseModelEspecNoObject("Categoria excluida!"), HttpStatus.OK);
    }

    public ResponseEntity<ResponseModel> update(@Valid CategoryDTO dto, @PathVariable Long id) {
            Category categories = categoryRepository.findById(id).orElse(null);
            if (categories == null) {
                throw new NotFoundException("Categoria inexistente");
            }
            if (categoryRepository.existsByName(dto.name())) {
                return new ResponseEntity<>(new ResponseModelEspecNoObject("Nome já existe!"), HttpStatus.BAD_REQUEST);
            }
            categories.update(dto);
            categoryRepository.save(categories);
            return new ResponseEntity<>(new ResponseModelEspecNoObject("Categoria foi atualizada!"), HttpStatus.OK);
    }
}
