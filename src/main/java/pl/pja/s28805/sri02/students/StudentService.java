package pl.pja.s28805.sri02.students;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class StudentService {

    private final StudentRepository repository;

    public Long addStudent(final String imie, final String nazwisko, final String nrIndeksu){
        Student student = new Student(imie, nazwisko, nrIndeksu);
        student = repository.save(student);

        return student.getId();
    }
}
