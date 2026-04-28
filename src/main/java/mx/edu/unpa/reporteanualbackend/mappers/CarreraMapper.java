package mx.edu.unpa.reporteanualbackend.mappers;

import mx.edu.unpa.reporteanualbackend.dtos.carrera.CarreraRequestDTO;
import mx.edu.unpa.reporteanualbackend.dtos.carrera.CarreraResponseDTO;
import mx.edu.unpa.reporteanualbackend.entities.Carrera;
import org.mapstruct.*;

@Mapper(componentModel = "spring")
public interface CarreraMapper {
    Carrera toEntity(CarreraRequestDTO dto);
    CarreraResponseDTO toResponse(Carrera entity);
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateFromDto(CarreraRequestDTO dto, @MappingTarget Carrera entity);
}