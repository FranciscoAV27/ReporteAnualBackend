package mx.edu.unpa.reporteanualbackend.services.servicesImpl.report.s1;

import lombok.RequiredArgsConstructor;
import mx.edu.unpa.reporteanualbackend.dtos.report.s1.CursoImpartidoRequestDTO;
import mx.edu.unpa.reporteanualbackend.dtos.report.s1.CursoImpartidoResponseDTO;
import mx.edu.unpa.reporteanualbackend.entities.report.Reporte;
import mx.edu.unpa.reporteanualbackend.entities.report.s1.CursoImpartido;
import mx.edu.unpa.reporteanualbackend.exceptions.ResourceNotFoundException;
import mx.edu.unpa.reporteanualbackend.mappers.report.s1.CursoImpartidoMapper;
import mx.edu.unpa.reporteanualbackend.repositories.report.ReporteRepository;
import mx.edu.unpa.reporteanualbackend.repositories.report.s1.CursoImpartidoRepository;
import mx.edu.unpa.reporteanualbackend.services.report.s1.CursoImpartidoService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CursoImpartidoServiceImpl implements CursoImpartidoService {

    private final CursoImpartidoRepository repository;
    private final ReporteRepository reporteRepository;
    private final CursoImpartidoMapper mapper;

    @Override
    @Transactional
    public CursoImpartidoResponseDTO crear(Integer reporteId, CursoImpartidoRequestDTO dto) {
        Reporte reporte = reporteRepository.findById(reporteId)
                .orElseThrow(() -> new ResourceNotFoundException("Reporte no encontrado: " + reporteId));
        CursoImpartido entity = mapper.toEntity(dto);
        entity.setReporte(reporte);
        return mapper.toResponse(repository.save(entity));
    }

    @Override
    @Transactional(readOnly = true)
    public List<CursoImpartidoResponseDTO> obtenerPorReporte(Integer reporteId) {
        return repository.findByReporteId(reporteId).stream().map(mapper::toResponse).toList();
    }

    @Override
    @Transactional
    public CursoImpartidoResponseDTO actualizar(Integer id, CursoImpartidoRequestDTO dto) {
        CursoImpartido entity = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Curso no encontrado: " + id));
        entity.setNumeroCurso(dto.getNumeroCurso());
        entity.setCarrera(dto.getCarrera());
        entity.setAsignatura(dto.getAsignatura());
        entity.setSemestre(dto.getSemestre());
        entity.setCicloEscolar(dto.getCicloEscolar());
        entity.setHorasSemana(dto.getHorasSemana());
        entity.setNumAlumnos(dto.getNumAlumnos());
        return mapper.toResponse(repository.save(entity));
    }

    @Override
    @Transactional
    public void eliminar(Integer id) {
        if (!repository.existsById(id))
            throw new ResourceNotFoundException("Curso no encontrado: " + id);
        repository.deleteById(id);
    }
}