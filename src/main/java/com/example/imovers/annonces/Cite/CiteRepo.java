package com.example.imovers.annonces.Cite;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

public interface CiteRepo extends JpaRepository<Cite, Long>{
    Cite findCiteByName(String cite);
    @Query("SELECT c FROM Cite c WHERE c.quartier.id = :quartierId")
    List<Cite> findByQuartierId(long quartierId);
    @Query("SELECT c from Cite c WHERE c.author.id = :appUserId")
    List<Cite> findByAppUserId(long appUserId);
}
