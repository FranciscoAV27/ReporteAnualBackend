package mx.edu.unpa.reporteanualbackend.entities.report.s3;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;
import mx.edu.unpa.reporteanualbackend.entities.report.Reporte;

@Entity
@Table(name = "actividades_desarrollo")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class ActividadDesarrollo {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "reporte_id", nullable = false)
    private Reporte reporte;

    @NotNull
    @Column(name = "num_actividad", nullable = false)
    private Integer numActividad;

    @NotBlank @Size(max = 300)
    @Column(nullable = false, length = 300)
    private String actividad;

    @NotBlank @Size(max = 300)
    @Column(name = "institucion_solicitante", nullable = false, length = 300)
    private String institucionSolicitante;

    @NotNull @Min(0)
    @Column(name = "horas_requeridas", nullable = false)
    private Short horasRequeridas;

    @Column(columnDefinition = "TEXT")
    private String producto;
}