package mx.edu.unpa.reporteanualbackend.dtos.report.s2;

import jakarta.validation.constraints.*;
import lombok.*;
import mx.edu.unpa.reporteanualbackend.entities.enums.GradoTesis;
import java.time.LocalDate;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class DireccionTesisRequestDTO {
    @NotBlank
    private String titulo;

    @NotBlank @Size(max = 200)
    private String nombreAlumno;

    @NotNull
    private GradoTesis grado;

    @Min(0) @Max(100)
    private Byte avancePorcentaje;

    private LocalDate fechaRegistro;
    private LocalDate fechaProgTerm;
    private LocalDate fechaReprogTerm;
}