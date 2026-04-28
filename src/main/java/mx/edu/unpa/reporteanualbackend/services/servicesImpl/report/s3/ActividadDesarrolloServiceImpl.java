package mx.edu.unpa.reporteanualbackend.services.servicesImpl.report.s3;

import lombok.RequiredArgsConstructor;
import mx.edu.unpa.reporteanualbackend.dtos.report.s3.ActividadDesarrolloRequestDTO;
import mx.edu.unpa.reporteanualbackend.dtos.report.s3.ActividadDesarrolloResponseDTO;
import mx.edu.unpa.reporteanualbackend.entities.report.Reporte;
import mx.edu.unpa.reporteanualbackend.entities.report.s3.ActividadDesarrollo;
import mx.edu.unpa.reporteanualbackend.exceptions.ResourceNotFoundException;
import mx.edu.unpa.reporteanualbackend.mappers.report.s3.ActividadDesarrolloMapper;
import mx.edu.unpa.reporteanualbackend.repositories.report.ReporteRepository;
import mx.edu.unpa.reporteanualbackend.repositories.report.s3.ActividadDesarrolloRepository;
import mx.edu.unpa.reporteanualbackend.services.report.s3.ActividadDesarrolloService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ActividadDesarrolloServiceImpl implements ActividadDesarrolloService {

    private final ActividadDesarrolloRepository repository;
    private final ReporteRepository reporteRepository;
    private final ActividadDesarrolloMapper mapper;

    @Override
    @Transactional
    public ActividadDesarrolloResponseDTO crear(Integer reporteId, ActividadDesarrolloRequestDTO dto) {
        Reporte reporte = reporteRepository.findById(reporteId)
                .orElseThrow(() -> new ResourceNotFoundException("Reporte no encontrado: " + reporteId));
        ActividadDesarrollo entity = mapper.toEntity(dto);
        entity.setReporte(reporte);
        return mapper.toResponse(repository.save(entity));
    }

    @Override
    @Transactional(readOnly = true)
    public List<ActividadDesarrolloResponseDTO> obtenerPorReporte(Integer reporteId) {
        return repository.findByReporteId(reporteId).stream().map(mapper::toResponse).toList();
    }

    @Override
    @Transactional
    public ActividadDesarrolloResponseDTO actualizar(Integer id, ActividadDesarrolloRequestDTO dto) {
        ActividadDesarrollo entity = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Actividad de desarrollo no encontrada: " + id));
        entity.setNumActividad(dto.getNumActividad());
        entity.setActividad(dto.getActividad());
        entity.setInstitucionSolicitante(dto.getInstitucionSolicitante());
        entity.setHorasRequeridas(dto.getHorasRequeridas());
        entity.setProducto(dto.getProducto());
        return mapper.toResponse(repository.save(entity));
    }

    @Override
    @Transactional
    public void eliminar(Integer id) {
        if (!repository.existsById(id))
            throw new ResourceNotFoundException("Actividad de desarrollo no encontrada: " + id);
        repository.deleteById(id);
    }
}