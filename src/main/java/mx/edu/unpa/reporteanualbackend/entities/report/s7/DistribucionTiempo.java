package mx.edu.unpa.reporteanualbackend.entities.report.s7;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;
import mx.edu.unpa.reporteanualbackend.entities.report.Reporte;
import java.math.BigDecimal;

@Entity
@Table(name = "distribucion_tiempo")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class DistribucionTiempo {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "reporte_id", nullable = false)
    private Reporte reporte;

    @NotBlank @Size(max = 200)
    @Column(name = "actividad_academica", nullable = false, length = 200)
    private String actividadAcademica;

    @NotNull
    @Column(nullable = false, precision = 2, scale = 1)
    private BigDecimal orden;

    @Column(name = "horas_ciclo_oi", nullable = false, precision = 5, scale = 2)
    private BigDecimal horasCicloOi = BigDecimal.ZERO;

    @Column(name = "horas_ciclo_pv", nullable = false, precision = 5, scale = 2)
    private BigDecimal horasCicloPv = BigDecimal.ZERO;

    @Column(name = "horas_verano", nullable = false, precision = 5, scale = 2)
    private BigDecimal horasVerano = BigDecimal.ZERO;
}