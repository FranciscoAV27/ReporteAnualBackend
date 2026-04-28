package mx.edu.unpa.reporteanualbackend.repositories.report.s2;

import mx.edu.unpa.reporteanualbackend.entities.report.s2.DireccionTesis;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface DireccionTesisRepository extends JpaRepository<DireccionTesis, Integer> {
    List<DireccionTesis> findByReporteId(Integer reporteId);
    void deleteByReporteId(Integer reporteId);
}