package mx.edu.unpa.reporteanualbackend.services.servicesImpl.report.s3;

import lombok.RequiredArgsConstructor;
import mx.edu.unpa.reporteanualbackend.dtos.report.s3.PublicacionRequestDTO;
import mx.edu.unpa.reporteanualbackend.dtos.report.s3.PublicacionResponseDTO;
import mx.edu.unpa.reporteanualbackend.entities.report.Reporte;
import mx.edu.unpa.reporteanualbackend.entities.report.s3.Publicacion;
import mx.edu.unpa.reporteanualbackend.exceptions.ResourceNotFoundException;
import mx.edu.unpa.reporteanualbackend.mappers.report.s3.PublicacionMapper;
import mx.edu.unpa.reporteanualbackend.repositories.report.ReporteRepository;
import mx.edu.unpa.reporteanualbackend.repositories.report.s3.PublicacionRepository;
import mx.edu.unpa.reporteanualbackend.services.report.s3.PublicacionService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PublicacionServiceImpl implements PublicacionService {

    private final PublicacionRepository repository;
    private final ReporteRepository reporteRepository;
    private final PublicacionMapper mapper;

    @Override
    @Transactional
    public PublicacionResponseDTO crear(Integer reporteId, PublicacionRequestDTO dto) {
        Reporte reporte = reporteRepository.findById(reporteId)
                .orElseThrow(() -> new ResourceNotFoundException("Reporte no encontrado: " + reporteId));
        Publicacion entity = mapper.toEntity(dto);
        entity.setReporte(reporte);
        return mapper.toResponse(repository.save(entity));
    }

    @Override
    @Transactional(readOnly = true)
    public List<PublicacionResponseDTO> obtenerPorReporte(Integer reporteId) {
        return repository.findByReporteId(reporteId).stream().map(mapper::toResponse).toList();
    }

    @Override
    @Transactional
    public PublicacionResponseDTO actualizar(Integer id, PublicacionRequestDTO dto) {
        Publicacion entity = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Publicación no encontrada: " + id));
        entity.setNumPublicacion(dto.getNumPublicacion());
        entity.setTitulo(dto.getTitulo());
        entity.setRevista(dto.getRevista());
        entity.setFase(dto.getFase());
        entity.setAnio(dto.getAnio());
        return mapper.toResponse(repository.save(entity));
    }

    @Override
    @Transactional
    public void eliminar(Integer id) {
        if (!repository.existsById(id))
            throw new ResourceNotFoundException("Publicación no encontrada: " + id);
        repository.deleteById(id);
    }
}