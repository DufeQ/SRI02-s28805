package pl.pja.s28805.sri02.students;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.hateoas.CollectionModel;
import org.springframework.stereotype.Component;
import pl.pja.s28805.sri02.dto.StudentDto;
import pl.pja.s28805.sri02.dto.mapper.StudentDtoMapper;
import pl.pja.s28805.sri02.model.Student;
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
public class StudentService{

    //private final ModelMapper modelMapper;
    private final StudentRepository repository;
    private final StudentDtoMapper studentDtoMapper;


    //private StudentDto convertToDto(Student stud) {return modelMapper.map(stud, StudentDto.class);}
    //private Student convertToStudent(StudentDto stud) {return modelMapper.map(stud, Student.class);}

    public CollectionModel<StudentDto> getStudents(){
        final List<Student> studentList = repository.findAll();
        final List<StudentDto> studentDtoList = studentList.stream()
                .map(studentDtoMapper::convertToDto)
                .collect(Collectors.toList());
        for (StudentDto dto : studentDtoList){
            dto.add(linkTo(methodOn(StudentsController.class).getStudentById(dto.getId())).withSelfRel());
            //dto.add(linkTo(methodOn(GrupaController.class).getStudentsByGrupaId(dto.getId())).withSelfRel());
        }
        CollectionModel<StudentDto> res = CollectionModel.of(studentDtoList, linkTo(methodOn(StudentsController.class).getStudents()).withSelfRel());
        return res;
    }

    public StudentDto getStudentById(final Long studentId) {
        Optional<Student> student = repository.findById(studentId);
        if (student.isPresent())
            return studentDtoMapper.convertToDto(student.get());
        else
            return null;
    }

    public StudentDto addStudent(StudentDto studentDto){
        Student student = new Student(studentDto.getImie(), studentDto.getNazwisko(), studentDto.getNrIndeksu());
        student = repository.save(student);
        return studentDtoMapper.convertToDto(student);
    }

    //Warto byłoby dodać isPresent() i w przypadku false zwracac null;
    public StudentDto modifyStudent(final Long studentDtoId, final StudentDto studentDto){
        Optional<Student> student = repository.findById(studentDtoId);
        Student student1 = student.get();
        student1.setImie(studentDto.getImie());
        student1.setNazwisko(studentDto.getNazwisko());
        student1.setNrIndeksu(studentDto.getNrIndeksu());
        repository.save(student1);
        return studentDtoMapper.convertToDto(student1);
    }

    public StudentDto deleteStudent(final Long id){
        Optional<Student> student = repository.findById(id);
        Student student1 = student.get();
        repository.delete(student1);
        Long temp = student1.getId();
        return studentDtoMapper.convertToDto(student1).add(linkTo(methodOn(StudentsController.class).getStudentById(temp)).withSelfRel());
    }


}
