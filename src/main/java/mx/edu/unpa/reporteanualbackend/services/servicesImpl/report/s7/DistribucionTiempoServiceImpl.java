package mx.edu.unpa.reporteanualbackend.services.servicesImpl.report.s7;

import lombok.RequiredArgsConstructor;
import mx.edu.unpa.reporteanualbackend.dtos.report.s7.DistribucionTiempoRequestDTO;
import mx.edu.unpa.reporteanualbackend.dtos.report.s7.DistribucionTiempoResponseDTO;
import mx.edu.unpa.reporteanualbackend.entities.report.Reporte;
import mx.edu.unpa.reporteanualbackend.entities.report.s7.DistribucionTiempo;
import mx.edu.unpa.reporteanualbackend.exceptions.ResourceNotFoundException;
import mx.edu.unpa.reporteanualbackend.mappers.report.s7.DistribucionTiempoMapper;
import mx.edu.unpa.reporteanualbackend.repositories.report.ReporteRepository;
import mx.edu.unpa.reporteanualbackend.repositories.report.s7.DistribucionTiempoRepository;
import mx.edu.unpa.reporteanualbackend.services.report.s7.DistribucionTiempoService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.math.BigDecimal;
import java.util.List;

@Service
@RequiredArgsConstructor
public class DistribucionTiempoServiceImpl implements DistribucionTiempoService {

    private final DistribucionTiempoRepository repository;
    private final ReporteRepository reporteRepository;
    private final DistribucionTiempoMapper mapper;

    @Override
    @Transactional
    public DistribucionTiempoResponseDTO crear(Integer reporteId, DistribucionTiempoRequestDTO dto) {
        Reporte reporte = reporteRepository.findById(reporteId)
                .orElseThrow(() -> new ResourceNotFoundException("Reporte no encontrado: " + reporteId));
        DistribucionTiempo entity = mapper.toEntity(dto);
        entity.setReporte(reporte);
        entity.setHorasCicloOi(dto.getHorasCicloOi() != null ? dto.getHorasCicloOi() : BigDecimal.ZERO);
        entity.setHorasCicloPv(dto.getHorasCicloPv() != null ? dto.getHorasCicloPv() : BigDecimal.ZERO);
        entity.setHorasVerano(dto.getHorasVerano() != null ? dto.getHorasVerano() : BigDecimal.ZERO);
        return mapper.toResponse(repository.save(entity));
    }

    @Override
    @Transactional(readOnly = true)
    public List<DistribucionTiempoResponseDTO> obtenerPorReporte(Integer reporteId) {
        return repository.findByReporteIdOrderByOrden(reporteId).stream()
                .map(mapper::toResponse).toList();
    }

    @Override
    @Transactional
    public DistribucionTiempoResponseDTO actualizar(Integer id, DistribucionTiempoRequestDTO dto) {
        DistribucionTiempo entity = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Distribución de tiempo no encontrada: " + id));
        entity.setActividadAcademica(dto.getActividadAcademica());
        entity.setOrden(dto.getOrden());
        entity.setHorasCicloOi(dto.getHorasCicloOi() != null ? dto.getHorasCicloOi() : BigDecimal.ZERO);
        entity.setHorasCicloPv(dto.getHorasCicloPv() != null ? dto.getHorasCicloPv() : BigDecimal.ZERO);
        entity.setHorasVerano(dto.getHorasVerano() != null ? dto.getHorasVerano() : BigDecimal.ZERO);
        return mapper.toResponse(repository.save(entity));
    }

    @Override
    @Transactional
    public void eliminar(Integer id) {
        if (!repository.existsById(id))
            throw new ResourceNotFoundException("Distribución de tiempo no encontrada: " + id);
        repository.deleteById(id);
    }
}