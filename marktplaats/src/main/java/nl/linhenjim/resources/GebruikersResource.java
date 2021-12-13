package nl.linhenjim.resources;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import nl.linhenjim.dao.GebruikerDao;
import nl.linhenjim.domain.Gebruiker;
import nl.linhenjim.filter.Authorized;
import nl.linhenjim.util.Admin;
import nl.linhenjim.util.PasswordUtils;
import nl.linhenjim.util.SimpleKeyGenerator;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import java.security.Key;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import java.util.logging.Logger;

import static java.time.LocalDateTime.now;
import static javax.ws.rs.core.HttpHeaders.AUTHORIZATION;
import static javax.ws.rs.core.MediaType.APPLICATION_JSON;
import static javax.ws.rs.core.Response.Status.UNAUTHORIZED;

@Path("/gebruikers")
public class GebruikersResource {
    @Inject
    private GebruikerDao gebruikerDao;

    @Inject
    private GebruikerResource gebruikerResource;

    @Context
    private UriInfo uriInfo;

    @Inject
    Admin admin;

    @Inject
    private SimpleKeyGenerator keyGenerator;

    @Inject
    private Logger log;


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
    @Path("/register")
    @Produces(APPLICATION_JSON)
    @Consumes(APPLICATION_JSON)
    public Gebruiker add(Gebruiker gebruiker) {
        String wachtwoord  = admin.genereerWachtwoord();
        System.out.println(wachtwoord);
        gebruiker.setWachtwoord(wachtwoord);
        return gebruikerDao.add(gebruiker);
    }
    @POST @Path("/login2")
    public Response login2(Gebruiker u) {
        try {
            String login = u.getUsername();
            String password = u.getWachtwoord();

            Gebruiker gevondenGebruiker = gebruikerDao.authenticate(login, password);
            String token = issueToken(login);
            gevondenGebruiker.setToken(token);
//            gevondenGebruiker.setWachtwoord("");
            return Response.ok()
                    .type(MediaType.APPLICATION_JSON_TYPE)
                    .header(AUTHORIZATION, "Bearer " + token)
                    .entity(gevondenGebruiker)
                    .build();
        } catch (Exception e) {
            return Response.status(UNAUTHORIZED).build();
        }
    }
//    @POST
//    @Path("/login")
//    public Gebruiker login(Gebruiker u) {
//        try {
//            System.out.println("ik zit in resources");
//            String username = u.getUsername();
//            String password = u.getWachtwoord();
//
//            Gebruiker user = gebruikerDao.authenticate(username, password);
//            String jwt = issueToken(username);
//
//            user.setToken(jwt);
//            u.setWachtwoord("");
//
//            return user;
//        } catch (Exception e) {
//            throw new NotAuthorizedException("User " + u + " is not authorized.");
//        }
//    }

    private String issueToken(String username) {
        Key password = keyGenerator.generateKey();
        String jwt = Jwts.builder()
                .setSubject(username)
                .setIssuer(uriInfo.getAbsolutePath().toString())
                .setIssuedAt(new Date())
                .setExpiration(toDate(now().plusMinutes(15L)))
                .signWith(SignatureAlgorithm.HS512, password)
                .compact();
        log.info("#### generated token: " + jwt);
        return jwt;
    }

    private Date toDate(LocalDateTime localDateTime) {
        return Date.from(localDateTime.atZone(ZoneId.systemDefault()).toInstant());
    }
}
