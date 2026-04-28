package mx.edu.unpa.reporteanualbackend.services.servicesImpl.report.s1;

import lombok.RequiredArgsConstructor;
import mx.edu.unpa.reporteanualbackend.dtos.report.s1.AsignaturaAfinidadRequestDTO;
import mx.edu.unpa.reporteanualbackend.dtos.report.s1.AsignaturaAfinidadResponseDTO;
import mx.edu.unpa.reporteanualbackend.entities.report.Reporte;
import mx.edu.unpa.reporteanualbackend.entities.report.s1.AsignaturaAfinidad;
import mx.edu.unpa.reporteanualbackend.exceptions.ResourceNotFoundException;
import mx.edu.unpa.reporteanualbackend.mappers.report.s1.AsignaturaAfinidadMapper;
import mx.edu.unpa.reporteanualbackend.repositories.report.ReporteRepository;
import mx.edu.unpa.reporteanualbackend.repositories.report.s1.AsignaturaAfinidadRepository;
import mx.edu.unpa.reporteanualbackend.services.report.s1.AsignaturaAfinidadService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AsignaturaAfinidadServiceImpl implements AsignaturaAfinidadService {

    private final AsignaturaAfinidadRepository repository;
    private final ReporteRepository reporteRepository;
    private final AsignaturaAfinidadMapper mapper;

    @Override
    @Transactional
    public AsignaturaAfinidadResponseDTO crear(Integer reporteId, AsignaturaAfinidadRequestDTO dto) {
        Reporte reporte = reporteRepository.findById(reporteId)
                .orElseThrow(() -> new ResourceNotFoundException("Reporte no encontrado: " + reporteId));
        AsignaturaAfinidad entity = mapper.toEntity(dto);
        entity.setReporte(reporte);
        return mapper.toResponse(repository.save(entity));
    }

    @Override
    @Transactional(readOnly = true)
    public List<AsignaturaAfinidadResponseDTO> obtenerPorReporte(Integer reporteId) {
        return repository.findByReporteId(reporteId).stream().map(mapper::toResponse).toList();
    }

    @Override
    @Transactional
    public AsignaturaAfinidadResponseDTO actualizar(Integer id, AsignaturaAfinidadRequestDTO dto) {
        AsignaturaAfinidad entity = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Asignatura no encontrada: " + id));
        entity.setNumAsignatura(dto.getNumAsignatura());
        entity.setCarrera(dto.getCarrera());
        entity.setAsignatura(dto.getAsignatura());
        entity.setSemestre(dto.getSemestre());
        return mapper.toResponse(repository.save(entity));
    }

    @Override
    @Transactional
    public void eliminar(Integer id) {
        if (!repository.existsById(id))
            throw new ResourceNotFoundException("Asignatura no encontrada: " + id);
        repository.deleteById(id);
    }
}