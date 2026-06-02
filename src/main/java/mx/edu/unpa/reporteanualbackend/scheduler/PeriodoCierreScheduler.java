package mx.edu.unpa.reporteanualbackend.scheduler;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import mx.edu.unpa.reporteanualbackend.entities.enums.EstadoPeriodo;
import mx.edu.unpa.reporteanualbackend.repositories.PeriodoRepository;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Slf4j
@Component
@RequiredArgsConstructor
public class PeriodoCierreScheduler {

    private final PeriodoRepository periodoRepository;

    // Ejecuta todos los días a las 00:05
    @Scheduled(cron = "0 5 0 * * *")
    @Transactional
    public void cerrarPeriodosVencidos() {
        var vencidos = periodoRepository.findByEstadoAndFechaLimiteBefore(
                EstadoPeriodo.ACTIVO, LocalDate.now()
        );
        if (vencidos.isEmpty()) return;

        vencidos.forEach(p -> {
            p.setEstado(EstadoPeriodo.CERRADO);
            p.setCerradoEn(LocalDateTime.now());
            log.info("Periodo {} cerrado automáticamente por vencimiento de fecha límite.", p.getId());
        });

        periodoRepository.saveAll(vencidos);
    }
}