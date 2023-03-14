package trevo.agro.api.image;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import trevo.agro.api.exceptions.models.BadRequestException;
import trevo.agro.api.exceptions.models.NotFoundException;
import trevo.agro.api.product.Product;
import trevo.agro.api.repository.ImageRepository;
import trevo.agro.api.repository.ProductRepository;
import trevo.agro.api.utils.ImageUtils;
import trevo.agro.api.utils.ResponseModelEspec;
import trevo.agro.api.utils.ResponseModelEspecNoObject;

import java.io.IOException;
import java.util.List;

@Service
public class ImageService {
    @Autowired
    ImageRepository imageRepository;
    @Autowired
    ProductRepository productRepository;

    public ResponseEntity<?> uploadImage(@RequestParam MultipartFile photo) throws IOException {
        if (imageRepository.existsByName(photo.getOriginalFilename())) {
            throw new NotFoundException("Imagem " + photo.getOriginalFilename() + " já existe");
        }
        Image image = imageRepository.save(Image.builder()
                .name(photo.getOriginalFilename())
                .type(photo.getContentType())
                .imageData(ImageUtils.compressImage(photo.getBytes())).build());
        return new ResponseEntity<>(new ResponseModelEspecNoObject("Imagem salva foi salva nome : " + image.getName()), HttpStatus.OK);
    }

    public ResponseEntity<?> listImages() {
        List<Image> images = imageRepository.findAll();
        return new ResponseEntity<>(new ResponseModelEspec("Detalhes de todos os produtos ", images), HttpStatus.OK);
    }

    public byte[] downloadImage(@PathVariable Long id) {
        Image dbImageData = imageRepository.findById(id).orElse(null);
        if (dbImageData == null) {
            throw new NotFoundException("Imagem não existe");
        }
        return ImageUtils.decompressImage(dbImageData.getImageData());
    }

    public ResponseEntity<?> delete(@PathVariable Long id) {
        Image image = imageRepository.findById(id).orElse(null);
        if (!imageRepository.existsById(id)) {
            throw new BadRequestException("Imagem com id " + id + "não encontrada");
        }
        List<Product> productList = productRepository.findByImages(image);
        if (productList.isEmpty()) {
            imageRepository.deleteById(id);
        }
        throw new BadRequestException("Imagem não pode ser excluida pois esta relacionada com produto");
    }
}
