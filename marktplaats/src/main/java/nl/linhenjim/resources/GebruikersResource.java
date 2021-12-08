package nl.linhenjim.resources;

import nl.linhenjim.dao.GebruikerDao;
import nl.linhenjim.domain.Gebruiker;

import javax.inject.Inject;
import javax.ws.rs.*;
import java.util.List;

import static javax.ws.rs.core.MediaType.APPLICATION_JSON;

@Path("/gebruikers")
public class GebruikersResource {
    @Inject
    private GebruikerDao gebruikerDao;

    @Inject
    private GebruikerResource gebruikerResource;

    @GET
    @Produces(APPLICATION_JSON)
    public List<Gebruiker> getAll(
            @QueryParam("q") String q) {
        return gebruikerDao.getAll(q);
    }

    @Path("{id}")
    public GebruikerResource get(@PathParam("id") long id) {
        this.gebruikerResource.setId(id);
        return this.gebruikerResource;
    }

    @POST
    @Produces(APPLICATION_JSON)
    @Consumes(APPLICATION_JSON)
    public Gebruiker add(Gebruiker newContact) {
        return gebruikerDao.add(newContact);
    }
}
