package pl.pja.s28805.sri02.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Entity
@Data
@NoArgsConstructor
@Setter
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String imie;
    private String nazwisko;
    private String nrIndeksu;

    public Student(final String imie, final String nazwisko, final String nrIndeksu) {
        this.imie = imie;
        this.nazwisko = nazwisko;
        this.nrIndeksu = nrIndeksu;
    }

    @ManyToOne
    @JoinColumn(name = "grupa_id")
    private Grupa grupa;
}
