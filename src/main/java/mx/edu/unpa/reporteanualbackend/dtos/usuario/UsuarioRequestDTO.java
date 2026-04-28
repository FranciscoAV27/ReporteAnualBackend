package mx.edu.unpa.reporteanualbackend.dtos.usuario;

import jakarta.validation.constraints.*;
import lombok.*;
import mx.edu.unpa.reporteanualbackend.entities.enums.Rol;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class UsuarioRequestDTO {

    @NotBlank @Size(max = 20)
    private String numeroTrabajo;

    @NotBlank @Size(max = 100)
    private String nombre;

    @NotBlank @Size(max = 150)
    private String apellidos;

    @NotBlank @Email @Size(max = 150)
    private String correo;

    @NotBlank @Size(min = 8, max = 100)
    private String contrasena;

    @NotNull
    private Rol rol;

    private Integer carreraId;
}