package mx.edu.unpa.reporteanualbackend.dtos.report.s3;

import lombok.*;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class ActividadDesarrolloResponseDTO {
    private Integer id;
    private Integer reporteId;
    private Integer numActividad;
    private String actividad;
    private String institucionSolicitante;
    private Short horasRequeridas;
    private String producto;
}