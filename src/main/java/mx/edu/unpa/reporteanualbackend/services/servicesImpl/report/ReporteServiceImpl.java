package mx.edu.unpa.reporteanualbackend.services.servicesImpl.report;

import lombok.RequiredArgsConstructor;
import mx.edu.unpa.reporteanualbackend.dtos.report.ReporteRequestDTO;
import mx.edu.unpa.reporteanualbackend.dtos.report.ValidacionRequestDTO;
import mx.edu.unpa.reporteanualbackend.dtos.report.ReporteResponseDTO;
import mx.edu.unpa.reporteanualbackend.entities.Usuario;
import mx.edu.unpa.reporteanualbackend.entities.enums.EstadoReporte;
import mx.edu.unpa.reporteanualbackend.entities.report.Reporte;
import mx.edu.unpa.reporteanualbackend.exceptions.*;
import mx.edu.unpa.reporteanualbackend.mappers.report.ReporteMapper;
import mx.edu.unpa.reporteanualbackend.repositories.UsuarioRepository;
import mx.edu.unpa.reporteanualbackend.repositories.report.ReporteRepository;
import mx.edu.unpa.reporteanualbackend.services.report.ReporteService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ReporteServiceImpl implements ReporteService {

    private final ReporteRepository reporteRepository;
    private final UsuarioRepository usuarioRepository;
    private final ReporteMapper reporteMapper;

    @Override
    @Transactional
    public ReporteResponseDTO crear(Integer profesorId, ReporteRequestDTO dto) {
        Usuario profesor = usuarioRepository.findById(profesorId)
                .orElseThrow(() -> new ResourceNotFoundException("Profesor no encontrado: " + profesorId));
        if (reporteRepository.findByProfesorIdAndAnio(profesorId, dto.getAnio()).isPresent())
            throw new ConflictException("Ya existe un reporte para el año " + dto.getAnio());
        Reporte reporte = reporteMapper.toEntity(dto);
        reporte.setProfesor(profesor);
        reporte.setEstado(EstadoReporte.BORRADOR);
        return reporteMapper.toResponse(reporteRepository.save(reporte));
    }

    @Override
    @Transactional(readOnly = true)
    public ReporteResponseDTO obtenerPorId(Integer id) {
        return reporteMapper.toResponse(findOrThrow(id));
    }

    @Override
    @Transactional
    public ReporteResponseDTO guardarBorrador(Integer id, ReporteRequestDTO dto) {
        Reporte reporte = findOrThrow(id);
        if (reporte.getEstado() == EstadoReporte.PENDIENTE_VALIDACION
                || reporte.getEstado() == EstadoReporte.ACEPTADO)
            throw new BusinessException("No se puede editar un reporte en estado: " + reporte.getEstado());
        reporteMapper.updateFromDto(dto, reporte);
        return reporteMapper.toResponse(reporteRepository.save(reporte));
    }

    /*@Override
    @Transactional
    public ReporteResponseDTO enviarARevision(Integer id) {
        Reporte reporte = findOrThrow(id);
        if (reporte.getEstado() != EstadoReporte.BORRADOR)
            throw new BusinessException("Solo se pueden enviar reportes en estado BORRADOR");
        reporte.setEstado(EstadoReporte.PENDIENTE_VALIDACION);
        reporte.setEnviadoEn(LocalDateTime.now());
        return reporteMapper.toResponse(reporteRepository.save(reporte));
    }*/

    @Override
    @Transactional
    public ReporteResponseDTO enviarARevision(Integer id) {
        Reporte reporte = findOrThrow(id);
        if (reporte.getEstado() != EstadoReporte.BORRADOR
                && reporte.getEstado() != EstadoReporte.RECHAZADO)  // ← agrega esto
            throw new BusinessException("Solo se pueden enviar reportes en estado BORRADOR o RECHAZADO");
        reporte.setEstado(EstadoReporte.PENDIENTE_VALIDACION);
        reporte.setEnviadoEn(LocalDateTime.now());
        return reporteMapper.toResponse(reporteRepository.save(reporte));
    }

    /*@Override
    @Transactional
    public ReporteResponseDTO validar(Integer id, ValidacionRequestDTO dto) {
        Reporte reporte = findOrThrow(id);
        if (reporte.getEstado() != EstadoReporte.PENDIENTE_VALIDACION)
            throw new BusinessException("Solo se pueden validar reportes en estado PENDIENTE_VALIDACION");
        if (dto.getAprobado()) {
            reporte.setEstado(EstadoReporte.ACEPTADO);
            reporte.setAprobadoEn(LocalDateTime.now());
        } else {
            if (dto.getComentariosAdmin() == null || dto.getComentariosAdmin().isBlank())
                throw new BusinessException("El motivo de rechazo es obligatorio");
            reporte.setEstado(EstadoReporte.RECHAZADO);
            reporte.setComentariosAdmin(dto.getComentariosAdmin());
        }
        return reporteMapper.toResponse(reporteRepository.save(reporte));
    }*/

    /*@Override
    @Transactional
    public ReporteResponseDTO validar(Integer id, ValidacionRequestDTO dto, Usuario secretaria) {
        Reporte reporte = findOrThrow(id);
        if (reporte.getEstado() != EstadoReporte.PENDIENTE_VALIDACION)
            throw new BusinessException("Solo se pueden validar reportes en estado PENDIENTE_VALIDACION");

        if (dto.getAprobado()) {
            reporte.setEstado(EstadoReporte.ACEPTADO);
            reporte.setAprobadoEn(LocalDateTime.now());
            reporte.setAprobadoPor(secretaria);
        } else {
            if (dto.getComentariosAdmin() == null || dto.getComentariosAdmin().isBlank())
                throw new BusinessException("El motivo de rechazo es obligatorio");
            reporte.setEstado(EstadoReporte.RECHAZADO);
            reporte.setComentariosAdmin(dto.getComentariosAdmin());
            reporte.setRechazadoPor(secretaria);
            reporte.setRechazadoEn(LocalDateTime.now());
        }
        return reporteMapper.toResponse(reporteRepository.save(reporte));
    }*/

    @Override
    @Transactional
    public ReporteResponseDTO validar(Integer id, ValidacionRequestDTO dto, Usuario secretaria) {
        Reporte reporte = findOrThrow(id);
        if (reporte.getEstado() != EstadoReporte.PENDIENTE_VALIDACION)
            throw new BusinessException("Solo se pueden validar reportes en estado PENDIENTE_VALIDACION");

        if (dto.getAprobado()) {
            reporte.setEstado(EstadoReporte.ACEPTADO);
            reporte.setAprobadoEn(LocalDateTime.now());
            reporte.setAprobadoPor(secretaria);

            // Crear automáticamente el reporte del siguiente ciclo
            int siguienteAnio = reporte.getAnio() + 1;
            boolean yaExiste = reporteRepository
                    .findByProfesorIdAndAnio(reporte.getProfesor().getId(), siguienteAnio)
                    .isPresent();

            if (!yaExiste) {
                Reporte siguiente = Reporte.builder()
                        .profesor(reporte.getProfesor())
                        .anio(siguienteAnio)
                        .estado(EstadoReporte.BORRADOR)
                        .build();
                reporteRepository.save(siguiente);
            }

        } else {
            if (dto.getComentariosAdmin() == null || dto.getComentariosAdmin().isBlank())
                throw new BusinessException("El motivo de rechazo es obligatorio");
            reporte.setEstado(EstadoReporte.RECHAZADO);
            reporte.setComentariosAdmin(dto.getComentariosAdmin());
            reporte.setRechazadoPor(secretaria);
            reporte.setRechazadoEn(LocalDateTime.now());
        }

        return reporteMapper.toResponse(reporteRepository.save(reporte));
    }

    @Override
    @Transactional
    public void touch(Integer id) {
        Reporte reporte = findOrThrow(id);
        reporte.setActualizadoEn(LocalDateTime.now());
        reporteRepository.save(reporte);
    }

    @Override
    @Transactional(readOnly = true)
    public List<ReporteResponseDTO> obtenerPorProfesor(Integer profesorId) {
        return reporteRepository.findByProfesorId(profesorId).stream()
                .map(reporteMapper::toResponse).toList();
    }

    @Override
    @Transactional(readOnly = true)
    public List<ReporteResponseDTO> obtenerPorCarreraYAnio(Integer carreraId, Integer anio) {
        return reporteRepository.findByCarreraIdAndAnio(carreraId, anio).stream()
                .map(reporteMapper::toResponse).toList();
    }

    @Override
    @Transactional(readOnly = true)
    public List<ReporteResponseDTO> obtenerPendientes() {
        return reporteRepository.findByEstado(EstadoReporte.PENDIENTE_VALIDACION).stream()
                .map(reporteMapper::toResponse).toList();
    }

    private Reporte findOrThrow(Integer id) {
        return reporteRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Reporte no encontrado con id: " + id));
    }

    @Override
    @Transactional
    public ReporteResponseDTO toggleSeccion(Integer id, Integer numSeccion) {
        Reporte reporte = findOrThrow(id);

        if (reporte.getEstado() == EstadoReporte.ACEPTADO)
            throw new BusinessException("No se puede modificar un reporte aceptado.");

        /*switch (numSeccion) {
            case 1 -> reporte.setSeccion1Concluida(!Boolean.TRUE.equals(reporte.getSeccion1Concluida()));
            case 2 -> reporte.setSeccion2Concluida(!Boolean.TRUE.equals(reporte.getSeccion2Concluida()));
            case 3 -> reporte.setSeccion3Concluida(!Boolean.TRUE.equals(reporte.getSeccion3Concluida()));
            case 4 -> reporte.setSeccion4Concluida(!Boolean.TRUE.equals(reporte.getSeccion4Concluida()));
            case 5 -> reporte.setSeccion5Concluida(!Boolean.TRUE.equals(reporte.getSeccion5Concluida()));
            case 6 -> reporte.setSeccion6Concluida(!Boolean.TRUE.equals(reporte.getSeccion6Concluida()));
            case 7 -> reporte.setSeccion7Concluida(!Boolean.TRUE.equals(reporte.getSeccion7Concluida()));
            default -> throw new BusinessException("Número de sección inválido: " + numSeccion);
        }*/

        switch (numSeccion) {
            case 1 -> reporte.setSeccion1Concluida(!reporte.isSeccion1Concluida());
            case 2 -> reporte.setSeccion2Concluida(!reporte.isSeccion2Concluida());
            case 3 -> reporte.setSeccion3Concluida(!reporte.isSeccion3Concluida());
            case 4 -> reporte.setSeccion4Concluida(!reporte.isSeccion4Concluida());
            case 5 -> reporte.setSeccion5Concluida(!reporte.isSeccion5Concluida());
            case 6 -> reporte.setSeccion6Concluida(!reporte.isSeccion6Concluida());
            case 7 -> reporte.setSeccion7Concluida(!reporte.isSeccion7Concluida());
            default -> throw new BusinessException("Número de sección inválido: " + numSeccion);
        }

        return reporteMapper.toResponse(reporteRepository.save(reporte));
    }

    @Override
    @Transactional(readOnly = true)
    public List<ReporteResponseDTO> obtenerPorAnio(Integer anio) {
        return reporteRepository.findByAnio(anio).stream()
                .map(reporteMapper::toResponse).toList();
    }

    @Override
    @Transactional(readOnly = true)
    public List<ReporteResponseDTO> obtenerHistorialProfesor(Integer profesorId) {
        return reporteRepository
                .findByProfesorIdAndEstadoOrderByAnioDesc(profesorId, EstadoReporte.ACEPTADO)
                .stream()
                .map(reporteMapper::toResponse)
                .toList();
    }

}