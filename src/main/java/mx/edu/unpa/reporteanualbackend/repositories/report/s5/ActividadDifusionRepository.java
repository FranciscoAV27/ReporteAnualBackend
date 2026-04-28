package mx.edu.unpa.reporteanualbackend.repositories.report.s5;

import mx.edu.unpa.reporteanualbackend.entities.report.s5.ActividadDifusion;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface ActividadDifusionRepository extends JpaRepository<ActividadDifusion, Integer> {
    List<ActividadDifusion> findByReporteId(Integer reporteId);
    void deleteByReporteId(Integer reporteId);
}