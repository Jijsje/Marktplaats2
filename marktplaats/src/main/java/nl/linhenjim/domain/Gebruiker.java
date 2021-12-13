package nl.linhenjim.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import nl.linhenjim.util.PasswordUtils;

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
        @NamedQuery(name = Gebruiker.FIND_BY_LOGIN_PASSWORD, query = "SELECT g FROM Gebruiker g WHERE g.username = :username AND g.wachtwoord = :wachtwoord")
})
public class Gebruiker {
    public static final String FIND_BY_LOGIN_PASSWORD = "Gebruiker.findByLoginAndPassword";
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String emailadres;

    @Column(unique = true)
    private String username;
    @Column(length = 256, nullable = false)
    private String wachtwoord;

    private String token;

    private String adres;

    private Bezorgwijze bezorgwijze;

    private boolean reglementGeaccepteerd;

    @PrePersist // to indicate that the method should be called before the entity is persisted into the database
    private void setUUID() {
//        id = randomUUID().toString()/*.replace("-", "")*/;
        wachtwoord = PasswordUtils.digestPassword(wachtwoord);
    }

}
