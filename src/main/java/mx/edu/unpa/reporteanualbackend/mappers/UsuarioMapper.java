package mx.edu.unpa.reporteanualbackend.mappers;

import mx.edu.unpa.reporteanualbackend.dtos.usuario.UsuarioRequestDTO;
import mx.edu.unpa.reporteanualbackend.dtos.usuario.UsuarioResponseDTO;
import mx.edu.unpa.reporteanualbackend.entities.Usuario;
import org.mapstruct.*;

@Mapper(componentModel = "spring")
public interface UsuarioMapper {
    @Mapping(target = "contrasenaHash", ignore = true)
    @Mapping(target = "carrera", ignore = true)
    @Mapping(target = "activo", ignore = true)
    @Mapping(target = "creadoEn", ignore = true)
    Usuario toEntity(UsuarioRequestDTO dto);

    @Mapping(source = "carrera.id", target = "carreraId")
    @Mapping(source = "carrera.nombre", target = "carreraNombre")
    UsuarioResponseDTO toResponse(Usuario entity);
}