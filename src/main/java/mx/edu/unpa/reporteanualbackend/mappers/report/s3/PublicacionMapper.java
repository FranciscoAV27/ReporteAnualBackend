package mx.edu.unpa.reporteanualbackend.mappers.report.s3;

import mx.edu.unpa.reporteanualbackend.dtos.report.s3.PublicacionRequestDTO;
import mx.edu.unpa.reporteanualbackend.dtos.report.s3.PublicacionResponseDTO;
import mx.edu.unpa.reporteanualbackend.entities.report.s3.Publicacion;
import org.mapstruct.*;

@Mapper(componentModel = "spring")
public interface PublicacionMapper {
    @Mapping(target = "reporte", ignore = true)
    Publicacion toEntity(PublicacionRequestDTO dto);

    @Mapping(source = "reporte.id", target = "reporteId")
    PublicacionResponseDTO toResponse(Publicacion entity);
}