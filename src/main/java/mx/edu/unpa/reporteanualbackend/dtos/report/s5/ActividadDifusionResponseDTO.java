package mx.edu.unpa.reporteanualbackend.dtos.report.s5;

import lombok.*;
import java.time.LocalDate;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class ActividadDifusionResponseDTO {
    private Integer id;
    private Integer reporteId;
    private Integer numActividad;
    private String nombre;
    private LocalDate periodoInicio;
    private LocalDate periodoFin;
}