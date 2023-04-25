package pl.pja.s28805.sri02.model;

import jakarta.annotation.Generated;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.GeneratedColumn;

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
    private String rok;

    public Grupa(final Long nr, final String rok){
        this.nr = nr;
        this.rok = rok;
    }

    @OneToMany(mappedBy = "grupa")
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private Set<Student> students;

}
