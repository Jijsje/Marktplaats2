package nl.linhenjim.domain;

import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@NoArgsConstructor
public class Artikel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column
    private String titel;

    @ManyToOne
    private Gebruiker verkoper;

    public Artikel(Gebruiker verkoper) {
        id = 0;
        this.verkoper = verkoper;
    }
}
