package trevo.agro.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import trevo.agro.api.image.ImageService;
import trevo.agro.api.utils.ResponseModel;
import trevo.agro.api.utils.ResponseModelEspec;
import trevo.agro.api.utils.ResponseModelEspecNoObject;

import java.io.IOException;

@RestController
@RequestMapping("/image")
public class ImageController {
    @Autowired
    private ImageService service;

    @RequestMapping(method = RequestMethod.POST,value = "/register")
    public ResponseEntity<ResponseModel> uploadImage(@RequestParam("image") MultipartFile photo) throws IOException {
        return service.uploadImage(photo);
    }

    @RequestMapping(value = "/{id}",method = RequestMethod.GET)
    public ResponseEntity<?> downloadImage(@PathVariable Long id) {
        byte[] imageData = service.downloadImage(id);
        return ResponseEntity.status(HttpStatus.OK).contentType(MediaType.valueOf("image/png"))
                .body(imageData);
    }

    @RequestMapping(value = "/delete/{id}",method = RequestMethod.DELETE)
    public ResponseEntity<ResponseModel> deletarImagem(@PathVariable Long id) {
        return service.delete(id);


    }

}
