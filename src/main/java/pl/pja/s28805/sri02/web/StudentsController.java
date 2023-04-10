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

    private final StudentService studentService;

    @GetMapping("/{id}")
    public ResponseEntity<StudentDto> getStudentById(@PathVariable final Long id) {
        StudentDto studentDto = studentService.getStudentById(id);
        if (studentDto == null)
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
        else
            return ResponseEntity.ok(studentDto);
    }

    @GetMapping
    public ResponseEntity<List<StudentDto>> getStudents(){
        return ResponseEntity.ok(studentService.getStudents());
    }

    @PostMapping
    public ResponseEntity<StudentDto> addStudent(@RequestBody final StudentDto student){
                return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(studentService.addStudent(student));
    }

    @PutMapping("/{id}")
    public ResponseEntity<StudentDto> modifyStudent(@PathVariable final Long id, @RequestBody final StudentDto studentDto){
        if (studentService.getStudentById(id) == null)
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        else {
            return ResponseEntity.ok(studentService.modifyStudent(id, studentDto));
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteStudent(@PathVariable final Long id){
        if (studentService.getStudentById(id) == null)
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        else {
            studentService.deleteStudent(id);
            return ResponseEntity.ok(null);
        }
    }


}
