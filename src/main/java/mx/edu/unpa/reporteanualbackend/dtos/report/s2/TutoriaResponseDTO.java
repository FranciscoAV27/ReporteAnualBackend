package mx.edu.unpa.reporteanualbackend.dtos.report.s2;

import lombok.*;
import java.time.LocalDate;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class TutoriaResponseDTO {
    private Integer id;
    private Integer reporteId;
    private String nombreAlumno;
    private String carrera;
    private Byte semestre;
    private LocalDate fechaRegistro;
}