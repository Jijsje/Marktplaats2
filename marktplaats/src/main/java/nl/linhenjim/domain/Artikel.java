package nl.linhenjim.domain;

import javax.persistence.*;

public class Artikel {
    int id;
    Gebruiker verkoper;

    public Artikel(Gebruiker verkoper) {
        id = 0;
        this.verkoper = verkoper;
    }
}
