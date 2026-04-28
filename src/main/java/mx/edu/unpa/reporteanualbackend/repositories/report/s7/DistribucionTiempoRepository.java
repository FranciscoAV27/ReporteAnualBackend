package mx.edu.unpa.reporteanualbackend.repositories.report.s7;

import mx.edu.unpa.reporteanualbackend.entities.report.s7.DistribucionTiempo;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface DistribucionTiempoRepository extends JpaRepository<DistribucionTiempo, Integer> {
    List<DistribucionTiempo> findByReporteId(Integer reporteId);
    List<DistribucionTiempo> findByReporteIdOrderByOrden(Integer reporteId);
    void deleteByReporteId(Integer reporteId);
}