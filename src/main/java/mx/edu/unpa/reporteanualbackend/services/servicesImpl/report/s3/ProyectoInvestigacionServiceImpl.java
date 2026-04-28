package mx.edu.unpa.reporteanualbackend.services.servicesImpl.report.s3;

import lombok.RequiredArgsConstructor;
import mx.edu.unpa.reporteanualbackend.dtos.report.s3.ProyectoInvestigacionRequestDTO;
import mx.edu.unpa.reporteanualbackend.dtos.report.s3.ProyectoInvestigacionResponseDTO;
import mx.edu.unpa.reporteanualbackend.entities.report.Reporte;
import mx.edu.unpa.reporteanualbackend.entities.report.s3.ProyectoInvestigacion;
import mx.edu.unpa.reporteanualbackend.exceptions.ResourceNotFoundException;
import mx.edu.unpa.reporteanualbackend.mappers.report.s3.ProyectoInvestigacionMapper;
import mx.edu.unpa.reporteanualbackend.repositories.report.ReporteRepository;
import mx.edu.unpa.reporteanualbackend.repositories.report.s3.ProyectoInvestigacionRepository;
import mx.edu.unpa.reporteanualbackend.services.report.s3.ProyectoInvestigacionService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ProyectoInvestigacionServiceImpl implements ProyectoInvestigacionService {

    private final ProyectoInvestigacionRepository repository;
    private final ReporteRepository reporteRepository;
    private final ProyectoInvestigacionMapper mapper;

    @Override
    @Transactional
    public ProyectoInvestigacionResponseDTO crear(Integer reporteId, ProyectoInvestigacionRequestDTO dto) {
        Reporte reporte = reporteRepository.findById(reporteId)
                .orElseThrow(() -> new ResourceNotFoundException("Reporte no encontrado: " + reporteId));
        ProyectoInvestigacion entity = mapper.toEntity(dto);
        entity.setReporte(reporte);
        return mapper.toResponse(repository.save(entity));
    }

    @Override
    @Transactional(readOnly = true)
    public List<ProyectoInvestigacionResponseDTO> obtenerPorReporte(Integer reporteId) {
        return repository.findByReporteId(reporteId).stream().map(mapper::toResponse).toList();
    }

    @Override
    @Transactional
    public ProyectoInvestigacionResponseDTO actualizar(Integer id, ProyectoInvestigacionRequestDTO dto) {
        ProyectoInvestigacion entity = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Proyecto no encontrado: " + id));
        entity.setNumProyecto(dto.getNumProyecto());
        entity.setTitulo(dto.getTitulo());
        entity.setResponsabilidad(dto.getResponsabilidad());
        entity.setFaseAprobacion(dto.getFaseAprobacion());
        entity.setInstancia(dto.getInstancia());
        entity.setFechaInicio(dto.getFechaInicio());
        entity.setFechaTerminacion(dto.getFechaTerminacion());
        entity.setFechaReprog(dto.getFechaReprog());
        entity.setAvancePorcentaje(dto.getAvancePorcentaje());
        return mapper.toResponse(repository.save(entity));
    }

    @Override
    @Transactional
    public void eliminar(Integer id) {
        if (!repository.existsById(id))
            throw new ResourceNotFoundException("Proyecto no encontrado: " + id);
        repository.deleteById(id);
    }
}