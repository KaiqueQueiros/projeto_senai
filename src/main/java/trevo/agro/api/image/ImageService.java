package trevo.agro.api.image;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import trevo.agro.api.repository.ImageRepository;
import trevo.agro.api.utils.ImageUtils;
import trevo.agro.api.utils.ResponseModel;
import trevo.agro.api.utils.ResponseModelEspecNoObject;
import java.io.IOException;
import java.util.Optional;

@Service
public class ImageService {
    @Autowired
    private ImageRepository imageRepository;

    public ResponseEntity<ResponseModel> uploadImage(@RequestParam MultipartFile photo) throws IOException {
        Image image = imageRepository.save(Image.builder()
                .name(photo.getOriginalFilename())
                .type(photo.getContentType())
                .imageData(ImageUtils.compressImage(photo.getBytes())).build());
        return new ResponseEntity<>(new ResponseModelEspecNoObject("Imagem salva foi salva nome : " + image.getName()), HttpStatus.OK);

    }
    public byte[] downloadImage (Long id){
        Optional<Image> dbImageData = imageRepository.findById(id);
        return ImageUtils.decompressImage(dbImageData.get().getImageData());
    }

    public ResponseEntity<ResponseModel> delete(@PathVariable Long id) {
        if (imageRepository.existsById(id)) {
                imageRepository.deleteById(id);
            return new ResponseEntity<>(new ResponseModelEspecNoObject("Imagem excluida"),HttpStatus.OK);
        }
        return new ResponseEntity<>(new ResponseModelEspecNoObject("Imagem não encontrada"),HttpStatus.BAD_REQUEST);
    }
}
