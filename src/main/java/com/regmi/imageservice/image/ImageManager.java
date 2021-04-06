package com.regmi.imageservice.image;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface ImageManager {
    List<String> uploadImage(MultipartFile[] file) throws IOException;
    byte[] getImage(String string) throws IOException;
}
