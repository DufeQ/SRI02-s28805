package pl.pja.s28805.sri02.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.hateoas.RepresentationModel;


@NoArgsConstructor
@Setter
@Data
public class GrupaDto extends RepresentationModel<GrupaDto> {
    private Long id;
    private Long nr;
    private String rok;
}
