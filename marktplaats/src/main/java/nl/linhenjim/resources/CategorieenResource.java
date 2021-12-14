package nl.linhenjim.resources;

import nl.linhenjim.dao.DienstCategorieDao;
import nl.linhenjim.dao.ProductCategorieDao;
import nl.linhenjim.domain.DienstCategorie;
import nl.linhenjim.domain.ProductCategorie;

import javax.inject.Inject;
import javax.ws.rs.*;
import java.util.List;

import static javax.ws.rs.core.MediaType.APPLICATION_JSON;

@Path("/categorieen")
public class CategorieenResource {
    @Inject
    DienstCategorieDao dienstCategorieDao;
    @Inject
    ProductCategorieDao productCategorieDao;

    @GET
    @Path("/dienst")
    @Produces(APPLICATION_JSON)
    public List<DienstCategorie> getAllDienst(
            @QueryParam("q") String q) {
        return dienstCategorieDao.getAll(q);
    }

    @POST
    @Path("/dienst")
    @Produces(APPLICATION_JSON)
    @Consumes(APPLICATION_JSON)
    public DienstCategorie addDienst(DienstCategorie c) {
        return dienstCategorieDao.add(c);
    }

    @GET
    @Path("/product")
    @Produces(APPLICATION_JSON)
    public List<ProductCategorie> getAllProduct(
            @QueryParam("q") String q) {
        return productCategorieDao.getAll(q);
    }

    @POST
    @Path("/product")
    @Produces(APPLICATION_JSON)
    @Consumes(APPLICATION_JSON)
    public ProductCategorie addProduct(ProductCategorie c) {
        return productCategorieDao.add(c);
    }

}
