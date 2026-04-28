package mx.edu.unpa.reporteanualbackend.dtos.report.s1;

import lombok.*;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class ProductoApoyoDocenteResponseDTO {
    private Integer id;
    private Integer reporteId;
    private Integer numeroCurso;
    private String descripcion;
    private String enlace;
}