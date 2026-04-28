package mx.edu.unpa.reporteanualbackend.entities.report.s2;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;
import mx.edu.unpa.reporteanualbackend.entities.enums.GradoTesis;
import mx.edu.unpa.reporteanualbackend.entities.report.Reporte;
import java.time.LocalDate;

@Entity
@Table(name = "direcciones_tesis")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class DireccionTesis {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "reporte_id", nullable = false)
    private Reporte reporte;

    @NotBlank
    @Column(nullable = false, columnDefinition = "TEXT")
    private String titulo;

    @NotBlank @Size(max = 200)
    @Column(name = "nombre_alumno", nullable = false, length = 200)
    private String nombreAlumno;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 1)
    private GradoTesis grado;

    @Min(0) @Max(100)
    @Column(name = "avance_porcentaje", nullable = false)
    private Byte avancePorcentaje = 0;

    @Column(name = "fecha_registro")
    private LocalDate fechaRegistro;

    @Column(name = "fecha_prog_term")
    private LocalDate fechaProgTerm;

    @Column(name = "fecha_reprog_term")
    private LocalDate fechaReprogTerm;
}