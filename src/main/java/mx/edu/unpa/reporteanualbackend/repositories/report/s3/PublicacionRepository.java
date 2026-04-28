package mx.edu.unpa.reporteanualbackend.repositories.report.s3;

import mx.edu.unpa.reporteanualbackend.entities.report.s3.Publicacion;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface PublicacionRepository extends JpaRepository<Publicacion, Integer> {
    List<Publicacion> findByReporteId(Integer reporteId);
    void deleteByReporteId(Integer reporteId);
}