package mx.edu.unpa.reporteanualbackend.dtos.usuario;

import lombok.*;
import mx.edu.unpa.reporteanualbackend.entities.enums.Rol;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class UsuarioResponseDTO {
    private Integer id;
    private String numeroTrabajo;
    private String nombre;
    private String apellidos;
    private String correo;
    private Rol rol;
    private Integer carreraId;
    private String carreraNombre;
    private Boolean activo;
}