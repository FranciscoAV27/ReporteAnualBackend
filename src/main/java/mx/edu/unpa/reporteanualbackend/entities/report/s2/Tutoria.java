package mx.edu.unpa.reporteanualbackend.entities.report.s2;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;
import mx.edu.unpa.reporteanualbackend.entities.report.Reporte;
import java.time.LocalDate;

@Entity
@Table(name = "tutorias")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class Tutoria {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "reporte_id", nullable = false)
    private Reporte reporte;

    @NotBlank @Size(max = 200)
    @Column(name = "nombre_alumno", nullable = false, length = 200)
    private String nombreAlumno;

    @NotBlank @Size(max = 150)
    @Column(nullable = false, length = 150)
    private String carrera;

    @NotNull @Min(1) @Max(12)
    @Column(nullable = false)
    private Byte semestre;

    @NotNull
    @Column(name = "fecha_registro", nullable = false)
    private LocalDate fechaRegistro;
}