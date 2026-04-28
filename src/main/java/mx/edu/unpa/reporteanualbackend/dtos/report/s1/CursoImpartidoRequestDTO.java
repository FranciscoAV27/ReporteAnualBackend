package mx.edu.unpa.reporteanualbackend.dtos.report.s1;

import jakarta.validation.constraints.*;
import lombok.*;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class CursoImpartidoRequestDTO {
    @NotNull private Integer numeroCurso;
    @NotBlank @Size(max = 150) private String carrera;
    @NotBlank @Size(max = 200) private String asignatura;
    @NotNull @Min(1) @Max(12) private Byte semestre;
    @NotBlank @Size(max = 20) private String cicloEscolar;
    @NotNull @Min(1) private Byte horasSemana;
    @NotNull @Min(0) private Short numAlumnos;
}