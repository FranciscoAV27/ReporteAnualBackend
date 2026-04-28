package mx.edu.unpa.reporteanualbackend.dtos.report.s5;

import jakarta.validation.constraints.*;
import lombok.*;
import java.time.LocalDate;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class ActividadDifusionRequestDTO {
    @NotNull
    private Integer numActividad;

    @NotBlank @Size(max = 300)
    private String nombre;

    private LocalDate periodoInicio;
    private LocalDate periodoFin;
}