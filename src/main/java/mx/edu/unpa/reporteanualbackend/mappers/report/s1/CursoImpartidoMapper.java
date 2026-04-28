package mx.edu.unpa.reporteanualbackend.mappers.report.s1;

import mx.edu.unpa.reporteanualbackend.dtos.report.s1.CursoImpartidoRequestDTO;
import mx.edu.unpa.reporteanualbackend.dtos.report.s1.CursoImpartidoResponseDTO;
import mx.edu.unpa.reporteanualbackend.entities.report.s1.CursoImpartido;
import org.mapstruct.*;

@Mapper(componentModel = "spring")
public interface CursoImpartidoMapper {
    @Mapping(target = "reporte", ignore = true)
    CursoImpartido toEntity(CursoImpartidoRequestDTO dto);

    @Mapping(source = "reporte.id", target = "reporteId")
    CursoImpartidoResponseDTO toResponse(CursoImpartido entity);
}