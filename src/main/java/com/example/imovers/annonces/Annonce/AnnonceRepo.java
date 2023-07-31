package com.example.imovers.annonces.Annonce;

import com.example.imovers.annonces.Cite.Cite;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface AnnonceRepo extends JpaRepository<Annonce, Long>{
    @Query("SELECT a FROM Annonce a WHERE a.cite.id = :citeId")
    List<Annonce> findByCiteId (long citeId);
    @Query("SELECT a FROM Annonce a WHERE a.visibility.name = 'APPROVED' ")
    List<Annonce> getApprovedAnnonces();
}
