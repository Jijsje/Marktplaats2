package nl.linhenjim.dao;

import nl.linhenjim.domain.Gebruiker;
import nl.linhenjim.util.PasswordUtils;
import nl.linhenjim.util.Responses;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;
import java.util.Optional;

import static javax.ejb.TransactionAttributeType.REQUIRED;

@Stateless
public class GebruikerDao {
    @PersistenceContext // Container managed EntityManager, not via @Inject
    private EntityManager em; // container, geef mij een EntityManager

    public List<Gebruiker> getAll(String q) {
        return em.createNamedQuery("Gebruiker.findAll", Gebruiker.class).getResultList();
    }

    public Optional<Gebruiker> get(Long id) {
        return Optional.ofNullable(em.find(Gebruiker.class, id));
    }

    @TransactionAttribute(REQUIRED)
    public Gebruiker add(Gebruiker gebruiker) {
        em.persist(gebruiker);
        return gebruiker;
    }

    public void delete(Long id) {
        get(id).ifPresentOrElse(em::remove, Responses.throwBadRequest(id));
    }

    public Gebruiker update(Long id, Gebruiker updatedGebruiker) {
        updatedGebruiker.setId(id);
        return em.merge(updatedGebruiker);
    }

    public Gebruiker authenticate(String username, String wachtwoord) {
        System.out.println("Authenticating");
        TypedQuery<Gebruiker> query = em.createNamedQuery(Gebruiker.FIND_BY_LOGIN_PASSWORD, Gebruiker.class);

        query.setParameter("username", username);
        query.setParameter("wachtwoord", PasswordUtils.digestPassword(wachtwoord));
        Gebruiker gebruiker = query.getSingleResult();
        if (gebruiker == null) throw new SecurityException("Invalid Gebruiker/password");
        return gebruiker;
    }
}
