package mx.edu.unpa.reporteanualbackend.dtos.report;

import jakarta.validation.constraints.*;
import lombok.*;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class ReporteRequestDTO {

    @NotNull @Min(2000) @Max(2100)
    private Integer anio;

    private String problemasDocencia;
    private String oportunidadesDocencia;
    private String problemasInvestigacion;
    private String oportunidadesInvestigacion;
    private String comentariosGenerales;
}