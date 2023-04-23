package pl.pja.s28805.sri02.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Setter;


@NoArgsConstructor
@Setter
@Data
public class GrupaDto {
    private Long id;
    private Long nr;
    private String przedmiot;
}
