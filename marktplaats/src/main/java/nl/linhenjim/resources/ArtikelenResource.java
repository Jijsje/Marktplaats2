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

    @GET
    @Produces(APPLICATION_JSON)
    public List getArtikelen(@QueryParam("userId") int id) { // optionele parameter
        System.out.println("ARTIKELEN HALEN IS GELUKT");
        // als id leeg is haalt ie alle producten op, anders alleen de eigen aangeboden producten
        return dao.getArtikelen();
    }

    @Path("{artikelId}")
    public ArtikelResource getArtikel(@PathParam("artikelId") int id) {
        // geeft 1 artikel obv ID
        id = 4;
        return new ArtikelResource();
    }
}
