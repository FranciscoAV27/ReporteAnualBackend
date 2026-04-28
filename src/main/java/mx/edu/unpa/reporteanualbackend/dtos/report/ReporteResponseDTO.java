package mx.edu.unpa.reporteanualbackend.dtos.report;

import lombok.*;
import mx.edu.unpa.reporteanualbackend.entities.enums.EstadoReporte;
import java.time.LocalDateTime;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class ReporteResponseDTO {
    private Integer id;
    private Integer profesorId;
    private String profesorNombre;
    private Integer anio;
    private EstadoReporte estado;
    private String comentariosAdmin;
    private String problemasDocencia;
    private String oportunidadesDocencia;
    private String problemasInvestigacion;
    private String oportunidadesInvestigacion;
    private String comentariosGenerales;
    private LocalDateTime creadoEn;
    private LocalDateTime actualizadoEn;
    private LocalDateTime enviadoEn;
    private LocalDateTime aprobadoEn;
}