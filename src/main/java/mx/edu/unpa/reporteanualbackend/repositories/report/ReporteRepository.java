package mx.edu.unpa.reporteanualbackend.repositories.report;

import mx.edu.unpa.reporteanualbackend.entities.enums.EstadoReporte;
import mx.edu.unpa.reporteanualbackend.entities.report.Reporte;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.util.List;
import java.util.Optional;

public interface ReporteRepository extends JpaRepository<Reporte, Integer> {
    Optional<Reporte> findByProfesorIdAndAnio(Integer profesorId, Integer anio);
    List<Reporte> findByEstado(EstadoReporte estado);
    List<Reporte> findByProfesorId(Integer profesorId);

    // Para jefe de carrera: profesores de su carrera y su estado de entrega
    @Query("""
        SELECT r FROM Reporte r
        JOIN r.profesor p
        WHERE p.carrera.id = :carreraId
        AND r.anio = :anio
    """)
    List<Reporte> findByCarreraIdAndAnio(@Param("carreraId") Integer carreraId,
                                         @Param("anio") Integer anio);

    List<Reporte> findByAnio(Integer anio);
}