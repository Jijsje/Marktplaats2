package nl.linhenjim.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@MappedSuperclass
public class Categorie {
    @Id
    @GeneratedValue
    protected long id;

    @Column(unique = true)
    protected String naam;

}
