package mx.edu.unpa.reporteanualbackend.dtos.report.s7;

import lombok.*;
import java.math.BigDecimal;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class DistribucionTiempoResponseDTO {
    private Integer id;
    private Integer reporteId;
    private String actividadAcademica;
    private BigDecimal orden;
    private BigDecimal horasCicloOi;
    private BigDecimal horasCicloPv;
    private BigDecimal horasVerano;
}