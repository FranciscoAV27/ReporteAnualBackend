package mx.edu.unpa.reporteanualbackend.mappers.report.s1;

import mx.edu.unpa.reporteanualbackend.dtos.report.s1.AsignaturaAfinidadRequestDTO;
import mx.edu.unpa.reporteanualbackend.dtos.report.s1.AsignaturaAfinidadResponseDTO;
import mx.edu.unpa.reporteanualbackend.entities.report.s1.AsignaturaAfinidad;
import org.mapstruct.*;

@Mapper(componentModel = "spring")
public interface AsignaturaAfinidadMapper {
    @Mapping(target = "reporte", ignore = true)
    AsignaturaAfinidad toEntity(AsignaturaAfinidadRequestDTO dto);

    @Mapping(source = "reporte.id", target = "reporteId")
    AsignaturaAfinidadResponseDTO toResponse(AsignaturaAfinidad entity);
}