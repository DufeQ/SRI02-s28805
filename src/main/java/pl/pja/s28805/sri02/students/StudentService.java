package pl.pja.s28805.sri02.students;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;
import org.springframework.ui.ModelMap;

import java.util.List;


//Student service implementuje StudentRepository
@Component
@RequiredArgsConstructor
public class StudentService{
    private final ModelMapper modelMapper;
    private final StudentRepository repository;

    public Long addStudent(final String imie, final String nazwisko, final String nrIndeksu){
        Student student = new Student(imie, nazwisko, nrIndeksu);
        student = repository.save(student);

        return student.getId();
    }

    public List<Student> findAll() {
        return repository.findAll();
    }

}
