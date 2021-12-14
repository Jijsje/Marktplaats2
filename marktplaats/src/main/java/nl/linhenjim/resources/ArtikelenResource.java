package nl.linhenjim.resources;

import nl.linhenjim.dao.ArtikelDao;
import nl.linhenjim.domain.Artikel;

import javax.inject.Inject;
import javax.ws.rs.*;
import java.util.List;
import java.util.Optional;

import static javax.ws.rs.core.MediaType.APPLICATION_JSON;

@Path("artikelen")
public class ArtikelenResource {

    @Inject
    ArtikelDao dao;

    @Inject
    private ArtikelResource artikelResource;

//    @GET
//    @Produces(APPLICATION_JSON)
//    public List<Artikel> getAlleArtikelen() {
//        System.out.println("QUERY 1");
//        return dao.getArtikelen();
//    }

    @GET // voor alle artikelen EN zoekfunctie EN 1 gebruiker
    @Produces(APPLICATION_JSON)
    public List<Artikel> getArtikelen(@QueryParam("q") String query, @QueryParam("userId") Long gebruikerId) { // optionele parameter
        Optional<Long> g = Optional.ofNullable(gebruikerId);
        return dao.getArtikelen(query, g);
    }

//    @GET // zoeken obv gebruiker
//    @Produces(APPLICATION_JSON)
//    public List<Artikel> getEigenArtikelen(@QueryParam("userId") Long userId) { // optionele parameter
//        System.out.println("QUERY 3 = " + userId);
//        return dao.getEigenArtikelen(userId);
//    }

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