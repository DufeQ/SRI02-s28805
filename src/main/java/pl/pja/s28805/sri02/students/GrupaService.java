package pl.pja.s28805.sri02.students;

import lombok.RequiredArgsConstructor;
import org.springframework.hateoas.CollectionModel;
import org.springframework.stereotype.Component;
import pl.pja.s28805.sri02.dto.GrupaDetailsDto;
import pl.pja.s28805.sri02.dto.GrupaDto;
import pl.pja.s28805.sri02.dto.StudentDto;
import pl.pja.s28805.sri02.dto.mapper.GrupaDtoMapper;
import pl.pja.s28805.sri02.dto.mapper.StudentDtoMapper;
import pl.pja.s28805.sri02.model.Grupa;
import pl.pja.s28805.sri02.model.Student;
import pl.pja.s28805.sri02.repository.GrupaRepository;
import pl.pja.s28805.sri02.repository.StudentRepository;
import pl.pja.s28805.sri02.rest.GrupaController;
import pl.pja.s28805.sri02.rest.StudentsController;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;


//Student service implementuje StudentRepository
@Component
@RequiredArgsConstructor
public class GrupaService {


    private final GrupaRepository grupaRepository;
    private final StudentRepository studentRepository;
    private final GrupaDtoMapper grupaDtoMapper;
    private final StudentDtoMapper studentDtoMapper;


    public CollectionModel<GrupaDto> getGrupy(){
        final List<Grupa> groupList = grupaRepository.findAll();
        final List<GrupaDto> groupDtoList = groupList.stream()
                .map(grupaDtoMapper::convertToDto)
                .collect(Collectors.toList());
        for (GrupaDto dto : groupDtoList){
            dto.add(linkTo(methodOn(GrupaController.class).getGrupaById(dto.getId())).withSelfRel());
            //dto.add(linkTo(methodOn(GrupaController.class).getStudentsByGrupaId(dto.getId())).withSelfRel());
        }
        CollectionModel<GrupaDto> res = CollectionModel.of(groupDtoList, linkTo(methodOn(GrupaController.class).getGrupy()).withSelfRel());
        return res;
    }

    public GrupaDetailsDto getGrupaById(final Long groupId) {
        Optional<Grupa> group = grupaRepository.findById(groupId);
        if (group.isPresent())
            return grupaDtoMapper.convertToDtoDetails(group.get());
        else
            return null;
    }

    public CollectionModel<StudentDto> getStudentsByGrupaId(final Long grupaId) {
        final List<Student> studentList = grupaRepository.getStudentsByGrupaId(grupaId);
        final List<StudentDto> studentDtoList = studentList.stream()
                .map(studentDtoMapper::convertToDto)
                .collect(Collectors.toList());
        for (StudentDto dto : studentDtoList){
            dto.add(linkTo(methodOn(StudentsController.class).getStudentById(dto.getId())).withSelfRel());
            //dto.add(linkTo(methodOn(GrupaController.class).getStudentsByGrupaId(dto.getId())).withSelfRel());
        }
        CollectionModel<StudentDto> res = CollectionModel.of(studentDtoList, linkTo(methodOn(GrupaController.class).getStudentsByGrupaId(grupaId)).withSelfRel());
        return res;
    }


    public GrupaDto addGrupa(GrupaDto grupaDto){
        Grupa group = new Grupa(grupaDto.getNr(), grupaDto.getRok());
        group = grupaRepository.save(group);
        return grupaDtoMapper.convertToDto(group);
    }

    //Warto byłoby dodać isPresent() i w przypadku false zwracac null;
    public GrupaDto modifyGrupa(final Long groupDtoId, final GrupaDto groupDto){
        Optional<Grupa> grupa = grupaRepository.findById(groupDtoId);
        Grupa grupa1 = grupa.get();
        grupa1.setRok(groupDto.getRok());
        grupa1.setNr(groupDto.getNr());
        grupaRepository.save(grupa1);

        return grupaDtoMapper.convertToDto(grupa1);
    }

    public GrupaDetailsDto modifyGrupa(final Long groupDetailsDtoId, final Long idStudent){
        Optional<Grupa> grupa = grupaRepository.findById(groupDetailsDtoId);
        Grupa grupa1 = grupa.get();
        Optional<Student> student = studentRepository.findById(idStudent);
        Student student1 = student.get();
        if (student1.getGrupa() == null){
            student1.setGrupa(grupa1);
            grupa1.getStudents().add(student1);
        }
        else if (student1.getGrupa().getId() != groupDetailsDtoId/* && grupa1.getStudents().contains(student1)*/) {
            student1.setGrupa(grupa1);
            grupa1.getStudents().add(student1);
        }
        else {
            grupa1.getStudents().remove(student1);
            student1.setGrupa(null);
        }
        grupaRepository.save(grupa1);
        studentRepository.save(student1);
        return grupaDtoMapper.convertToDtoDetails(grupa1);
    }



    public GrupaDto deleteGrupa(final Long id){
        Optional<Grupa> group = grupaRepository.findById(id);
        Grupa group1 = group.get();
        group1.getStudents().stream().forEach(student -> student.setGrupa(null));
        grupaRepository.delete(group1);
        return grupaDtoMapper.convertToDto(group1);
    }


}
