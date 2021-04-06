package com.regmi.imageservice.image;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class ImageManagerImpl implements ImageManager{
    @Override
    public List<String> uploadImage(MultipartFile[] file) throws IOException {
        ArrayList<String> imageUrls= new ArrayList<>() ;
        for(MultipartFile multipartFile :file){
            String uniqueFileName = UUID.randomUUID().toString();
            File file1 = new File("C:\\Users\\himal\\Desktop\\Product-project\\image-cloud\\"+uniqueFileName+".JPEG");
            FileOutputStream fileOutputStream= new FileOutputStream(file1);
            fileOutputStream.write(multipartFile.getBytes());
            fileOutputStream.close();
            imageUrls.add("http://192.168.100.6:8093/images/"+uniqueFileName);
        }
        return imageUrls;
    }

    @Override
    public byte[] getImage(String string) throws IOException {
        Path path = Paths.get("C:\\Users\\himal\\Desktop\\Product-project\\image-cloud\\"+string+".JPEG");
        try{
            return Files.readAllBytes(path);
        }catch (NoSuchFileException e){
            System.out.println(e.getMessage());
        }
        return null;
    }
}
