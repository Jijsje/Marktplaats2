package nl.linhenjim.resources;

import nl.linhenjim.dao.GebruikerDao;
import nl.linhenjim.domain.Gebruiker;
import nl.linhenjim.util.Responses;

import javax.enterprise.context.Dependent;
import javax.inject.Inject;
import javax.ws.rs.*;

import static javax.ws.rs.core.MediaType.APPLICATION_JSON;

@Dependent
public class GebruikerResource {
    private long id;

    @Inject
    private GebruikerDao gebruikerDao;

    @GET
    @Produces(APPLICATION_JSON)
    public Gebruiker get() {
        return gebruikerDao.get(this.id).orElseThrow(() -> Responses.badRequest(this.id));
    }

    @DELETE
    public void delete() {
        gebruikerDao.delete(this.id);
    }

    @PUT
    @Produces(APPLICATION_JSON)
    @Consumes(APPLICATION_JSON)
    public Gebruiker put(Gebruiker updatedGebruiker) {
        return gebruikerDao.update(this.id, updatedGebruiker);
    }

    public void setId(long id) {
        this.id = id;
    }
}
