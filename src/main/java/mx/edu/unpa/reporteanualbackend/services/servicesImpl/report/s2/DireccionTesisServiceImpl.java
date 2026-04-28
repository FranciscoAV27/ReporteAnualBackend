package mx.edu.unpa.reporteanualbackend.services.servicesImpl.report.s2;

import lombok.RequiredArgsConstructor;
import mx.edu.unpa.reporteanualbackend.dtos.report.s2.DireccionTesisRequestDTO;
import mx.edu.unpa.reporteanualbackend.dtos.report.s2.DireccionTesisResponseDTO;
import mx.edu.unpa.reporteanualbackend.entities.report.Reporte;
import mx.edu.unpa.reporteanualbackend.entities.report.s2.DireccionTesis;
import mx.edu.unpa.reporteanualbackend.exceptions.ResourceNotFoundException;
import mx.edu.unpa.reporteanualbackend.mappers.report.s2.DireccionTesisMapper;
import mx.edu.unpa.reporteanualbackend.repositories.report.ReporteRepository;
import mx.edu.unpa.reporteanualbackend.repositories.report.s2.DireccionTesisRepository;
import mx.edu.unpa.reporteanualbackend.services.report.s2.DireccionTesisService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
@RequiredArgsConstructor
public class DireccionTesisServiceImpl implements DireccionTesisService {

    private final DireccionTesisRepository repository;
    private final ReporteRepository reporteRepository;
    private final DireccionTesisMapper mapper;

    @Override
    @Transactional
    public DireccionTesisResponseDTO crear(Integer reporteId, DireccionTesisRequestDTO dto) {
        Reporte reporte = reporteRepository.findById(reporteId)
                .orElseThrow(() -> new ResourceNotFoundException("Reporte no encontrado: " + reporteId));
        DireccionTesis entity = mapper.toEntity(dto);
        entity.setReporte(reporte);
        return mapper.toResponse(repository.save(entity));
    }

    @Override
    @Transactional(readOnly = true)
    public List<DireccionTesisResponseDTO> obtenerPorReporte(Integer reporteId) {
        return repository.findByReporteId(reporteId).stream().map(mapper::toResponse).toList();
    }

    @Override
    @Transactional
    public DireccionTesisResponseDTO actualizar(Integer id, DireccionTesisRequestDTO dto) {
        DireccionTesis entity = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Dirección de tesis no encontrada: " + id));
        entity.setTitulo(dto.getTitulo());
        entity.setNombreAlumno(dto.getNombreAlumno());
        entity.setGrado(dto.getGrado());
        entity.setAvancePorcentaje(dto.getAvancePorcentaje());
        entity.setFechaRegistro(dto.getFechaRegistro());
        entity.setFechaProgTerm(dto.getFechaProgTerm());
        entity.setFechaReprogTerm(dto.getFechaReprogTerm());
        return mapper.toResponse(repository.save(entity));
    }

    @Override
    @Transactional
    public void eliminar(Integer id) {
        if (!repository.existsById(id))
            throw new ResourceNotFoundException("Dirección de tesis no encontrada: " + id);
        repository.deleteById(id);
    }
}