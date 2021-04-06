package com.regmi.imageservice.rest;

import com.regmi.imageservice.image.ImageManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;
import java.util.List;

@CrossOrigin
@RestController
public class ImageController {
    private ImageManager imageManager;

    @Autowired
    public ImageController(ImageManager imageManager) {
        this.imageManager = imageManager;
    }

    @PostMapping("/images")
    public List<String> uploadImage(@RequestPart(required = true,value = "file")
                                            MultipartFile[] file) throws IOException {

        return imageManager.uploadImage(file);
    }
    @GetMapping("/images/{image-name}")
    public HttpEntity<byte[]> getImage(@PathVariable String imageName) throws IOException {
        byte[] imageBytes= imageManager.getImage(imageName);
        HttpHeaders headers= new HttpHeaders();
        headers.setContentType(MediaType.IMAGE_JPEG);
        headers.setContentLength(imageBytes.length);
        return new HttpEntity<>(imageBytes,headers);
    }

}
