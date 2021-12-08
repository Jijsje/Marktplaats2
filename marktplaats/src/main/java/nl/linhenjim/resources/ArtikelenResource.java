package nl.linhenjim.resources;

import nl.linhenjim.dao.ArtikelDao;

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

    // Bij een lege param worden alle artikelen opgehaald, anders alleen de artikelen vd actieve gebruiker
    @GET
    @Produces(APPLICATION_JSON)
    public List getArtikelen(@QueryParam("userId") Long id) { // optionele parameter
        System.out.println("ARTIKELEN HALEN IS GELUKT");
        return dao.getArtikelen(id);
    }

    @Path("{artikelId}")
    public ArtikelResource getArtikel(@PathParam("artikelId") int id) {
        this.artikelResource.setId(id);
        return this.artikelResource;
    }
}
