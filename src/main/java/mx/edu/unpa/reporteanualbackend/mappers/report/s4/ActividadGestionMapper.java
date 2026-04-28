package mx.edu.unpa.reporteanualbackend.mappers.report.s4;

import mx.edu.unpa.reporteanualbackend.dtos.report.s4.ActividadGestionRequestDTO;
import mx.edu.unpa.reporteanualbackend.dtos.report.s4.ActividadGestionResponseDTO;
import mx.edu.unpa.reporteanualbackend.entities.report.s4.ActividadGestion;
import org.mapstruct.*;

@Mapper(componentModel = "spring")
public interface ActividadGestionMapper {
    @Mapping(target = "reporte", ignore = true)
    ActividadGestion toEntity(ActividadGestionRequestDTO dto);

    @Mapping(source = "reporte.id", target = "reporteId")
    ActividadGestionResponseDTO toResponse(ActividadGestion entity);
}