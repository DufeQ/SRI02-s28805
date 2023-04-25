package pl.pja.s28805.sri02.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
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
    @NotBlank(message = "Imie jest wymagane")
    @Pattern(regexp="[a-zA-Z]+", message = "Imie może składać się tylko z liter")
    private String imie;
    @NotBlank(message = "Nazwisko jest wymagane")
    @Pattern(regexp="[a-zA-Z]+", message = "Naziwsko może składać się tylko z liter")
    //@Size(min = 2, max = 255)
    private String nazwisko;
    @NotBlank(message = "Nr indeksu jest wymagany")
    @Pattern(regexp="s[0-9]+", message = "Nr indeksu musi mieć postać: sXXXXX ")
    private String nrIndeksu;
    //private Long nrGrupy;
}
