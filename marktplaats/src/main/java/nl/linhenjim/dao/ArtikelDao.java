package nl.linhenjim.dao;

import nl.linhenjim.domain.Artikel;

import javax.ejb.Stateless;
import java.util.List;

@Stateless
public class ArtikelDao {

//    List artikelen = List.of;

    public Artikel getArtikel(int id) {
        return new Artikel(id);
    }

    public List getArtikelen() {
        return List.of("a", "b", "c"); // haal alle artikelen
    }

    public List getArtikelen(int id){ // id van user of product?
        return List.of("d", "e", "f");

    }
}
