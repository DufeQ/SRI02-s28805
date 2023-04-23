package pl.pja.s28805.sri02.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import pl.pja.s28805.sri02.model.Student;

import java.util.List;

public interface StudentRepository extends JpaRepository<Student, Long> {
    List<Student> findAll();
}
