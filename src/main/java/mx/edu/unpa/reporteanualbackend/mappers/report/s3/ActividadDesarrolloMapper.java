package mx.edu.unpa.reporteanualbackend.mappers.report.s3;

import mx.edu.unpa.reporteanualbackend.dtos.report.s3.ActividadDesarrolloRequestDTO;
import mx.edu.unpa.reporteanualbackend.dtos.report.s3.ActividadDesarrolloResponseDTO;
import mx.edu.unpa.reporteanualbackend.entities.report.s3.ActividadDesarrollo;
import org.mapstruct.*;

@Mapper(componentModel = "spring")
public interface ActividadDesarrolloMapper {
    @Mapping(target = "reporte", ignore = true)
    ActividadDesarrollo toEntity(ActividadDesarrolloRequestDTO dto);

    @Mapping(source = "reporte.id", target = "reporteId")
    ActividadDesarrolloResponseDTO toResponse(ActividadDesarrollo entity);
}