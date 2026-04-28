package mx.edu.unpa.reporteanualbackend.repositories.report.s1;

import mx.edu.unpa.reporteanualbackend.entities.report.s1.CursoImpartido;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface CursoImpartidoRepository extends JpaRepository<CursoImpartido, Integer> {
    List<CursoImpartido> findByReporteId(Integer reporteId);
    void deleteByReporteId(Integer reporteId);
}