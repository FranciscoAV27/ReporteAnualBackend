package mx.edu.unpa.reporteanualbackend.entities.report.s3;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;
import mx.edu.unpa.reporteanualbackend.entities.enums.*;
import mx.edu.unpa.reporteanualbackend.entities.report.Reporte;

import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "proyectos_investigacion")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class ProyectoInvestigacion {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "reporte_id", nullable = false)
    private Reporte reporte;

    @NotNull
    @Column(name = "num_proyecto", nullable = false)
    private Integer numProyecto;

    @NotBlank
    @Column(nullable = false, columnDefinition = "TEXT")
    private String titulo;

    @NotNull @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 1)
    private ResponsabilidadProyecto responsabilidad;

    @NotNull @Enumerated(EnumType.STRING)
    @Column(name = "fase_aprobacion", nullable = false, length = 3)
    private FaseAprobacion faseAprobacion;

    @NotNull @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 1)
    private InstanciaProyecto instancia;

    @Column(name = "fecha_inicio")
    private LocalDate fechaInicio;

    @Column(name = "fecha_terminacion")
    private LocalDate fechaTerminacion;

    @Column(name = "fecha_reprog")
    private LocalDate fechaReprog;

    @Min(0) @Max(100)
    @Column(name = "avance_porcentaje", nullable = false)
    private Byte avancePorcentaje = 0;

    @OneToMany(mappedBy = "proyecto", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<IndicadorProyecto> indicadores;
}