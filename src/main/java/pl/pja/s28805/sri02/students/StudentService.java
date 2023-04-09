package pl.pja.s28805.sri02.students;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;
import org.springframework.ui.ModelMap;

import java.util.List;
import java.util.stream.Collectors;


//Student service implementuje StudentRepository
@Component
@RequiredArgsConstructor
public class StudentService{
    private final ModelMapper modelMapper;
    private final StudentRepository repository;


    private StudentDto convertToDto(Student stud) {return modelMapper.map(stud, StudentDto.class);}
    private Student converToStudent(StudentDto stud) {return modelMapper.map(stud, Student.class);}

    public Long addStudent(final String imie, final String nazwisko, final String nrIndeksu){
        Student student = new Student(imie, nazwisko, nrIndeksu);
        student = repository.save(student);

        return student.getId();
    }

//    public List<Student> findAll() {
//        return repository.findAll();
//    }

    public List<StudentDto> getStudents(){
        final List<Student> studentList = repository.findAll();
        final List<StudentDto> studentDtoList = studentList.stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
        return studentDtoList;
    }

}
