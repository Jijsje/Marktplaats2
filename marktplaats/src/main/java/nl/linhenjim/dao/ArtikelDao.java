package nl.linhenjim.dao;

import nl.linhenjim.domain.Artikel;

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

    // zoek op artikel ID
    public Optional<Artikel> getArtikel(int id) {
        return Optional.ofNullable(em.find(Artikel.class, id));
    }

    public List<Artikel> getArtikelen(String query, Optional<Long> gebruikerId) {
        if(gebruikerId.isPresent()) {
            return getEigenArtikelen(gebruikerId.get());
        }
        return query == null ?
                em.createQuery("SELECT a FROM Artikel a", Artikel.class).getResultList() :
                em.createQuery("SELECT a FROM Artikel a WHERE a.titel LIKE :query", Artikel.class)
                        .setParameter("query", '%'+ query+ '%')
                        .getResultList();
    }

    // alle artikelen van 1 gebruiker
    public List<Artikel> getEigenArtikelen(Long userId) {
        return em.createQuery("SELECT a FROM Artikel a WHERE a.verkoper.id = :userId", Artikel.class)
                .setParameter("userId", userId)
                .getResultList();
    }

    @TransactionAttribute(REQUIRED)
    public Artikel addArtikel(Artikel artikel) {
        em.persist(artikel);
        return artikel;
    }
}
