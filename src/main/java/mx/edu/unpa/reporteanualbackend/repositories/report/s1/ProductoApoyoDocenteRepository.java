package mx.edu.unpa.reporteanualbackend.repositories.report.s1;

import mx.edu.unpa.reporteanualbackend.entities.report.s1.ProductoApoyoDocente;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface ProductoApoyoDocenteRepository extends JpaRepository<ProductoApoyoDocente, Integer> {
    List<ProductoApoyoDocente> findByReporteId(Integer reporteId);
    void deleteByReporteId(Integer reporteId);
}