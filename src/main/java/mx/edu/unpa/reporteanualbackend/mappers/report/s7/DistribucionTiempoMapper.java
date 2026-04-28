package mx.edu.unpa.reporteanualbackend.mappers.report.s7;

import mx.edu.unpa.reporteanualbackend.dtos.report.s7.DistribucionTiempoRequestDTO;
import mx.edu.unpa.reporteanualbackend.dtos.report.s7.DistribucionTiempoResponseDTO;
import mx.edu.unpa.reporteanualbackend.entities.report.s7.DistribucionTiempo;
import org.mapstruct.*;

@Mapper(componentModel = "spring")
public interface DistribucionTiempoMapper {
    @Mapping(target = "reporte", ignore = true)
    DistribucionTiempo toEntity(DistribucionTiempoRequestDTO dto);

    @Mapping(source = "reporte.id", target = "reporteId")
    DistribucionTiempoResponseDTO toResponse(DistribucionTiempo entity);
}