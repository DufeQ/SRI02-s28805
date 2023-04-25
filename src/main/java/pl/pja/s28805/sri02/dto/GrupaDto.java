package pl.pja.s28805.sri02.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.hateoas.RepresentationModel;


@NoArgsConstructor
@Setter
@Getter
@Data
public class GrupaDto extends RepresentationModel<GrupaDto> {
    private Long id;

    @NotBlank(message = "Nr grupy jest wymagany")
    @Pattern(regexp = "[1-9]+", message = "Numer może składać się tylko z cyfr")
    private String nr;

    @NotBlank(message = "Rok grupy jest wymagany")
    @Pattern(regexp = "[IVXLCDM]+", message = "Numer może składać się tylko z cyfr rzymskich")
    private String rok;
}
