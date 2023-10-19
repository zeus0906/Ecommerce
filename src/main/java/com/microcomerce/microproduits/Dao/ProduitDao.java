package com.microcomerce.microproduits.Dao;

import com.microcomerce.microproduits.Entite.Produit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProduitDao extends JpaRepository<Produit, Integer > {

    Produit findById(int id);
    List<Produit> findByPrixGreaterThan(int prixLimit);
    List<Produit> findByNomLike(String recherche);
    //Produit findAll(int id);
}
