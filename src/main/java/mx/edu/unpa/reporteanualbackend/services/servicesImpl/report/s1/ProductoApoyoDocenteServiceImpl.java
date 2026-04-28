package mx.edu.unpa.reporteanualbackend.services.servicesImpl.report.s1;

import lombok.RequiredArgsConstructor;
import mx.edu.unpa.reporteanualbackend.dtos.report.s1.ProductoApoyoDocenteRequestDTO;
import mx.edu.unpa.reporteanualbackend.dtos.report.s1.ProductoApoyoDocenteResponseDTO;
import mx.edu.unpa.reporteanualbackend.entities.report.Reporte;
import mx.edu.unpa.reporteanualbackend.entities.report.s1.ProductoApoyoDocente;
import mx.edu.unpa.reporteanualbackend.exceptions.ResourceNotFoundException;
import mx.edu.unpa.reporteanualbackend.mappers.report.s1.ProductoApoyoDocenteMapper;
import mx.edu.unpa.reporteanualbackend.repositories.report.ReporteRepository;
import mx.edu.unpa.reporteanualbackend.repositories.report.s1.ProductoApoyoDocenteRepository;
import mx.edu.unpa.reporteanualbackend.services.report.s1.ProductoApoyoDocenteService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductoApoyoDocenteServiceImpl implements ProductoApoyoDocenteService {

    private final ProductoApoyoDocenteRepository repository;
    private final ReporteRepository reporteRepository;
    private final ProductoApoyoDocenteMapper mapper;

    @Override
    @Transactional
    public ProductoApoyoDocenteResponseDTO crear(Integer reporteId, ProductoApoyoDocenteRequestDTO dto) {
        Reporte reporte = reporteRepository.findById(reporteId)
                .orElseThrow(() -> new ResourceNotFoundException("Reporte no encontrado: " + reporteId));
        ProductoApoyoDocente entity = mapper.toEntity(dto);
        entity.setReporte(reporte);
        return mapper.toResponse(repository.save(entity));
    }

    @Override
    @Transactional(readOnly = true)
    public List<ProductoApoyoDocenteResponseDTO> obtenerPorReporte(Integer reporteId) {
        return repository.findByReporteId(reporteId).stream().map(mapper::toResponse).toList();
    }

    @Override
    @Transactional
    public ProductoApoyoDocenteResponseDTO actualizar(Integer id, ProductoApoyoDocenteRequestDTO dto) {
        ProductoApoyoDocente entity = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Producto no encontrado: " + id));
        entity.setNumeroCurso(dto.getNumeroCurso());
        entity.setDescripcion(dto.getDescripcion());
        entity.setEnlace(dto.getEnlace());
        return mapper.toResponse(repository.save(entity));
    }

    @Override
    @Transactional
    public void eliminar(Integer id) {
        if (!repository.existsById(id))
            throw new ResourceNotFoundException("Producto no encontrado: " + id);
        repository.deleteById(id);
    }
}