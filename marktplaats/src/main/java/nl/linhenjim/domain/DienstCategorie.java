package nl.linhenjim.domain;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

//@AllArgsConstructor
//@NoArgsConstructor
@Entity
@NamedQueries({
        @NamedQuery(name = "DienstCategorie.findAll", query = "SELECT d FROM DienstCategorie d"),
        @NamedQuery(name = "DienstCategorie.findByQ", query = "SELECT d FROM DienstCategorie d WHERE d.naam LIKE :q")
})
public class DienstCategorie extends Categorie {
//    @Builder
//    public DienstCategorie(long id, String naam) {
//        super(id, naam);
//    }

    @Override
    public String toString() {
        return super.naam;
    }
}
