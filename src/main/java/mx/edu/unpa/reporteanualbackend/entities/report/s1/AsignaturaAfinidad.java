package mx.edu.unpa.reporteanualbackend.entities.report.s1;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;
import mx.edu.unpa.reporteanualbackend.entities.report.Reporte;

@Entity
@Table(name = "asignaturas_afinidad")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class AsignaturaAfinidad {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "reporte_id", nullable = false)
    private Reporte reporte;

    @NotNull
    @Column(name = "num_asignatura", nullable = false)
    private Integer numAsignatura;

    @NotBlank @Size(max = 150)
    @Column(nullable = false, length = 150)
    private String carrera;

    @NotBlank @Size(max = 200)
    @Column(nullable = false, length = 200)
    private String asignatura;

    @NotNull @Min(1) @Max(12)
    @Column(nullable = false)
    private Byte semestre;
}