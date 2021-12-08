package nl.linhenjim.dao;

import nl.linhenjim.domain.Artikel;
import nl.linhenjim.domain.Gebruiker;

import javax.ejb.Stateless;
import java.util.List;

@Stateless
public class ArtikelDao {

    List artikelen;

    public Artikel getArtikel(int id) {
        // create query: get artikel where id = id
        Gebruiker g = new Gebruiker();
        System.out.println("Artikel met id " + id + " opgehaald!");
        return new Artikel(g);
    }

    public List getArtikelen(Long id){ // id van user of product?
        return id == null ?
                List.of("a", "b", "c", "d", "e", "f") :
                List.of("d", "e", "f");
    }
}
