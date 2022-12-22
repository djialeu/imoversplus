package com.example.imovers.annonces.ImageData;

import com.example.imovers.annonces.Annonce;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Optional;

@Service
public class StorageService {
    @Autowired
    private StorageRepository repository;

    public ImageData uploadImage(MultipartFile file, Annonce annonce) throws IOException {

        ImageData imageData = repository.save(ImageData.builder()
                .name(annonce.getId() + annonce.getDatepublication().toInstant().toEpochMilli() +  file.getOriginalFilename())
                .type(file.getContentType())
                .imageData(ImageUtils.compressImage(file.getBytes()))
                .annonce(annonce)
                .build());
        if (imageData != null) {
            return imageData;
        }
        return null;
    }

    public String uploadOnlyImage(MultipartFile file) throws IOException {

        ImageData imageData = repository.save(ImageData.builder()
                .name( file.getOriginalFilename())
                .type(file.getContentType())
                .imageData(ImageUtils.compressImage(file.getBytes()))
                .build());
        if (imageData != null) {
            return "file uploaded successfully : " + file.getOriginalFilename();
        }
        return null;
    }

    public byte[] downloadImage(String fileName){
        Optional<ImageData> dbImageData = repository.findByName(fileName);
        byte[] images=ImageUtils.decompressImage(dbImageData.get().getImageData());
        return images;
    }
}
