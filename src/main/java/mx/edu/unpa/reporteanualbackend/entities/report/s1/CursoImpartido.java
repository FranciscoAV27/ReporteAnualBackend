package mx.edu.unpa.reporteanualbackend.entities.report.s1;


import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;
import mx.edu.unpa.reporteanualbackend.entities.report.Reporte;

@Entity
@Table(name = "cursos_impartidos")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class CursoImpartido {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "reporte_id", nullable = false)
    private Reporte reporte;

    @NotNull
    @Column(name = "numero_curso", nullable = false)
    private Integer numeroCurso;

    @NotBlank @Size(max = 150)
    @Column(nullable = false, length = 150)
    private String carrera;

    @NotBlank @Size(max = 200)
    @Column(nullable = false, length = 200)
    private String asignatura;

    @NotNull @Min(1) @Max(12)
    @Column(nullable = false)
    private Byte semestre;

    @NotBlank @Size(max = 20)
    @Column(name = "ciclo_escolar", nullable = false, length = 20)
    private String cicloEscolar;

    @NotNull @Min(1)
    @Column(name = "horas_semana", nullable = false)
    private Byte horasSemana;

    @NotNull @Min(0)
    @Column(name = "num_alumnos", nullable = false)
    private Short numAlumnos;
}