package mx.edu.unpa.reporteanualbackend.dtos.report.s2;

import jakarta.validation.constraints.*;
import lombok.*;
import java.time.LocalDate;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class TutoriaRequestDTO {
    @NotBlank @Size(max = 200)
    private String nombreAlumno;

    @NotBlank @Size(max = 150)
    private String carrera;

    @NotNull @Min(1) @Max(12)
    private Byte semestre;

    @NotNull
    private LocalDate fechaRegistro;
}