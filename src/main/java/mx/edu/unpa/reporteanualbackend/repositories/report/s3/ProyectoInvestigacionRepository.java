package mx.edu.unpa.reporteanualbackend.repositories.report.s3;

import mx.edu.unpa.reporteanualbackend.entities.report.s3.ProyectoInvestigacion;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface ProyectoInvestigacionRepository extends JpaRepository<ProyectoInvestigacion, Integer> {
    List<ProyectoInvestigacion> findByReporteId(Integer reporteId);
    void deleteByReporteId(Integer reporteId);
}