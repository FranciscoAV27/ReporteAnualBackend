package mx.edu.unpa.reporteanualbackend.dtos.report.s3;

import lombok.*;
import mx.edu.unpa.reporteanualbackend.entities.enums.*;
import java.time.LocalDate;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class ProyectoInvestigacionResponseDTO {
    private Integer id;
    private Integer reporteId;
    private Integer numProyecto;
    private String titulo;
    private ResponsabilidadProyecto responsabilidad;
    private FaseAprobacion faseAprobacion;
    private InstanciaProyecto instancia;
    private LocalDate fechaInicio;
    private LocalDate fechaTerminacion;
    private LocalDate fechaReprog;
    private Byte avancePorcentaje;
}