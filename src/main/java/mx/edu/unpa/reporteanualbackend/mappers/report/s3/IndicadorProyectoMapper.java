package mx.edu.unpa.reporteanualbackend.mappers.report.s3;

import mx.edu.unpa.reporteanualbackend.dtos.report.s3.IndicadorProyectoRequestDTO;
import mx.edu.unpa.reporteanualbackend.dtos.report.s3.IndicadorProyectoResponseDTO;
import mx.edu.unpa.reporteanualbackend.entities.report.s3.IndicadorProyecto;
import org.mapstruct.*;

@Mapper(componentModel = "spring")
public interface IndicadorProyectoMapper {
    @Mapping(target = "proyecto", ignore = true)
    IndicadorProyecto toEntity(IndicadorProyectoRequestDTO dto);

    @Mapping(source = "proyecto.id", target = "proyectoId")
    IndicadorProyectoResponseDTO toResponse(IndicadorProyecto entity);
}