package mx.edu.unpa.reporteanualbackend.entities.report.s3;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;

@Entity
@Table(name = "indicadores_proyecto")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class IndicadorProyecto {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "proyecto_id", nullable = false)
    private ProyectoInvestigacion proyecto;

    @NotNull
    @Column(name = "num_proyecto", nullable = false)
    private Integer numProyecto;

    @NotNull
    @Column(name = "num_indicador", nullable = false)
    private Integer numIndicador;

    @NotBlank
    @Column(nullable = false, columnDefinition = "TEXT")
    private String descripcion;
}