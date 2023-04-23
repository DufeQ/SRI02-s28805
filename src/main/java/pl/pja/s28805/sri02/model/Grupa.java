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
    private Long nr;
    private String przedmiot;

    public Grupa(final Long nr, final String przedmiot){
        this.nr = nr;
        this.przedmiot = przedmiot;
    }

    @OneToMany(mappedBy = "grupa")
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private Set<Student> students;

}
