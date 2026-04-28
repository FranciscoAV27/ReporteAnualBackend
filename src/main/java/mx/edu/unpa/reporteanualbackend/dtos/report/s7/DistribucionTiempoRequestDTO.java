package mx.edu.unpa.reporteanualbackend.dtos.report.s7;

import jakarta.validation.constraints.*;
import lombok.*;
import java.math.BigDecimal;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class DistribucionTiempoRequestDTO {
    @NotBlank @Size(max = 200)
    private String actividadAcademica;

    @NotNull
    @DecimalMin("0.0") @DecimalMax("9.9")
    @Digits(integer = 1, fraction = 1)
    private BigDecimal orden;

    @DecimalMin("0.0")
    private BigDecimal horasCicloOi;

    @DecimalMin("0.0")
    private BigDecimal horasCicloPv;

    @DecimalMin("0.0")
    private BigDecimal horasVerano;
}