package mx.edu.unpa.reporteanualbackend.services.servicesImpl;

import lombok.RequiredArgsConstructor;
import mx.edu.unpa.reporteanualbackend.dtos.periodo.PeriodoRequestDTO;
import mx.edu.unpa.reporteanualbackend.dtos.periodo.PeriodoResponseDTO;
import mx.edu.unpa.reporteanualbackend.entities.Periodo;
import mx.edu.unpa.reporteanualbackend.entities.Usuario;
import mx.edu.unpa.reporteanualbackend.entities.enums.EstadoPeriodo;
import mx.edu.unpa.reporteanualbackend.exceptions.BusinessException;
import mx.edu.unpa.reporteanualbackend.exceptions.ConflictException;
import mx.edu.unpa.reporteanualbackend.repositories.PeriodoRepository;
import mx.edu.unpa.reporteanualbackend.services.PeriodoService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PeriodoServiceImpl implements PeriodoService {

    private final PeriodoRepository periodoRepository;

    @Override
    @Transactional
    public PeriodoResponseDTO iniciar(PeriodoRequestDTO dto, Usuario creadoPor) {
        if (periodoRepository.findByEstado(EstadoPeriodo.ACTIVO).isPresent())
            throw new ConflictException("Ya hay un periodo activo.");

        if (!dto.getFechaLimite().isAfter(dto.getFechaApertura()))
            throw new BusinessException("La fecha límite debe ser posterior a la de apertura.");

        int anio = calcularAnioCiclo(dto.getFechaApertura());

        Periodo periodo = Periodo.builder()
                .anio(anio)
                .fechaApertura(dto.getFechaApertura())
                .fechaLimite(dto.getFechaLimite())
                .estado(EstadoPeriodo.ACTIVO)
                .instrucciones(dto.getInstrucciones())
                .creadoPor(creadoPor)
                .build();

        return toResponse(periodoRepository.save(periodo));
    }

    @Override
    @Transactional
    public PeriodoResponseDTO editar(Integer id, PeriodoRequestDTO dto) {
        Periodo periodo = findOrThrow(id);

        if (!dto.getFechaLimite().isAfter(dto.getFechaApertura()))
            throw new BusinessException("La fecha límite debe ser posterior a la fecha de apertura.");

        periodo.setFechaApertura(dto.getFechaApertura());
        periodo.setFechaLimite(dto.getFechaLimite());
        periodo.setInstrucciones(dto.getInstrucciones());

        return toResponse(periodoRepository.save(periodo));
    }

    @Override
    @Transactional
    public PeriodoResponseDTO cerrar(Integer id) {
        Periodo periodo = findOrThrow(id);

        if (periodo.getEstado() == EstadoPeriodo.CERRADO)
            throw new BusinessException("El periodo ya está cerrado.");

        periodo.setEstado(EstadoPeriodo.CERRADO);
        periodo.setCerradoEn(LocalDateTime.now());

        return toResponse(periodoRepository.save(periodo));
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<PeriodoResponseDTO> obtenerActivo() {
        return periodoRepository.findByEstado(EstadoPeriodo.ACTIVO)
                .map(this::toResponse);
    }

    // ── Helpers ────────────────────────────────────────────────

    private Periodo findOrThrow(Integer id) {
        return periodoRepository.findById(id)
                .orElseThrow(() -> new mx.edu.unpa.reporteanualbackend
                        .exceptions.ResourceNotFoundException("Periodo no encontrado: " + id));
    }

    // Ciclo va de octubre a septiembre:
    // Si la apertura es oct-dic del año X → ciclo es X / X+1 → anio = X+1
    // Si la apertura es ene-sep del año X → ciclo es X-1 / X   → anio = X
    private int calcularAnioCiclo(LocalDate fecha) {
        return fecha.getMonthValue() >= 10
                ? fecha.getYear() + 1
                : fecha.getYear();
    }

    private PeriodoResponseDTO toResponse(Periodo p) {
        String nombre = String.format("Reporte Anual %d-%d", p.getAnio() - 1, p.getAnio());
        return PeriodoResponseDTO.builder()
                .id(p.getId())
                .anio(p.getAnio())
                .nombre(nombre)
                .fechaApertura(p.getFechaApertura())
                .fechaLimite(p.getFechaLimite())
                .estado(p.getEstado())
                .instrucciones(p.getInstrucciones())
                .creadoEn(p.getCreadoEn())
                .cerradoEn(p.getCerradoEn())
                .creadoPorNombre(p.getCreadoPor() != null ? p.getCreadoPor().getNombre() : null)
                .creadoPorApellidos(p.getCreadoPor() != null ? p.getCreadoPor().getApellidos() : null)
                .build();
    }
}