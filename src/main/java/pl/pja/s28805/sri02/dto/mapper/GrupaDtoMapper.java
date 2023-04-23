package pl.pja.s28805.sri02.dto.mapper;

import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;
import pl.pja.s28805.sri02.dto.GrupaDetailsDto;
import pl.pja.s28805.sri02.dto.GrupaDto;
import pl.pja.s28805.sri02.model.Grupa;

@AllArgsConstructor
@Component
public class GrupaDtoMapper {
    private final ModelMapper modelMapper;


    public GrupaDetailsDto convertToDtoDetails(Grupa g) {return modelMapper.map(g, GrupaDetailsDto.class);}

    public GrupaDto convertToDto(Grupa g) {return modelMapper.map(g, GrupaDto.class);}
    private Grupa convertToGrupa(GrupaDto g) {return modelMapper.map(g, Grupa.class);}



}
