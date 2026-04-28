package mx.edu.unpa.reporteanualbackend.mappers.report.s2;

import mx.edu.unpa.reporteanualbackend.dtos.report.s2.TutoriaRequestDTO;
import mx.edu.unpa.reporteanualbackend.dtos.report.s2.TutoriaResponseDTO;
import mx.edu.unpa.reporteanualbackend.entities.report.s2.Tutoria;
import org.mapstruct.*;

@Mapper(componentModel = "spring")
public interface TutoriaMapper {
    @Mapping(target = "reporte", ignore = true)
    Tutoria toEntity(TutoriaRequestDTO dto);

    @Mapping(source = "reporte.id", target = "reporteId")
    TutoriaResponseDTO toResponse(Tutoria entity);
}