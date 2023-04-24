package pl.pja.s28805.sri02.dto;

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
    private Long nr;
    private String przedmiot;

    private Set<StudentDto> students;

}
