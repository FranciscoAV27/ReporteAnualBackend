package mx.edu.unpa.reporteanualbackend.dtos.report.s3;

import lombok.*;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class IndicadorProyectoResponseDTO {
    private Integer id;
    private Integer proyectoId;
    private Integer numProyecto;
    private Integer numIndicador;
    private String descripcion;
}