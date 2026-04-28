package mx.edu.unpa.reporteanualbackend.repositories.report.s1;

import mx.edu.unpa.reporteanualbackend.entities.report.s1.AsignaturaAfinidad;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface AsignaturaAfinidadRepository extends JpaRepository<AsignaturaAfinidad, Integer> {
    List<AsignaturaAfinidad> findByReporteId(Integer reporteId);
    void deleteByReporteId(Integer reporteId);
}