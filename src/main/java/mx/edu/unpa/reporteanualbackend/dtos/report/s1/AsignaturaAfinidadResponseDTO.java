package mx.edu.unpa.reporteanualbackend.dtos.report.s1;

import lombok.*;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class AsignaturaAfinidadResponseDTO {
    private Integer id;
    private Integer reporteId;
    private Integer numAsignatura;
    private String carrera;
    private String asignatura;
    private Byte semestre;
}