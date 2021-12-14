package nl.linhenjim.domain;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@Entity
public class Artikel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column
    private String titel;
    private String beschrijving;
    private double prijs;
    private Bezorgwijze bezorgwijze;
    private String datumAanmaak;

    @ManyToOne
    private Gebruiker verkoper;

    @ManyToOne
    private DienstCategorie dienstCategorie;

    @ManyToOne
    private ProductCategorie productCategorie;
}
