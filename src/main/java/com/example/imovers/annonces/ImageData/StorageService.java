package com.example.imovers.annonces.ImageData;

import com.example.imovers.annonces.Annonce.Annonce;
import com.example.imovers.annonces.Cite.Cite;
import com.example.imovers.annonces.Residence.Arrondissement;
import com.example.imovers.annonces.Residence.Ville;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Date;
import java.util.Optional;

@Service
public class StorageService {
    @Autowired
    private StorageRepository repository;

    public ImageData uploadImage(MultipartFile file, Annonce annonce) throws IOException {
        if(file != null && annonce != null){
            return repository.save(ImageData.builder()
                    .name(annonce.getId() + annonce.getDatepublication().toInstant().toEpochMilli() +  file.getOriginalFilename())
                    .type(file.getContentType())
                    .imageData(ImageUtils.compressImage(file.getBytes()))
                    .annonce(annonce)
                    .build());
        }
        return  null;
    }
    public ImageData uploadImageVille(MultipartFile file, Ville ville) throws IOException {
        if (file != null && ville != null){
            return repository.save(ImageData.builder()
                    .name(ville.getId() + new Date().toInstant().toEpochMilli() +  file.getOriginalFilename())
                    .type(file.getContentType())
                    .imageData(ImageUtils.compressImage(file.getBytes()))
                    .ville(ville)
                    .build());
        }
        return null;
    }
    public ImageData uploadImageArrondissement(MultipartFile file, Arrondissement arrondissement) throws IOException {
        if (file != null && arrondissement != null){
            return repository.save(ImageData.builder()
                    .name(arrondissement.getId() + new Date().toInstant().toEpochMilli() +  file.getOriginalFilename())
                    .type(file.getContentType())
                    .imageData(ImageUtils.compressImage(file.getBytes()))
                    .arrondissement(arrondissement)
                    .build());
        }
        return null;
    }
    public ImageData uploadImageCite(MultipartFile file, Cite cite) throws IOException {
        if (file != null && cite != null){
            return repository.save(ImageData.builder()
                    .name(cite.getId() + new Date().toInstant().toEpochMilli() +  file.getOriginalFilename())
                    .type(file.getContentType())
                    .imageData(ImageUtils.compressImage(file.getBytes()))
                    .cite(cite)
                    .build());
        }
        return null;
    }

    public String uploadOnlyImage(MultipartFile file) throws IOException {

        repository.save(ImageData.builder()
                .name(file.getOriginalFilename())
                .type(file.getContentType())
                .imageData(ImageUtils.compressImage(file.getBytes()))
                .build());
        return "file uploaded successfully : " + file.getOriginalFilename();
    }

    public byte[] downloadImage(String fileName){
        Optional<ImageData> dbImageData = repository.findByName(fileName);
        return ImageUtils.decompressImage(dbImageData.get().getImageData());
    }
}
