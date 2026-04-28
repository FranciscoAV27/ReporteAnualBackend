package mx.edu.unpa.reporteanualbackend.dtos.report.s3;

import jakarta.validation.constraints.*;
import lombok.*;
import mx.edu.unpa.reporteanualbackend.entities.enums.*;
import java.time.LocalDate;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class ProyectoInvestigacionRequestDTO {
    @NotNull
    private Integer numProyecto;

    @NotBlank
    private String titulo;

    @NotNull
    private ResponsabilidadProyecto responsabilidad;

    @NotNull
    private FaseAprobacion faseAprobacion;

    @NotNull
    private InstanciaProyecto instancia;

    private LocalDate fechaInicio;
    private LocalDate fechaTerminacion;
    private LocalDate fechaReprog;

    @Min(0) @Max(100)
    private Byte avancePorcentaje;
}