package mx.edu.unpa.reporteanualbackend.services.servicesImpl.report.s2;

import lombok.RequiredArgsConstructor;
import mx.edu.unpa.reporteanualbackend.dtos.report.s2.TutoriaRequestDTO;
import mx.edu.unpa.reporteanualbackend.dtos.report.s2.TutoriaResponseDTO;
import mx.edu.unpa.reporteanualbackend.entities.report.Reporte;
import mx.edu.unpa.reporteanualbackend.entities.report.s2.Tutoria;
import mx.edu.unpa.reporteanualbackend.exceptions.ResourceNotFoundException;
import mx.edu.unpa.reporteanualbackend.mappers.report.s2.TutoriaMapper;
import mx.edu.unpa.reporteanualbackend.repositories.report.ReporteRepository;
import mx.edu.unpa.reporteanualbackend.repositories.report.s2.TutoriaRepository;
import mx.edu.unpa.reporteanualbackend.services.report.s2.TutoriaService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TutoriaServiceImpl implements TutoriaService {

    private final TutoriaRepository repository;
    private final ReporteRepository reporteRepository;
    private final TutoriaMapper mapper;

    @Override
    @Transactional
    public TutoriaResponseDTO crear(Integer reporteId, TutoriaRequestDTO dto) {
        Reporte reporte = reporteRepository.findById(reporteId)
                .orElseThrow(() -> new ResourceNotFoundException("Reporte no encontrado: " + reporteId));
        Tutoria entity = mapper.toEntity(dto);
        entity.setReporte(reporte);
        return mapper.toResponse(repository.save(entity));
    }

    @Override
    @Transactional(readOnly = true)
    public List<TutoriaResponseDTO> obtenerPorReporte(Integer reporteId) {
        return repository.findByReporteId(reporteId).stream().map(mapper::toResponse).toList();
    }

    @Override
    @Transactional
    public TutoriaResponseDTO actualizar(Integer id, TutoriaRequestDTO dto) {
        Tutoria entity = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Tutoría no encontrada: " + id));
        entity.setNombreAlumno(dto.getNombreAlumno());
        entity.setCarrera(dto.getCarrera());
        entity.setSemestre(dto.getSemestre());
        entity.setFechaRegistro(dto.getFechaRegistro());
        return mapper.toResponse(repository.save(entity));
    }

    @Override
    @Transactional
    public void eliminar(Integer id) {
        if (!repository.existsById(id))
            throw new ResourceNotFoundException("Tutoría no encontrada: " + id);
        repository.deleteById(id);
    }
}