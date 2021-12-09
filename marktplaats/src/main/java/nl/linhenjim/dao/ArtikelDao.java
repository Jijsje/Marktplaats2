package nl.linhenjim.dao;

import nl.linhenjim.domain.Artikel;
import nl.linhenjim.domain.Gebruiker;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.Optional;

import static javax.ejb.TransactionAttributeType.REQUIRED;

@Stateless
public class ArtikelDao {

    @PersistenceContext
    private EntityManager em;

    public Optional<Artikel> getArtikel(int id) {
        return Optional.ofNullable(em.find(Artikel.class, id));
    }

    public List getArtikelen(Long id){ // id van user of product?
        return id == null ?
                em.createQuery("SELECT a FROM Artikel a").getResultList() :
                em.createQuery("SELECT a, v FROM Artikel a JOIN a.verkoper v").getResultList();
    }

    @TransactionAttribute(REQUIRED)
    public Artikel addArtikel(Artikel artikel) {
        em.persist(artikel);
        return artikel;
    }
}
