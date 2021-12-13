package nl.linhenjim.resources;

import nl.linhenjim.dao.ArtikelDao;
import nl.linhenjim.domain.Artikel;

import javax.inject.Inject;
import javax.ws.rs.*;
import java.util.List;

import static javax.ws.rs.core.MediaType.APPLICATION_JSON;

@Path("artikelen")
public class ArtikelenResource {

    @Inject
    ArtikelDao dao;

    @Inject
    private ArtikelResource artikelResource;

    @GET
    @Produces(APPLICATION_JSON)
    public List<Artikel> getArtikelen(@QueryParam("userId") Long userId) { // optionele parameter
        return dao.getArtikelen(userId);
    }

    @POST
    @Consumes(APPLICATION_JSON)
    @Produces(APPLICATION_JSON)
    public Artikel addArtikel(Artikel artikel) {
        return dao.addArtikel(artikel);
    }

    @Path("{artikelId}")
    public ArtikelResource getArtikel(@PathParam("artikelId") int id) {
        this.artikelResource.setId(id);
        return this.artikelResource;
    }
}