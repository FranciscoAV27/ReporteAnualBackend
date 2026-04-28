package mx.edu.unpa.reporteanualbackend.repositories.report.s4;

import mx.edu.unpa.reporteanualbackend.entities.report.s4.ActividadGestion;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface ActividadGestionRepository extends JpaRepository<ActividadGestion, Integer> {
    List<ActividadGestion> findByReporteId(Integer reporteId);
    void deleteByReporteId(Integer reporteId);
}