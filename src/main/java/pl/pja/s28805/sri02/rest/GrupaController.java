package pl.pja.s28805.sri02.rest;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.pja.s28805.sri02.dto.GrupaDto;
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
    public ResponseEntity<GrupaDto> getGrupaById(@PathVariable final Long id) {
        GrupaDto grupaDto = grupaService.getGrupaById(id);
        if (grupaDto == null)
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
        else
            return ResponseEntity.ok(grupaDto);
    }

    @GetMapping
    public ResponseEntity<List<GrupaDto>> getGrupy(){
        return ResponseEntity.ok(grupaService.getGrupy());
    }

    @PostMapping
    public ResponseEntity<GrupaDto> addGrupa(@RequestBody final GrupaDto groupDto){
                return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(grupaService.addGrupa(groupDto));
    }


    @PutMapping("/{id}")
    public ResponseEntity<GrupaDto> modifyGrupa(@PathVariable final Long id, @RequestBody final GrupaDto grupaDto){
        if (grupaService.getGrupaById(id) == null)
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        else {
            return ResponseEntity.ok(grupaService.modifyGrupa(id, grupaDto));
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteGroup(@PathVariable final Long id){
        if (grupaService.getGrupaById(id) == null)
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        else {
            grupaService.deleteGrupa(id);
            return ResponseEntity.ok(null);
        }
    }
}