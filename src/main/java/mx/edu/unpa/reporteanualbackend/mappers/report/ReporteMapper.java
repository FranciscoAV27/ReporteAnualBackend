package mx.edu.unpa.reporteanualbackend.mappers.report;

import mx.edu.unpa.reporteanualbackend.dtos.report.ReporteRequestDTO;
import mx.edu.unpa.reporteanualbackend.dtos.report.ReporteResponseDTO;
import mx.edu.unpa.reporteanualbackend.entities.report.Reporte;
import org.mapstruct.*;

@Mapper(componentModel = "spring")
public interface ReporteMapper {
    @Mapping(target = "profesor", ignore = true)
    @Mapping(target = "estado", ignore = true)
    @Mapping(target = "comentariosAdmin", ignore = true)
    Reporte toEntity(ReporteRequestDTO dto);

    @Mapping(source = "profesor.id", target = "profesorId")
    @Mapping(source = "profesor.nombre", target = "profesorNombre")
    @Mapping(source = "profesor.apellidos", target = "profesorApellidos") // ← nuevo
    @Mapping(source = "profesor.carrera.nombre", target = "profesorCarrera")

    @Mapping(source = "rechazadoPor.nombre",      target = "rechazadoPorNombre")
    @Mapping(source = "rechazadoPor.apellidos",   target = "rechazadoPorApellidos")
    @Mapping(source = "aprobadoPor.nombre",       target = "aprobadoPorNombre")
    @Mapping(source = "aprobadoPor.apellidos",    target = "aprobadoPorApellidos")

    ReporteResponseDTO toResponse(Reporte entity);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateFromDto(ReporteRequestDTO dto, @MappingTarget Reporte entity);
}