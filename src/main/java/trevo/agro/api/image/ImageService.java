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
        return new ResponseEntity<>(new ResponseModelEspecNoObject("Imagem salva foi salva como :" + photo.getOriginalFilename()), HttpStatus.OK);

    }
    public byte[] downloadImage (String fileName){
        Optional<Image> dbImageData = imageRepository.findByName(fileName);
        byte[] images = ImageUtils.decompressImage(dbImageData.get().getImageData());
        return images;
    }

    public ResponseEntity<ResponseModel> delete(@PathVariable Long id) {
        if (imageRepository.findById(id).isPresent()) {
            imageRepository.deleteById(id);
            return new ResponseEntity<>(new ResponseModelEspecNoObject("Imagem excluida"),HttpStatus.OK);
        }
        return new ResponseEntity<>(new ResponseModelEspecNoObject("Imagem não encontrada"),HttpStatus.OK);
    }
}
