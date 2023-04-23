package pl.pja.s28805.sri02.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@NoArgsConstructor
@Setter
@Data
public class GrupaDetailsDto {
    private Long id;
    private Long nr;
    private String przedmiot;

    private Set<StudentDto> students;
}
