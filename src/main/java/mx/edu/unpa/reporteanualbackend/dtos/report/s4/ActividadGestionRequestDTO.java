package mx.edu.unpa.reporteanualbackend.dtos.report.s4;

import jakarta.validation.constraints.*;
import lombok.*;
import java.time.LocalDate;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class ActividadGestionRequestDTO {
    @NotNull
    private Integer numActividad;

    @NotBlank @Size(max = 300)
    private String nombre;

    @Size(max = 300)
    private String comisionOPuesto;

    private LocalDate periodoInicio;
    private LocalDate periodoFin;
}