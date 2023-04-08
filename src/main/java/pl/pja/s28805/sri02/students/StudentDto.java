package pl.pja.s28805.sri02.students;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor

public class StudentDto {
    private Long id;
    private String imie;
    private String nazwisko;
    private String nrIndeksu;
}
