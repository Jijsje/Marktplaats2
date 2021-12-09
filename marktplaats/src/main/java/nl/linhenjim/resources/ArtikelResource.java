package nl.linhenjim.resources;

import nl.linhenjim.dao.ArtikelDao;
import nl.linhenjim.domain.Artikel;

import nl.linhenjim.domain.Gebruiker;

import javax.inject.Inject;

import nl.linhenjim.util.Responses;

import javax.ws.rs.GET;
import javax.ws.rs.Produces;

import java.util.Optional;

import static javax.ws.rs.core.MediaType.APPLICATION_JSON;

public class ArtikelResource {

    @Inject
    ArtikelDao dao;
    int id;

    @GET
    @Produces(APPLICATION_JSON)
    public Optional<Artikel> get() {
        return dao.getArtikel(this.id);
    }

//    @GET
//    @Produces(APPLICATION_JSON)
//    public Artikel get() {
//        Gebruiker g = new Gebruiker();
//        return new Artikel(g);
//    }

    public void setId(int id) {
        this.id = id;
    }

}
