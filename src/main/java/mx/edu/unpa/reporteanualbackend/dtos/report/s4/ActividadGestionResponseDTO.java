package mx.edu.unpa.reporteanualbackend.dtos.report.s4;

import lombok.*;
import java.time.LocalDate;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class ActividadGestionResponseDTO {
    private Integer id;
    private Integer reporteId;
    private Integer numActividad;
    private String nombre;
    private String comisionOPuesto;
    private LocalDate periodoInicio;
    private LocalDate periodoFin;
}