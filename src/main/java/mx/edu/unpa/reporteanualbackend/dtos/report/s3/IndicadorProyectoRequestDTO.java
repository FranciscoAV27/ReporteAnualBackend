package mx.edu.unpa.reporteanualbackend.dtos.report.s3;

import jakarta.validation.constraints.*;
import lombok.*;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class IndicadorProyectoRequestDTO {
    @NotNull
    private Integer numProyecto;

    @NotNull
    private Integer numIndicador;

    @NotBlank
    private String descripcion;
}