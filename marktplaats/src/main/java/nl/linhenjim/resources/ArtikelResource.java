package nl.linhenjim.resources;

import nl.linhenjim.dao.ArtikelDao;
import nl.linhenjim.domain.Artikel;
import nl.linhenjim.util.Responses;
import javax.ws.rs.GET;
import javax.ws.rs.Produces;

import static javax.ws.rs.core.MediaType.APPLICATION_JSON;

public class ArtikelResource {

    ArtikelDao dao;

//    @GET
//    @Produces(APPLICATION_JSON)
//    public Artikel get() {
//        return dao.getArtikel(this.id).orElseThrow(() -> badRequest(this.id));
//    }
}
