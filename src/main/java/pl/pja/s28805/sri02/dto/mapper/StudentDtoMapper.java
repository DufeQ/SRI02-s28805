package pl.pja.s28805.sri02.dto.mapper;

import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;
import pl.pja.s28805.sri02.dto.StudentDto;
import pl.pja.s28805.sri02.model.Student;

@Component
@AllArgsConstructor
public class StudentDtoMapper {
    private final ModelMapper modelMapper;

    public StudentDto convertToDto(Student stud) {return modelMapper.map(stud, StudentDto.class);}
    public Student convertToStudent(StudentDto stud) {return modelMapper.map(stud, Student.class);}

}
