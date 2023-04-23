package pl.pja.s28805.sri02.students;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import pl.pja.s28805.sri02.dto.GrupaDto;
import pl.pja.s28805.sri02.dto.mapper.GrupaDtoMapper;
import pl.pja.s28805.sri02.model.Grupa;
import pl.pja.s28805.sri02.repository.GrupaRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


//Student service implementuje StudentRepository
@Component
@RequiredArgsConstructor
public class GrupaService {


    private final GrupaRepository repository;
    private final GrupaDtoMapper grupaDtoMapper;


    public List<GrupaDto> getGrupy(){
        final List<Grupa> groupList = repository.findAll();
        final List<GrupaDto> groupDtoList = groupList.stream()
                .map(grupaDtoMapper::convertToDto)
                .collect(Collectors.toList());
        return groupDtoList;
    }

    public GrupaDto getGrupaById(final Long groupId) {
        Optional<Grupa> group = repository.findById(groupId);
        if (group.isPresent())
            return grupaDtoMapper.convertToDto(group.get());
        else
            return null;
    }

    public GrupaDto addGrupa(GrupaDto groupDto){
        Grupa group = new Grupa(groupDto.getPrzedmiot());
        group = repository.save(group);
        return grupaDtoMapper.convertToDto(group);
    }

    //Warto byłoby dodać isPresent() i w przypadku false zwracac null;
    public GrupaDto modifyGrupa(final Long groupDtoId, final GrupaDto groupDto){
        Optional<Grupa> grupa = repository.findById(groupDtoId);
        Grupa grupa1 = grupa.get();
        grupa1.setPrzedmiot(groupDto.getPrzedmiot());
        repository.save(grupa1);
        return grupaDtoMapper.convertToDto(grupa1);
    }

    public void deleteGrupa(final Long id){
        Optional<Grupa> group = repository.findById(id);
        Grupa group1 = group.get();
        repository.delete(group1);
    }


}
