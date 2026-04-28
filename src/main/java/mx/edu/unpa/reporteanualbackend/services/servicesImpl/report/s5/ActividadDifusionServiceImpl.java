package mx.edu.unpa.reporteanualbackend.services.servicesImpl.report.s5;

import lombok.RequiredArgsConstructor;
import mx.edu.unpa.reporteanualbackend.dtos.report.s5.ActividadDifusionRequestDTO;
import mx.edu.unpa.reporteanualbackend.dtos.report.s5.ActividadDifusionResponseDTO;
import mx.edu.unpa.reporteanualbackend.entities.report.Reporte;
import mx.edu.unpa.reporteanualbackend.entities.report.s5.ActividadDifusion;
import mx.edu.unpa.reporteanualbackend.exceptions.ResourceNotFoundException;
import mx.edu.unpa.reporteanualbackend.mappers.report.s5.ActividadDifusionMapper;
import mx.edu.unpa.reporteanualbackend.repositories.report.ReporteRepository;
import mx.edu.unpa.reporteanualbackend.repositories.report.s5.ActividadDifusionRepository;
import mx.edu.unpa.reporteanualbackend.services.report.s5.ActividadDifusionService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ActividadDifusionServiceImpl implements ActividadDifusionService {

    private final ActividadDifusionRepository repository;
    private final ReporteRepository reporteRepository;
    private final ActividadDifusionMapper mapper;

    @Override
    @Transactional
    public ActividadDifusionResponseDTO crear(Integer reporteId, ActividadDifusionRequestDTO dto) {
        Reporte reporte = reporteRepository.findById(reporteId)
                .orElseThrow(() -> new ResourceNotFoundException("Reporte no encontrado: " + reporteId));
        ActividadDifusion entity = mapper.toEntity(dto);
        entity.setReporte(reporte);
        return mapper.toResponse(repository.save(entity));
    }

    @Override
    @Transactional(readOnly = true)
    public List<ActividadDifusionResponseDTO> obtenerPorReporte(Integer reporteId) {
        return repository.findByReporteId(reporteId).stream().map(mapper::toResponse).toList();
    }

    @Override
    @Transactional
    public ActividadDifusionResponseDTO actualizar(Integer id, ActividadDifusionRequestDTO dto) {
        ActividadDifusion entity = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Actividad de difusión no encontrada: " + id));
        entity.setNumActividad(dto.getNumActividad());
        entity.setNombre(dto.getNombre());
        entity.setPeriodoInicio(dto.getPeriodoInicio());
        entity.setPeriodoFin(dto.getPeriodoFin());
        return mapper.toResponse(repository.save(entity));
    }

    @Override
    @Transactional
    public void eliminar(Integer id) {
        if (!repository.existsById(id))
            throw new ResourceNotFoundException("Actividad de difusión no encontrada: " + id);
        repository.deleteById(id);
    }
}