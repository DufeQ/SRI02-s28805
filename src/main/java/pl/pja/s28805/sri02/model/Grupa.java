package pl.pja.s28805.sri02.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.Set;

@Entity
@Data
@NoArgsConstructor
@Setter
public class Grupa {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long nr;
    private String przedmiot;

    public Grupa(final String przedmiot){
        this.przedmiot = przedmiot;
    }

    @OneToMany(mappedBy = "grupa")
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private Set<Student> students;

}
