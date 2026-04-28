package mx.edu.unpa.reporteanualbackend.services.servicesImpl.report.s4;

import lombok.RequiredArgsConstructor;
import mx.edu.unpa.reporteanualbackend.dtos.report.s4.ActividadGestionRequestDTO;
import mx.edu.unpa.reporteanualbackend.dtos.report.s4.ActividadGestionResponseDTO;
import mx.edu.unpa.reporteanualbackend.entities.report.Reporte;
import mx.edu.unpa.reporteanualbackend.entities.report.s4.ActividadGestion;
import mx.edu.unpa.reporteanualbackend.exceptions.ResourceNotFoundException;
import mx.edu.unpa.reporteanualbackend.mappers.report.s4.ActividadGestionMapper;
import mx.edu.unpa.reporteanualbackend.repositories.report.ReporteRepository;
import mx.edu.unpa.reporteanualbackend.repositories.report.s4.ActividadGestionRepository;
import mx.edu.unpa.reporteanualbackend.services.report.s4.ActividadGestionService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ActividadGestionServiceImpl implements ActividadGestionService {

    private final ActividadGestionRepository repository;
    private final ReporteRepository reporteRepository;
    private final ActividadGestionMapper mapper;

    @Override
    @Transactional
    public ActividadGestionResponseDTO crear(Integer reporteId, ActividadGestionRequestDTO dto) {
        Reporte reporte = reporteRepository.findById(reporteId)
                .orElseThrow(() -> new ResourceNotFoundException("Reporte no encontrado: " + reporteId));
        ActividadGestion entity = mapper.toEntity(dto);
        entity.setReporte(reporte);
        return mapper.toResponse(repository.save(entity));
    }

    @Override
    @Transactional(readOnly = true)
    public List<ActividadGestionResponseDTO> obtenerPorReporte(Integer reporteId) {
        return repository.findByReporteId(reporteId).stream().map(mapper::toResponse).toList();
    }

    @Override
    @Transactional
    public ActividadGestionResponseDTO actualizar(Integer id, ActividadGestionRequestDTO dto) {
        ActividadGestion entity = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Actividad de gestión no encontrada: " + id));
        entity.setNumActividad(dto.getNumActividad());
        entity.setNombre(dto.getNombre());
        entity.setComisionOPuesto(dto.getComisionOPuesto());
        entity.setPeriodoInicio(dto.getPeriodoInicio());
        entity.setPeriodoFin(dto.getPeriodoFin());
        return mapper.toResponse(repository.save(entity));
    }

    @Override
    @Transactional
    public void eliminar(Integer id) {
        if (!repository.existsById(id))
            throw new ResourceNotFoundException("Actividad de gestión no encontrada: " + id);
        repository.deleteById(id);
    }
}
