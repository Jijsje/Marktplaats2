package nl.linhenjim.dao;

import nl.linhenjim.domain.DienstCategorie;
import nl.linhenjim.domain.ProductCategorie;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.Optional;

import static javax.ejb.TransactionAttributeType.REQUIRED;

@Stateless
public class ProductCategorieDao {
    @PersistenceContext // Container managed EntityManager, not via @Inject
    private EntityManager em; // container, geef mij een EntityManager

    public List<ProductCategorie> getAll(String q) {
        return q == null ?
                em.createNamedQuery("ProductCategorie.findAll", ProductCategorie.class)
                        .getResultList() :
                em.createNamedQuery("ProductCategorie.findByQ", ProductCategorie.class)
                        .setParameter("q", "%" + q + "%")
                        .getResultList();
    }

    public Optional<ProductCategorie> get(Long id) {
        return Optional.ofNullable(em.find(ProductCategorie.class, id));
    }

    @TransactionAttribute(REQUIRED)
    public ProductCategorie add(ProductCategorie c) {
        System.out.println(c.toString());
        em.persist(c);
        return c;
    }
}
