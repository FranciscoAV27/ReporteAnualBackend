package mx.edu.unpa.reporteanualbackend.repositories.report.s3;

import mx.edu.unpa.reporteanualbackend.entities.report.s3.ActividadDesarrollo;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface ActividadDesarrolloRepository extends JpaRepository<ActividadDesarrollo, Integer> {
    List<ActividadDesarrollo> findByReporteId(Integer reporteId);
    void deleteByReporteId(Integer reporteId);
}