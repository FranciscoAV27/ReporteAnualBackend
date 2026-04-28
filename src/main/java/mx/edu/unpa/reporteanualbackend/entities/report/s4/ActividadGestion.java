package mx.edu.unpa.reporteanualbackend.entities.report.s4;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;
import mx.edu.unpa.reporteanualbackend.entities.report.Reporte;
import java.time.LocalDate;

@Entity
@Table(name = "actividades_gestion")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class ActividadGestion {

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
    private String nombre;

    @NotBlank @Size(max = 300)
    @Column(name = "comision_o_puesto", nullable = false, length = 300)
    private String comisionOPuesto;

    @Column(name = "periodo_inicio")
    private LocalDate periodoInicio;

    @Column(name = "periodo_fin")
    private LocalDate periodoFin;
}
