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
        @NamedQuery(name = "ProductCategorie.findAll", query = "SELECT d FROM ProductCategorie d"),
        @NamedQuery(name = "ProductCategorie.findByQ", query = "SELECT d FROM ProductCategorie d WHERE d.naam LIKE :q")
})
public class ProductCategorie extends Categorie {
//    @Builder
//    public DienstCategorie(long id, String naam) {
//        super(id, naam);
//    }

    @Override
    public String toString() {
        return super.naam;
    }
}
