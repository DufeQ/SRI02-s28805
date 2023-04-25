package pl.pja.s28805.sri02.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.boot.actuate.endpoint.web.Link;
import org.springframework.hateoas.RepresentationModel;

import java.util.Set;

@NoArgsConstructor
@Setter
@Data
public class GrupaDetailsDto extends RepresentationModel<GrupaDetailsDto> {
    private Long id;
    //@NotBlank(message = "Nr grupy jest wymagany")
    //@Pattern(regexp = "[1-9]+", message = "Numer może składać się tylko z cyfr")
    private String nr;
    //@NotBlank(message = "Rok grupy jest wymagany")
    //@Pattern(regexp = "[a-zA-Z]+", message = "Numer może składać się tylko z cyfr rzymskich")
    private String rok;

    private Set<StudentDto> students;

}
