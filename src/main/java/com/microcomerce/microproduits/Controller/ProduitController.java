package com.microcomerce.microproduits.Controller;

import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;
import com.microcomerce.microproduits.Dao.ProduitDao;
import com.microcomerce.microproduits.Entite.Produit;
import com.microcomerce.microproduits.Exception.ProduitIntrouvableException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
public class ProduitController {

    @Autowired
    private ProduitDao produitDao;

    @RequestMapping(value="/Produits", method= RequestMethod.GET)
    public MappingJacksonValue listProduit(){
        Iterable<Produit> produits = produitDao.findAll();

        SimpleBeanPropertyFilter monfiltre = SimpleBeanPropertyFilter.serializeAllExcept("prixAchat");
        FilterProvider listeFiltre = new SimpleFilterProvider().addFilter("FiltreDynamique", monfiltre);

        MappingJacksonValue filtre = new MappingJacksonValue(produits);
        filtre.setFilters(listeFiltre);

        return filtre;
    }

    @GetMapping(value="/Produits/{id}")
    public Produit afficherproduit(@PathVariable int id){

        Produit produit = produitDao.findById(id);

        if (produit == null) throw new ProduitIntrouvableException("Le produit avec l'id" +id+ " est introuvable");

        return produit;
    }

    @GetMapping(value= "/test/Produits/{prixLimit}")
    public List<Produit> testrequetes(@PathVariable int prixLimit){
        return produitDao.findByPrixGreaterThan(150000);
    }

    //fait une recherche de mot
    @GetMapping(value = "/test/Produits/{recherche}")
    public List<Produit> testeDeRequetes(@PathVariable String recherche){
        return produitDao.findByNomLike("%"+recherche+"%");
    }

    @PostMapping("/Produits")
    public ResponseEntity<Void> ajouterProduit(@RequestBody Produit produit){
        Produit produit1 = produitDao.save(produit);

        if (produit1 == null)
            return ResponseEntity.noContent().build();

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(produit1.getId())
                .toUri();

        return ResponseEntity.created(location).build();

    }
}
