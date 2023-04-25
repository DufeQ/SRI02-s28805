package pl.pja.s28805.sri02.rest;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.pja.s28805.sri02.dto.StudentDto;
import pl.pja.s28805.sri02.students.StudentService;

import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

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
            studentDto.add(linkTo(methodOn(StudentsController.class).getStudentById(id)).withSelfRel());
            return ResponseEntity.ok(studentDto);
    }

    @GetMapping
    public ResponseEntity<CollectionModel<StudentDto>> getStudents(){
        return ResponseEntity.ok(studentService.getStudents());
    }

    @PostMapping
    public ResponseEntity<StudentDto> addStudent(@Valid @RequestBody final StudentDto studentDto){
        StudentDto studentDto1 = studentService.addStudent(studentDto);
        Long temp = studentDto1.getId();
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(studentDto1.add(linkTo(methodOn(StudentsController.class).getStudentById(temp)).withSelfRel()));
    }


    @PutMapping("/{id}")
    public ResponseEntity<StudentDto> modifyStudent(@Valid  @PathVariable final Long id, @RequestBody final StudentDto studentDto){
        if (studentService.getStudentById(id) == null)
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        else {
            StudentDto studentDto1 = studentService.modifyStudent(id, studentDto);
            Long temp = studentDto1.getId();
            return ResponseEntity.ok(studentDto1.add(linkTo(methodOn(StudentsController.class).getStudentById(temp)).withSelfRel()));
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteStudent(@PathVariable final Long id){
        if (studentService.getStudentById(id) == null)
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        else {
            StudentDto studentDto = studentService.deleteStudent(id);
            return ResponseEntity.ok(studentDto);
        }
    }
}
