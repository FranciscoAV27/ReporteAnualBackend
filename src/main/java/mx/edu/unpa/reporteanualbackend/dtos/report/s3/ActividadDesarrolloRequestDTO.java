package mx.edu.unpa.reporteanualbackend.dtos.report.s3;

import jakarta.validation.constraints.*;
import lombok.*;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class ActividadDesarrolloRequestDTO {
    @NotNull
    private Integer numActividad;

    @NotBlank @Size(max = 300)
    private String actividad;

    @NotBlank @Size(max = 300)
    private String institucionSolicitante;

    @NotNull @Min(0)
    private Short horasRequeridas;

    private String producto;
}