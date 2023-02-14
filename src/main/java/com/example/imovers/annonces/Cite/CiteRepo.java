package com.example.imovers.annonces.Cite;

import org.springframework.data.jpa.repository.JpaRepository;

public interface CiteRepo extends JpaRepository<Cite, Long>{
    Cite findCiteByName(String cite);
}
