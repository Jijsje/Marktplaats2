package nl.linhenjim.dao;

import nl.linhenjim.domain.Artikel;
import nl.linhenjim.domain.Categorie;
import nl.linhenjim.domain.DienstCategorie;
import nl.linhenjim.domain.Gebruiker;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.Optional;

import static javax.ejb.TransactionAttributeType.REQUIRED;

@Stateless
public class DienstCategorieDao {
    @PersistenceContext // Container managed EntityManager, not via @Inject
    private EntityManager em; // container, geef mij een EntityManager

    public List<DienstCategorie> getAll(String q) {
        return q == null ?
                em.createNamedQuery("DienstCategorie.findAll", DienstCategorie.class)
                        .getResultList() :
                em.createNamedQuery("DienstCategorie.findByQ", DienstCategorie.class)
                        .setParameter("q", "%" + q + "%")
                        .getResultList();
    }

    public Optional<DienstCategorie> get(Long id) {
        return Optional.ofNullable(em.find(DienstCategorie.class, id));
    }

    @TransactionAttribute(REQUIRED)
    public DienstCategorie add(DienstCategorie c) {
        System.out.println(c.toString());
        em.persist(c);
        return c;
    }
}
