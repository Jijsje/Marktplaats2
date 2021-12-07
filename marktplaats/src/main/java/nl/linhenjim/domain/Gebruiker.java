package nl.linhenjim.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@XmlRootElement

@NamedQueries({
        @NamedQuery(name = "Gebruiker.findAll", query = "SELECT g FROM Gebruiker g ORDER BY g.emailadres DESC"),
        @NamedQuery(name = Gebruiker.FIND_BY_LOGIN_PASSWORD, query = "SELECT g FROM Gebruiker g WHERE g.emailadres = :login AND g.wachtwoord = :wachtwoord")
})
public class Gebruiker {
    public static final String FIND_BY_LOGIN_PASSWORD = "Gebruiker.findByLoginAndPassword";
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String emailadres;

    private String wachtwoord;
    private String adres;

    private Bezorgwijze bezorgwijze;

    private boolean reglementGeaccepteerd;

//    @OneToMany(mappedBy = "gebruiker", targetEntity = Artikel.class, cascade = CascadeType.ALL, orphanRemoval=true)
//    private List<Artikel> artikelen = new ArrayList<>();

//    void addArtikel(Artikel a) {
//        this.artikelen.add(a);
//    }
}
