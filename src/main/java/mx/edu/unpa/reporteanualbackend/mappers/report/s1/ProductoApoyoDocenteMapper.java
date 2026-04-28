package mx.edu.unpa.reporteanualbackend.mappers.report.s1;

import mx.edu.unpa.reporteanualbackend.dtos.report.s1.ProductoApoyoDocenteRequestDTO;
import mx.edu.unpa.reporteanualbackend.dtos.report.s1.ProductoApoyoDocenteResponseDTO;
import mx.edu.unpa.reporteanualbackend.entities.report.s1.ProductoApoyoDocente;
import org.mapstruct.*;

@Mapper(componentModel = "spring")
public interface ProductoApoyoDocenteMapper {
    @Mapping(target = "reporte", ignore = true)
    ProductoApoyoDocente toEntity(ProductoApoyoDocenteRequestDTO dto);

    @Mapping(source = "reporte.id", target = "reporteId")
    ProductoApoyoDocenteResponseDTO toResponse(ProductoApoyoDocente entity);
}