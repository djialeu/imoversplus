package com.example.imovers.annonces.ImageData;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface StorageRepository extends JpaRepository<ImageData,Long> {

    Optional<ImageData> findByName(String fileName);

    Optional<List<ImageData>> findImageDataByAnnonce_Id(String annonce_id);
}
