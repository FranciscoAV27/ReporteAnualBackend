package mx.edu.unpa.reporteanualbackend.mappers.report.s2;

import mx.edu.unpa.reporteanualbackend.dtos.report.s2.DireccionTesisRequestDTO;
import mx.edu.unpa.reporteanualbackend.dtos.report.s2.DireccionTesisResponseDTO;
import mx.edu.unpa.reporteanualbackend.entities.report.s2.DireccionTesis;
import org.mapstruct.*;

@Mapper(componentModel = "spring")
public interface DireccionTesisMapper {
    @Mapping(target = "reporte", ignore = true)
    DireccionTesis toEntity(DireccionTesisRequestDTO dto);

    @Mapping(source = "reporte.id", target = "reporteId")
    DireccionTesisResponseDTO toResponse(DireccionTesis entity);
}