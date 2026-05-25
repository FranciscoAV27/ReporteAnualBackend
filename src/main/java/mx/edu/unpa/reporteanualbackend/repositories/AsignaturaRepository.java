package mx.edu.unpa.reporteanualbackend.repositories;

import mx.edu.unpa.reporteanualbackend.entities.Asignatura;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AsignaturaRepository extends JpaRepository<Asignatura, Integer> {
    List<Asignatura> findByCarreraIdOrderBySemestreAscNombreAsc(Integer carreraId);
}