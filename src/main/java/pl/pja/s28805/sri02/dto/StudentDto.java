package pl.pja.s28805.sri02.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.hateoas.RepresentationModel;
import pl.pja.s28805.sri02.model.Grupa;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class StudentDto extends RepresentationModel<StudentDto> {
    private Long id;
    private String imie;
    @NotBlank(message = "Nazwisko jest wymagane")
    //@Size(min = 2, max = 255)
    private String nazwisko;
    private String nrIndeksu;
    //private Long nrGrupy;
}
