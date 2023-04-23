package pl.pja.s28805.sri02.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import pl.pja.s28805.sri02.model.Grupa;

import java.util.List;
import java.util.Optional;

public interface GrupaRepository extends JpaRepository<Grupa, Long> {
    List<Grupa> findAll();

    @Query("from Grupa as g left join fetch g.students where g.id =: grupaId")
    Optional<Grupa> getGrupaDetailsById(@Param("grupaId") Long grupaId);
}
