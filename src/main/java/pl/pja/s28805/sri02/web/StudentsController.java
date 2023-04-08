package pl.pja.s28805.sri02.web;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.pja.s28805.sri02.students.StudentDto;

import java.util.List;

@RestController
@RequestMapping("/api/students")
public class StudentsController {

    @GetMapping("/test")
    //@RequestMapping("/test") alternatywnie
    public String getString(){
        return "Hello world!";
    }

    @GetMapping
    public ResponseEntity<List<StudentDto>> getStudents(){
        final List<StudentDto> list = List.of(new StudentDto("Bartosz", "J", "s28805"));
        return ResponseEntity.ok(list);
    }


}
