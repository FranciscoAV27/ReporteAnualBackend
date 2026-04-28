package mx.edu.unpa.reporteanualbackend.mappers.report.s3;

import mx.edu.unpa.reporteanualbackend.dtos.report.s3.ProyectoInvestigacionRequestDTO;
import mx.edu.unpa.reporteanualbackend.dtos.report.s3.ProyectoInvestigacionResponseDTO;
import mx.edu.unpa.reporteanualbackend.entities.report.s3.ProyectoInvestigacion;
import org.mapstruct.*;

@Mapper(componentModel = "spring")
public interface ProyectoInvestigacionMapper {
    @Mapping(target = "reporte", ignore = true)
    @Mapping(target = "indicadores", ignore = true)
    ProyectoInvestigacion toEntity(ProyectoInvestigacionRequestDTO dto);

    @Mapping(source = "reporte.id", target = "reporteId")
    ProyectoInvestigacionResponseDTO toResponse(ProyectoInvestigacion entity);
}