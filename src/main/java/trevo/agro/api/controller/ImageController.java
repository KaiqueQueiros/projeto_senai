package trevo.agro.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import trevo.agro.api.image.ImageService;
import trevo.agro.api.utils.ResponseModel;
import trevo.agro.api.utils.ResponseModelEspecNoObject;

import java.io.IOException;

@RestController
@RequestMapping("/image")
public class ImageController {
    @Autowired
    private ImageService service;

    @PostMapping
    public ResponseEntity<ResponseModel> uploadImage(@RequestParam("image") MultipartFile photo) throws IOException {
        service.uploadImage(photo);
        return new ResponseEntity<>(new ResponseModelEspecNoObject("Imagem salva com sucesso"), HttpStatus.OK);
    }

    @GetMapping("/{fileName}")
    public ResponseEntity<?> downloadImage(@PathVariable String fileName) {
        byte[] imageData = service.downloadImage(fileName);
        return ResponseEntity.status(HttpStatus.OK).contentType(MediaType.valueOf("image/png"))
                .body(imageData);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<ResponseModel> deletarImagem(@PathVariable Long id) {
        service.delete(id);
        return new ResponseEntity<>(new ResponseModelEspecNoObject("Imagem foi excluida"), HttpStatus.OK);

    }

}
