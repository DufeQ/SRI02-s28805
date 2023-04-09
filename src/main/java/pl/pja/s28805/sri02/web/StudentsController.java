package pl.pja.s28805.sri02.web;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.pja.s28805.sri02.students.Student;
import pl.pja.s28805.sri02.students.StudentDto;
import pl.pja.s28805.sri02.students.StudentService;

import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/students")
public class StudentsController {

    private final ModelMapper modelMapper;

    private StudentDto convertToDto(Student stud) {return modelMapper.map(stud, StudentDto.class);}
    private Student converToStudent(StudentDto stud) {return modelMapper.map(stud, Student.class);}

    private final StudentService studentService;
    @GetMapping("/test")
    //@RequestMapping("/test") alternatywnie
    public String getString(){
        return "Hello world!";
    }

    @GetMapping
    public ResponseEntity<List<StudentDto>> getStudents(){
        return ResponseEntity.ok(studentService.getStudents());
    }

    @PostMapping
    public ResponseEntity<Long> addStudent(@RequestBody final StudentDto student){
        final Long result = studentService.addStudent(student.getImie(), student.getNazwisko(), student.getNrIndeksu());

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(result);
    }


}
