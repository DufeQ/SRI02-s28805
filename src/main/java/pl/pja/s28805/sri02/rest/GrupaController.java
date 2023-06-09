package pl.pja.s28805.sri02.rest;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
//LINK byl ze złego pakietu wybrany
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.Link;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.pja.s28805.sri02.dto.GrupaDetailsDto;
import pl.pja.s28805.sri02.dto.GrupaDto;
import pl.pja.s28805.sri02.dto.StudentDto;
import pl.pja.s28805.sri02.students.GrupaService;
import pl.pja.s28805.sri02.students.StudentService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/groups")
public class GrupaController {

    private final StudentService studentService;
    private final GrupaService grupaService;

    @GetMapping("/{id}")
    public ResponseEntity<GrupaDetailsDto> getGrupaById(@PathVariable final Long id) {
        GrupaDetailsDto grupaDetailsDto = grupaService.getGrupaById(id);

        if (grupaDetailsDto == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
            //return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
        }
        else {
            //Link linkSelf = new Link("http://localhost:8080/api/groups/" + id); - nie dziala
            //grupaDetailsDto.add(Link.of("http://localhost:8080/api/groups/" + id));
            //Link linkself = linkTo(methodOn(GrupaController.class).getGrupaById(id)).withSelfRel();
            grupaDetailsDto.add(linkTo(methodOn(GrupaController.class).getStudentsByGrupaId(grupaDetailsDto.getId())).withSelfRel());
            grupaDetailsDto.add(linkTo(methodOn(GrupaController.class).getGrupaById(id)).withSelfRel());
            return ResponseEntity.ok(grupaDetailsDto);
            //return new ResponseEntity<>(grupaDetailsDto, HttpStatus.OK);
        }
    }


    @GetMapping
    public ResponseEntity<CollectionModel<GrupaDto>> getGrupy(){
        return ResponseEntity.ok(grupaService.getGrupy());
    }

    @GetMapping("/{grupaId}/students")
    public ResponseEntity<CollectionModel<StudentDto>> getStudentsByGrupaId(@PathVariable final Long grupaId) {
//        GrupaDetailsDto grupaDto = grupaService.getStudentsByGrupaId(grupaId);
//        if (grupaDto == null)
//            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
//        else
        return ResponseEntity.ok(grupaService.getStudentsByGrupaId(grupaId));
    }
    @PostMapping
    public ResponseEntity<GrupaDto> addGrupa(@Valid @RequestBody final GrupaDto groupDto){

        GrupaDto grupaDto = grupaService.addGrupa(groupDto);
        Long temp = grupaDto.getId();//to jest potrzebne, żeby w linku była liczba a nie "{id}"
//                return ResponseEntity
//                .status(HttpStatus.CREATED)
//                .body(grupaService.addGrupa(groupDto).add(linkTo(methodOn(GrupaController.class).getGrupaById(groupDto.getId())).withSelfRel()));
        return ResponseEntity.ok(grupaDto.add(linkTo(methodOn(GrupaController.class).getGrupaById(temp)).withSelfRel()));//status(HttpStatus.CREATED).body

    }


    @PutMapping("/{id}")
    public ResponseEntity<GrupaDto> modifyGrupa(@PathVariable final Long id, @RequestBody final GrupaDto grupaDto){
        if (grupaService.getGrupaById(id) == null)
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        else {
            GrupaDto grupaDto2 = grupaService.modifyGrupa(id ,grupaDto);
            Long temp = grupaDto2.getId();
            return ResponseEntity.ok(grupaDto2.add(linkTo(methodOn(GrupaController.class).getGrupaById(temp)).withSelfRel()));
        }
    }

    @PutMapping("/{id}/{idStudent}")
    public ResponseEntity<GrupaDetailsDto> modifyGrupa(@PathVariable final Long id, @PathVariable final Long idStudent){
        if (studentService.getStudentById(idStudent) == null)
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        else {
            if (grupaService.getGrupaById(id) == null){
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
            }
            else {
                GrupaDetailsDto grupaDetailsDto = grupaService.modifyGrupa(id, idStudent);
                grupaDetailsDto.add(linkTo(methodOn(GrupaController.class).getStudentsByGrupaId(id)).withSelfRel());
                grupaDetailsDto.add(linkTo(methodOn(GrupaController.class).getGrupaById(id)).withSelfRel());
                return ResponseEntity.ok(grupaDetailsDto);

            }
        }
    }
    @DeleteMapping("/{id}")
    public ResponseEntity deleteGroup(@PathVariable final Long id){
        if (grupaService.getGrupaById(id) == null)
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        else {
            GrupaDto grupaDto = grupaService.deleteGrupa(id);
            return ResponseEntity.ok(grupaDto.add(linkTo(methodOn(GrupaController.class).getGrupaById(id)).withSelfRel()));
        }
    }
}
