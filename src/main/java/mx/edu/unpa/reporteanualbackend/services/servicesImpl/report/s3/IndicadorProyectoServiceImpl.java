package mx.edu.unpa.reporteanualbackend.services.servicesImpl.report.s3;

import lombok.RequiredArgsConstructor;
import mx.edu.unpa.reporteanualbackend.dtos.report.s3.IndicadorProyectoRequestDTO;
import mx.edu.unpa.reporteanualbackend.dtos.report.s3.IndicadorProyectoResponseDTO;
import mx.edu.unpa.reporteanualbackend.entities.report.s3.IndicadorProyecto;
import mx.edu.unpa.reporteanualbackend.entities.report.s3.ProyectoInvestigacion;
import mx.edu.unpa.reporteanualbackend.exceptions.ResourceNotFoundException;
import mx.edu.unpa.reporteanualbackend.mappers.report.s3.IndicadorProyectoMapper;
import mx.edu.unpa.reporteanualbackend.repositories.report.s3.IndicadorProyectoRepository;
import mx.edu.unpa.reporteanualbackend.repositories.report.s3.ProyectoInvestigacionRepository;
import mx.edu.unpa.reporteanualbackend.services.report.s3.IndicadorProyectoService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
@RequiredArgsConstructor
public class IndicadorProyectoServiceImpl implements IndicadorProyectoService {

    private final IndicadorProyectoRepository repository;
    private final ProyectoInvestigacionRepository proyectoRepository;
    private final IndicadorProyectoMapper mapper;

    @Override
    @Transactional
    public IndicadorProyectoResponseDTO crear(Integer proyectoId, IndicadorProyectoRequestDTO dto) {
        ProyectoInvestigacion proyecto = proyectoRepository.findById(proyectoId)
                .orElseThrow(() -> new ResourceNotFoundException("Proyecto no encontrado: " + proyectoId));
        IndicadorProyecto entity = mapper.toEntity(dto);
        entity.setProyecto(proyecto);
        return mapper.toResponse(repository.save(entity));
    }

    @Override
    @Transactional(readOnly = true)
    public List<IndicadorProyectoResponseDTO> obtenerPorProyecto(Integer proyectoId) {
        return repository.findByProyectoId(proyectoId).stream().map(mapper::toResponse).toList();
    }

    @Override
    @Transactional
    public IndicadorProyectoResponseDTO actualizar(Integer id, IndicadorProyectoRequestDTO dto) {
        IndicadorProyecto entity = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Indicador no encontrado: " + id));
        entity.setNumProyecto(dto.getNumProyecto());
        entity.setNumIndicador(dto.getNumIndicador());
        entity.setDescripcion(dto.getDescripcion());
        return mapper.toResponse(repository.save(entity));
    }

    @Override
    @Transactional
    public void eliminar(Integer id) {
        if (!repository.existsById(id))
            throw new ResourceNotFoundException("Indicador no encontrado: " + id);
        repository.deleteById(id);
    }
}