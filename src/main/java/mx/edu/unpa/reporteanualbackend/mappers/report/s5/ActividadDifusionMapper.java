package mx.edu.unpa.reporteanualbackend.mappers.report.s5;

import mx.edu.unpa.reporteanualbackend.dtos.report.s5.ActividadDifusionRequestDTO;
import mx.edu.unpa.reporteanualbackend.dtos.report.s5.ActividadDifusionResponseDTO;
import mx.edu.unpa.reporteanualbackend.entities.report.s5.ActividadDifusion;
import org.mapstruct.*;

@Mapper(componentModel = "spring")
public interface ActividadDifusionMapper {
    @Mapping(target = "reporte", ignore = true)
    ActividadDifusion toEntity(ActividadDifusionRequestDTO dto);

    @Mapping(source = "reporte.id", target = "reporteId")
    ActividadDifusionResponseDTO toResponse(ActividadDifusion entity);
}