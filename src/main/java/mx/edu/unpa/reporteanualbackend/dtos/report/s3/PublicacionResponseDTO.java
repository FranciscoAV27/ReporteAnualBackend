package mx.edu.unpa.reporteanualbackend.dtos.report.s3;

import lombok.*;
import mx.edu.unpa.reporteanualbackend.entities.enums.FasePublicacion;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class PublicacionResponseDTO {
    private Integer id;
    private Integer reporteId;
    private Integer numPublicacion;
    private String titulo;
    private String revista;
    private FasePublicacion fase;
    private Integer anio;
}