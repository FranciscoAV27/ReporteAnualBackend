package mx.edu.unpa.reporteanualbackend.repositories.report.s3;

import mx.edu.unpa.reporteanualbackend.entities.report.s3.IndicadorProyecto;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface IndicadorProyectoRepository extends JpaRepository<IndicadorProyecto, Integer> {
    List<IndicadorProyecto> findByProyectoId(Integer proyectoId);
    void deleteByProyectoId(Integer proyectoId);
}