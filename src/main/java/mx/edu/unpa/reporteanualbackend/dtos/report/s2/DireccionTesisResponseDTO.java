package mx.edu.unpa.reporteanualbackend.dtos.report.s2;

import lombok.*;
import mx.edu.unpa.reporteanualbackend.entities.enums.GradoTesis;
import java.time.LocalDate;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class DireccionTesisResponseDTO {
    private Integer id;
    private Integer reporteId;
    private String titulo;
    private String nombreAlumno;
    private GradoTesis grado;
    private Byte avancePorcentaje;
    private LocalDate fechaRegistro;
    private LocalDate fechaProgTerm;
    private LocalDate fechaReprogTerm;
}