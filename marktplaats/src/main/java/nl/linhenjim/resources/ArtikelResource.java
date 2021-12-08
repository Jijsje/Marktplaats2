package nl.linhenjim.resources;

import nl.linhenjim.dao.ArtikelDao;
import nl.linhenjim.domain.Artikel;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Produces;

import static javax.ws.rs.core.MediaType.APPLICATION_JSON;

public class ArtikelResource {

    @Inject
    ArtikelDao dao;
    int id;

    @GET
    @Produces(APPLICATION_JSON)
    public Artikel get() {
        return dao.getArtikel(this.id);
    }

    public void setId(int id) {
        this.id = id;
    }
}
