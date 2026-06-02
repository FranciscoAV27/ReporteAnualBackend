package mx.edu.unpa.reporteanualbackend.repositories;

import mx.edu.unpa.reporteanualbackend.entities.Periodo;
import mx.edu.unpa.reporteanualbackend.entities.enums.EstadoPeriodo;
import org.springframework.data.jpa.repository.JpaRepository;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface PeriodoRepository extends JpaRepository<Periodo, Integer> {

    // Periodo activo actual
    Optional<Periodo> findByEstado(EstadoPeriodo estado);

    // Verificar si ya existe un periodo para ese año
    boolean existsByAnio(Integer anio);

    // Para el job de cierre automático
    List<Periodo> findByEstadoAndFechaLimiteBefore(EstadoPeriodo estado, LocalDate fecha);
}